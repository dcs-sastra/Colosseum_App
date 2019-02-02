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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;
import colosseum19.a300dpi.colosseum2k19.Adapters.PointsAdapter;
import colosseum19.a300dpi.colosseum2k19.Model.Score;
import colosseum19.a300dpi.colosseum2k19.R;

public class ScoresFragment extends Fragment {


    private String TAG = ScoresFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private List<Score> scoreList = new ArrayList<>();

    private Context context;
    private PointsAdapter pointsAdapter = new PointsAdapter();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collection_scores = db.collection("scores");

    public ScoresFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_scores,container,false);
        ButterKnife.bind(this,view);
        context = view.getContext();

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        db.collection("scores")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "listen:error", e);
                            return;
                        }

                        for(DocumentChange dc: queryDocumentSnapshots.getDocumentChanges()){
                            switch (dc.getType()){
                                case ADDED:
                                    pointsAdapter.addScores(dc.getDocument().toObject(Score.class));
                                    pointsAdapter.notifyItemInserted(pointsAdapter.numberOfevents());
                                    break;
                                case REMOVED:
                                    break;
                            }
                        }
                    }
                });

        collection_scores
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot snapshot: Objects.requireNonNull(task.getResult())){
                                Score score = snapshot.toObject(Score.class);
                                Toast.makeText(context, score.getEvent_name(), Toast.LENGTH_SHORT).show();
                                scoreList.add(score);
                            }
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

                            pointsAdapter.setScores(scoreList);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setAdapter(pointsAdapter);

                        }else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
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
}
