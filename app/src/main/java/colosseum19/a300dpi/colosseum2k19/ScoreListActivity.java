package colosseum19.a300dpi.colosseum2k19;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import colosseum19.a300dpi.colosseum2k19.API.BackupApi;
import colosseum19.a300dpi.colosseum2k19.Adapters.ScoreAdapter;
import colosseum19.a300dpi.colosseum2k19.Adapters.ScoreGameListAdapter;
import colosseum19.a300dpi.colosseum2k19.Interfaces.ApiCallback;
import colosseum19.a300dpi.colosseum2k19.Interfaces.CallbackInterface;
import colosseum19.a300dpi.colosseum2k19.Model.Score;

public class ScoreListActivity extends AppCompatActivity implements ApiCallback {

    String TAG = ScoreListActivity.class.getSimpleName();
    RecyclerView scoreRecyclerView;
    ScoreAdapter scoreAdapter;
    Toolbar toolbar;
    ProgressDialog progressDialog;

    ArrayList<Score> scoreArrayList;
    int day;
    String selectedGame;
    BackupApi backupApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);

        setSupportActionBar(toolbar);
        if(getIntent() != null){
            day = getIntent().getIntExtra(getString(R.string.day_key), 0);
            selectedGame = getIntent().getStringExtra(getString(R.string.game_name));

        }else{
            day = 0;
            Log.d(TAG, "INTENT_DATE_ERROR");
        }

        scoreArrayList = new ArrayList<>();
        scoreRecyclerView = findViewById(R.id.score_recycler_view);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        backupApi = new BackupApi();
        backupApi.checkIfBackup(getApplicationContext(), this);
        progressDialog.show();
    }

    @Override
    public void shouldUseBackup(boolean isSuccess, boolean isBackup) {
        progressDialog.cancel();
        if(isSuccess){
            scoreAdapter = new ScoreAdapter(this, selectedGame, day, isBackup);
            scoreRecyclerView.setAdapter(scoreAdapter);
            scoreRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else{
            Toast.makeText(getApplicationContext(), getString(R.string.try_again), Toast.LENGTH_SHORT).show();
        }
    }

    public  void getGameScores(String query, int day, final CallbackInterface callbackInterface){
        String collectionPath = "scores_day"+day;
        Log.d("TEST_QUERY",query);
        Query gameQuery = FirebaseFirestore.getInstance()
                .collection(collectionPath)
                .whereEqualTo("game_name", query)
                .orderBy("timestamp");

        gameQuery.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(queryDocumentSnapshots.size() > 0){
                    scoreArrayList.clear();
                    Log.d("SCORE_GET","TASK SUCCESSFULL");
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        Score singleScore = doc.toObject(Score.class);
                        scoreArrayList.add(singleScore);
                    }
                    //QueryData.setData(fixtureArrayList);
                    callbackInterface.setScoreData(scoreArrayList, false);
                }else{
                    callbackInterface.setScoreData(null,true);
                    Log.d("NULL","NULL");

                }
            }
        });
    }
}
