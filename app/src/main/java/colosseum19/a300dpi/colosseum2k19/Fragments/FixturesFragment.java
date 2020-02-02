package colosseum19.a300dpi.colosseum2k19.Fragments;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;
import colosseum19.a300dpi.colosseum2k19.Adapters.FixtureAdapter;
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

public class FixturesFragment extends Fragment{

    private static final String TAG = FixturesFragment.class.getSimpleName();

    private Context context;
    private RecyclerView gameList;
    private FixtureAdapter fixtureAdapter = new FixtureAdapter(getActivity());
    private FixtureGameListAdapter fixtureGameListAdapter;
    private HelperClass helperClass = new HelperClass();
    private ConstraintLayout rootLayout;

    public List<Fixture> fixtureArrayList = new ArrayList<>();

    private ColosseumDatabase colosseumDatabase;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collection_events = db.collection("current_events");

    ColosseumViewModelFactory colosseumViewModelFactory;
    ColosseumViewModel colosseumViewModel;

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

        rootLayout = view.findViewById(R.id.fixture_root_layout);

        colosseumDatabase = ColosseumDatabase.getInstance(context);
        setUpFixturesViewModel("Basketball");

        //recycler adapter for game names
        gameList = view.findViewById(R.id.fixtures_game_names_list);
        fixtureGameListAdapter = new FixtureGameListAdapter(getActivity());
        gameList.setAdapter(fixtureGameListAdapter);
        gameList.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*db.collection("current_events")
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

    //to get the results for particular game
    //send the data back toadapter using interface
    public void getGameFixtures(String query, final CallbackInterface callbackInterface,
                                final FixtureGameListAdapter.GameHolder gameHolder) {
        Log.d(TAG,query);
        fixtureArrayList.clear();

        colosseumViewModel.getFixtureEntries(query).observe(this, new Observer<List<FixtureEntry>>() {
            @Override
            public void onChanged(@Nullable List<FixtureEntry> fixtureEntries) {

                if (fixtureEntries != null) {
                    fixtureArrayList = helperClass.convertFixtureEntryIntoFixture(fixtureEntries);
                    callbackInterface.setFixtureData(fixtureArrayList, gameHolder, false);
                    Log.d(TAG, "Not NULL");
                } else {
                    callbackInterface.setFixtureData(null, null, true);
                    Log.d(TAG, "NULL");
                }
            }
        });
       /*
        Query gameQuery = db.collection("current_events").whereEqualTo("game_name", query);
        gameQuery.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.d("SIZE",queryDocumentSnapshots.size()+"");
                if(queryDocumentSnapshots.size() > 0){
                    fixtureArrayList.clear();
                    Log.d(TAG,"TASK SUCCESSFULL");
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        Fixture singleFixture = doc.toObject(Fixture.class);
                        Log.d(TAG,singleFixture.getEvent_name());
                        Log.d(TAG,singleFixture.getEvent_time());
                        Log.d(TAG,singleFixture.getGame_name());
                        Log.d(TAG,singleFixture.getTeamA());
                        Log.d(TAG,singleFixture.getTeamB());
                        fixtureArrayList.add(singleFixture);
                    }
                    //QueryData.setData(fixtureArrayList);
                    callbackInterface.setFixtureData(fixtureArrayList,gameHolder,false);
                }else{
                    callbackInterface.setFixtureData(null,null,true);
                    Log.d("NULL","NULL");
                }

            }
        });*/
    }

    // ***** DATABASE  IMPLEMENTATION ***** //
    private void setUpFixturesViewModel(String game_name) {

        colosseumViewModelFactory = new ColosseumViewModelFactory(colosseumDatabase, game_name);
        colosseumViewModel = ViewModelProviders
                .of(this, colosseumViewModelFactory)
                .get(ColosseumViewModel.class);

        colosseumViewModel.getFixtureEntries(game_name).observe(this, new Observer<List<FixtureEntry>>() {
            @Override
            public void onChanged(@Nullable List<FixtureEntry> fixtureEntries) {

                if (fixtureEntries != null) {
                    //fixtureArrayList.clear();
                    //fixtureArrayList = new ArrayList<>();
                    fixtureArrayList = helperClass.convertFixtureEntryIntoFixture(fixtureEntries);
                }
            }
        });


    }

  /*  private void loadDataFromFirestore(){
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
                                fixtureArrayList.add(fixture);
                            }

                        }else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        if(fixtureArrayList != null){

            AppExecutors.getInstance().executorForDatabase().execute(new Runnable() {
                @Override
                public void run() {
                    colosseumDatabase.colosseumDao.deleteFixtureTable();
                }
            });
            colosseumViewModel.loadFixturesIntoDatabase(convertFixtureIntoFixtureEntry(fixtureArrayList));
        }

    }*/
// LoaderCallback method for loading Fixtures into Database
   /* private LoaderManager.LoaderCallbacks<Boolean> loaderCallbacksForFixture(final ArrayList<FixtureEntry> fixtureEntries){
        return new LoaderManager.LoaderCallbacks<Boolean>() {
            @SuppressLint("StaticFieldLeak")
            @NonNull
            @Override
            public Loader<Boolean> onCreateLoader(int i, @Nullable Bundle bundle) {
                return new AsyncTaskLoader<Boolean>(context) {

                    @Override
                    protected void onStartLoading() {
                        super.onStartLoading();
                    }

                    @Nullable
                    @Override
                    public Boolean loadInBackground() {
                        loadFixturesIntoDatabase(fixtureEntries);
                        return true;
                    }
                };
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Boolean> loader, Boolean dataAdded) {
                if (dataAdded)
                    Log.d(TAG, "Loaded into DB");
            }

            @Override
            public void onLoaderReset(@NonNull Loader<Boolean> loader) {

            }
        };
    }

    private void loadFixturesIntoDatabase(ArrayList<FixtureEntry> fixtureEntries){
            colosseumDatabase.colosseumDao.insertFixtures(fixtureEntries);
    }*/

    private void loadEventsFromFirestore() {
        collection_events
                .orderBy("timestamp")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "addOnCompleteListener");
                            for (QueryDocumentSnapshot snapshot : Objects.requireNonNull(task.getResult())) {
                                Fixture fixture = snapshot.toObject(Fixture.class);
                                fixture.setId(snapshot.getId());
                                Log.d(TAG, snapshot.getId());
                                fixtureArrayList.add(fixture);
                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        if (fixtureArrayList != null) {

            AppExecutors.getInstance().executorForDatabase().execute(new Runnable() {
                @Override
                public void run() {
                    colosseumDatabase.colosseumDao().deleteFixtureTable();
                    colosseumDatabase.colosseumDao().insertFixtures(
                            helperClass.convertFixtureIntoFixtureEntry(fixtureArrayList));
                }
            });
        }

    }

}

