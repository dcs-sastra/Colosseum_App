package colosseum19.a300dpi.colosseum2k19.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List
;
import java.util.Objects;

import butterknife.ButterKnife;
import colosseum19.a300dpi.colosseum2k19.Adapters.FixtureGameListAdapter;
import colosseum19.a300dpi.colosseum2k19.Database.ColosseumDatabase;
import colosseum19.a300dpi.colosseum2k19.Database.FixtureEntry;
import colosseum19.a300dpi.colosseum2k19.Interfaces.CallbackInterface;
import colosseum19.a300dpi.colosseum2k19.Model.Fixture;
import colosseum19.a300dpi.colosseum2k19.R;
import colosseum19.a300dpi.colosseum2k19.Utilities.AppExecutors;
import colosseum19.a300dpi.colosseum2k19.Utilities.HelperClass;
import colosseum19.a300dpi.colosseum2k19.ViewModel.ColosseumViewModel;
import colosseum19.a300dpi.colosseum2k19.ViewModel.ColosseumViewModelFactory;

public class FixturesFragment extends Fragment implements FixtureGameListAdapter.FixturesItemClickListener {

    private static final String TAG = FixturesFragment.class.getSimpleName();

    private Context context;
    private RecyclerView gameList;
    //private FixtureAdapter fixtureAdapter = new FixtureAdapter(getActivity());
    private FixtureGameListAdapter fixtureGameListAdapter;
    private ConstraintLayout rootLayout;

    public List<Fixture> fixtureList = new ArrayList<>();
    public List<Fixture> allFixtureList = new ArrayList<>();

    private HelperClass helperClass = new HelperClass();

    private ColosseumDatabase colosseumDatabase;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collection_events = db.collection("current_events");

    private ColosseumViewModelFactory colosseumViewModelFactory;
    private ColosseumViewModel colosseumViewModel;

    private static FixturesFragment instance;

    public static FixturesFragment getInstance(){
        if(instance == null){
            instance = new FixturesFragment();
        }
        return instance;
    }

    public FixturesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_fixtures, container, false);
        ButterKnife.bind(this, view);
        context = view.getContext();

        colosseumDatabase = ColosseumDatabase.getInstance(context);

        rootLayout = view.findViewById(R.id.fixture_root_layout);

        //recycler adapter for game names
        gameList = view.findViewById(R.id.fixtures_game_names_list);
        fixtureGameListAdapter = new FixtureGameListAdapter(getActivity(),this);
        gameList.setAdapter(fixtureGameListAdapter);
        gameList.setLayoutManager(new LinearLayoutManager(getActivity()));


        if (isUpdateRequired()){
            Log.d(TAG,"Update Required");
            loadEventsFromFirestore();
        }else {
            Log.d(TAG,"Update not Required");
        }


        colosseumViewModelFactory = new ColosseumViewModelFactory(colosseumDatabase);
        colosseumViewModel = ViewModelProviders
                .of(this, colosseumViewModelFactory)
                .get(ColosseumViewModel.class);
        colosseumViewModel.getAllFixtureEntries().observe(this, new Observer<List<FixtureEntry>>() {
            @Override
            public void onChanged(@Nullable List<FixtureEntry> fixtureEntryList) {

                allFixtureList = helperClass.convertFixtureEntryIntoFixture(fixtureEntryList);
                Log.d(TAG, "1) allFixtureList.size(): " + String.valueOf(allFixtureList.size()));
            }
        });
       /* db.collection("current_events")
                .orderBy("timestamp")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "listen:error", e);
                            return;
                        }
                        for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {
                            switch (dc.getType()) {
                                case ADDED:
                                    fixtureAdapter.addEvent(dc.getDocument().toObject(Fixture.class));
                                    fixtureAdapter.notifyItemInserted(fixtureAdapter.numberOfevents());
                                    break;
                                case REMOVED:
                                    break;
                            }
                        }

                    }
                });*/


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Override
    public void onFixtureClick(String game_name, CallbackInterface callbackInterface, FixtureGameListAdapter.GameHolder gameHolder) {

        Log.d(TAG,game_name);
        if (allFixtureList != null){
            Log.d(TAG,"allFixtureList != null");
            //Log.d("qqq",fixture.getGame_name());
        }
        fixtureList.clear();
        for (Fixture fixture: allFixtureList){
            if (fixture.getGame_name().equals(game_name)) {
                fixtureList.add(fixture);
            }
        }
        if (fixtureList != null){
            Log.d(TAG, " fixtureList.size(): " + String.valueOf(fixtureList.size()));
            callbackInterface.setFixtureData(fixtureList,gameHolder,false);
        }else {
            callbackInterface.setFixtureData(null,gameHolder,false);
        }
    }

// ***** DATABASE  IMPLEMENTATION ***** //
    private void loadEventsFromFirestore(){
        collection_events
                .orderBy("timestamp")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG,"addOnCompleteListener");
                            for (QueryDocumentSnapshot snapshot: Objects.requireNonNull(task.getResult())){
                                Fixture fixture = snapshot.toObject(Fixture.class);
                                fixture.setId(snapshot.getId());
                                Log.d(TAG,snapshot.getId());
                                fixtureList.add(fixture);
                            }

                            if(fixtureList != null){
                                Log.d(TAG, "if(fixtureList != null) ");

                                AppExecutors.getInstance().executorForDatabase().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.d(TAG, "AppExecutors");
                                        colosseumDatabase.colosseumDao().deleteFixtureTable();
                                        colosseumDatabase.colosseumDao().insertFixtures(
                                                helperClass.convertFixtureIntoFixtureEntry(fixtureList));
                                        Log.d(TAG, "AppExecutors - ");
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
        SharedPreferences sharedPref = context.getSharedPreferences("LastUpdated",context.MODE_PRIVATE);
        Date currentTime= Calendar.getInstance().getTime();
        if(currentTime!=null){
            Log.d("msg","date not null");
            Log.d("msg",currentTime.toString());

        }

        if(sharedPref.contains("UpdatedTime")){
//            Log.d("msg","1");
            String updatedTimeString= sharedPref.getString("UpdatedTime",null);
            Date updatedTime = helperClass.getDateFromString(updatedTimeString);
            int diffhr = currentTime.getMinutes()-updatedTime.getMinutes();

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("UpdatedTime",currentTime.toString());
            editor.apply();
//            Log.d("msg","Hours:"+diffhr+"");
//            Log.d("msg",currentTime.toString());

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
            editor.putString("UpdatedTime",currentTime.toString());
            editor.apply();
//            Log.d("msg","created for the first time");
//            Log.d("msg",currentTime.toString());
            return true;
        }


    }

}

