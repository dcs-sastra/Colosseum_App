package colosseum19.a300dpi.colosseum2k19.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;
import colosseum19.a300dpi.colosseum2k19.Adapters.ScoreGameListAdapter;
import colosseum19.a300dpi.colosseum2k19.Database.ColosseumDatabase;
import colosseum19.a300dpi.colosseum2k19.Database.FixtureEntry;
import colosseum19.a300dpi.colosseum2k19.Database.ScoreEntry;
import colosseum19.a300dpi.colosseum2k19.Interfaces.CallbackInterface;
import colosseum19.a300dpi.colosseum2k19.Model.Fixture;
import colosseum19.a300dpi.colosseum2k19.Model.Score;
import colosseum19.a300dpi.colosseum2k19.R;
import colosseum19.a300dpi.colosseum2k19.Utilities.AppExecutors;
import colosseum19.a300dpi.colosseum2k19.Utilities.HelperClass;
import colosseum19.a300dpi.colosseum2k19.ViewModel.ColosseumViewModel;
import colosseum19.a300dpi.colosseum2k19.ViewModel.ColosseumViewModelFactory;

public class ScoresFragment extends Fragment implements ScoreGameListAdapter.ScoresItemClickListener {


    private String TAG = ScoresFragment.class.getSimpleName();

    private RecyclerView gameNameList;
    private ScoreGameListAdapter gameListAdapter;
    //private List<Score> scoreList = new ArrayList<>();
    private List<Score> scoreList = new ArrayList<>();
    private List<Score> allScoreList = new ArrayList<>();

    private Context context;
    private static ScoresFragment instance;

    private HelperClass helperClass = new HelperClass();
    private ColosseumDatabase colosseumDatabase;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collection_scores = db.collection("scores");

    private ColosseumViewModelFactory colosseumViewModelFactory;
    private ColosseumViewModel colosseumViewModel;

    public static ScoresFragment getInstance(){
        if(instance == null){
            instance = new ScoresFragment();
        }
        return instance;
    }

    public ScoresFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_scores,container,false);
        ButterKnife.bind(this,view);
        context = view.getContext();

        colosseumDatabase = ColosseumDatabase.getInstance(context);

        gameNameList = view.findViewById(R.id.score_game_name_list);
        gameListAdapter = new ScoreGameListAdapter(getActivity(), this);
        gameNameList.setAdapter(gameListAdapter);
        gameNameList.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (isUpdateRequired()){
            Log.d(TAG,"Update Required");
            loadScoresFromFirestore();
        }else {
            Log.d(TAG,"Update not Required");
        }

        colosseumViewModelFactory = new ColosseumViewModelFactory(colosseumDatabase);
        colosseumViewModel = ViewModelProviders
                .of(this, colosseumViewModelFactory)
                .get(ColosseumViewModel.class);

        colosseumViewModel.getAllScoreEntries().observe(this, new Observer<List<ScoreEntry>>() {
            @Override
            public void onChanged(@Nullable List<ScoreEntry> scoreEntryList) {

                allScoreList = helperClass.convertScoreEntryIntoScore(scoreEntryList);
                Log.d(TAG, "1) allScoreList.size(): " + String.valueOf(allScoreList.size()));
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onScoreClick(String game_name, CallbackInterface callbackInterface, ScoreGameListAdapter.ScoreGameHolder scoreGameHolder) {
        Log.d(TAG,game_name);
        if (allScoreList != null){
            Log.d(TAG,"allScoreList != null");
        }

        scoreList.clear();
        for (Score score: allScoreList){
            if (score.getGame_name().equals(game_name)) {
                scoreList.add(score);
            }
        }
        if (scoreList != null){
            Log.d(TAG, " scoreList.size(): " + String.valueOf(scoreList.size()));
            callbackInterface.setScoreData(scoreList,scoreGameHolder,false);
        }else {
            callbackInterface.setScoreData(null,scoreGameHolder,false);
        }

    }

// ***** DATABASE  IMPLEMENTATION ***** //

    private void loadScoresFromFirestore(){
        collection_scores
                .orderBy("timestamp")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG,"addOnCompleteListener");
                            for (QueryDocumentSnapshot snapshot: Objects.requireNonNull(task.getResult())){
                                Score score = snapshot.toObject(Score.class);
                                Log.d(TAG,snapshot.getId());
                                scoreList.add(score);
                            }
                            if(scoreList != null){

                                AppExecutors.getInstance().executorForDatabase().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.d(TAG, "AppExecutors");
                                        colosseumDatabase.colosseumDao().deleteScoreTable();
                                        colosseumDatabase.colosseumDao().insertScores(
                                                helperClass.convertScoreIntoScoreEntry(scoreList));
                                        Log.d(TAG, "AppExecutors-");
                                    }
                                });
                            }

                        }else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public boolean isUpdateRequired(){
        SharedPreferences sharedPref = context.getSharedPreferences("LastUpdatedScore",context.MODE_PRIVATE);
        Date currentTime= Calendar.getInstance().getTime();
        if(currentTime!=null){
            Log.d("msg","date not null");
            Log.d("msg",currentTime.toString());

        }

        if(sharedPref.contains("UpdatedTimeScore")){
//            Log.d("msg","1");
            String updatedTimeString= sharedPref.getString("UpdatedTimeScore",null);
            Date updatedTime = helperClass.getDateFromString(updatedTimeString);
            int diffhr = currentTime.getHours()-updatedTime.getHours();
//            Log.d("msg","Hours:"+diffhr+"");
//            Log.d("msg",currentTime.toString());
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("UpdatedTimeScore",currentTime.toString());
            editor.apply();

            if(diffhr>1){
                return true;
            }

            else{
                return false;
            }

        }

        else{
//            Log.d("msg","2");
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("UpdatedTimeScore",currentTime.toString());
            editor.apply();
//            Log.d("msg","created for the first time");
//            Log.d("msg",currentTime.toString());
            return true;
        }
    }
}
