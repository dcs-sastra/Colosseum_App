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
import colosseum19.a300dpi.colosseum2k19.Adapters.FixtureAdapter;
import colosseum19.a300dpi.colosseum2k19.Adapters.FixtureGameListAdapter;
import colosseum19.a300dpi.colosseum2k19.Interfaces.ApiCallback;
import colosseum19.a300dpi.colosseum2k19.Interfaces.CallbackInterface;
import colosseum19.a300dpi.colosseum2k19.Model.Fixture;

public class FixtureListActivity extends AppCompatActivity implements ApiCallback {

    FixtureAdapter fixtureAdapter;
    RecyclerView fixtureRecyclerView;
    Toolbar toolbar;
    private FirebaseFirestore db;
    ProgressDialog progressDialog;


    ArrayList<Fixture> fixtureArrayList = new ArrayList<>();
    String TAG = "FIXTURE_LIST_ACTIVITY";
    int day = 0;
    String selectedGame = "";
    BackupApi backupApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixture_list);

        toolbar = findViewById(R.id.fixture_toolbar);
        setSupportActionBar(toolbar);
        db = FirebaseFirestore.getInstance();

        if(getIntent() != null){
            day = getIntent().getIntExtra(getString(R.string.day_key), 0);
            selectedGame = getIntent().getStringExtra(getString(R.string.game_name));
        }else{
            day = 1;
            Log.d(TAG, "INTENT_DATE_ERROR");
        }

        fixtureRecyclerView = findViewById(R.id.fixture_recycler_view);
        //checking whether to use backup data
        backupApi = new BackupApi();
        backupApi.checkIfBackup(getApplicationContext(), this);

        //display progress bar
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();


    }


    @Override
    public void shouldUseBackup(boolean isSuccess, boolean isBackup) {
        progressDialog.cancel();
        if(isSuccess) {
            fixtureAdapter = new FixtureAdapter(this, selectedGame, day, isBackup);
            fixtureRecyclerView.setAdapter(fixtureAdapter);
            fixtureRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else{
            Toast.makeText(getApplicationContext(), getString(R.string.try_again), Toast.LENGTH_SHORT).show();
        }
    }

    public void getGameFixturesFirebase(String query, int day, final CallbackInterface callbackInterface) {
        Log.d(TAG, query);
        String collectionPath = "current_events_day"+day;
        Query gameQuery = db.collection(collectionPath).whereEqualTo("game_name", query);

        gameQuery.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.d("SIZE", queryDocumentSnapshots.size() + "");
                if (queryDocumentSnapshots.size() > 0) {
                    fixtureArrayList.clear();
                    Log.d(TAG, "TASK SUCCESSFULL");
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        Fixture singleFixture = doc.toObject(Fixture.class);
                        Log.d(TAG, singleFixture.getEvent_name());
                        Log.d(TAG, singleFixture.getEvent_time());
                        Log.d(TAG, singleFixture.getGame_name());
                        Log.d(TAG, singleFixture.getTeamA());
                        Log.d(TAG, singleFixture.getTeamB());
                        fixtureArrayList.add(singleFixture);
                    }
                    //QueryData.setData(fixtureArrayList);
                    callbackInterface.setFixtureData(fixtureArrayList, false);
                } else {
                    callbackInterface.setFixtureData(null, true);
                    Log.d("NULL", "NULL");
                }

            }
        });
    }


}
