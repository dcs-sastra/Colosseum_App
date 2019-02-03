package colosseum19.a300dpi.colosseum2k19.Fragments;

import android.content.Context;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import butterknife.ButterKnife;
import colosseum19.a300dpi.colosseum2k19.Adapters.FixtureAdapter;
import colosseum19.a300dpi.colosseum2k19.Adapters.GameListAdapter;
import colosseum19.a300dpi.colosseum2k19.Interfaces.CallbackInterface;
import colosseum19.a300dpi.colosseum2k19.Model.Fixture;
import colosseum19.a300dpi.colosseum2k19.Model.QueryData;
import colosseum19.a300dpi.colosseum2k19.R;

public class FixturesFragment extends Fragment{

    private static final String TAG = FixturesFragment.class.getSimpleName();

    private Context context;
    private RecyclerView gameList;
    private FixtureAdapter fixtureAdapter = new FixtureAdapter();
    private GameListAdapter gameListAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ArrayList<Fixture> fixtureArrayList = new ArrayList<>();

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

        //recycler adapter for game names
        gameList = view.findViewById(R.id.game_names_list);
        gameListAdapter = new GameListAdapter(getActivity());
        gameList.setAdapter(gameListAdapter);
        gameList.setLayoutManager(new LinearLayoutManager(getActivity()));


        db.collection("current_events")
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
                });


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

    //to get the results for particular game
    //send the data back toadapter using interface
    public  void getGameFixtures(String query, final CallbackInterface callbackInterface, final GameListAdapter.GameHolder gameHolder){
        Log.d(TAG,query);
        Query gameQuery = db.collection("current_events").whereEqualTo("game_name", query);
        gameQuery.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
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
                callbackInterface.setData(fixtureArrayList,gameHolder);
            }
        });
    }

}

