package colosseum19.a300dpi.colosseum2k19.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import colosseum19.a300dpi.colosseum2k19.API.BackupApi;
import colosseum19.a300dpi.colosseum2k19.Adapters.ScoreGameListAdapter;
import colosseum19.a300dpi.colosseum2k19.Interfaces.DateInterface;
import colosseum19.a300dpi.colosseum2k19.R;

public class ScoresFragment extends Fragment{


    private String TAG = ScoresFragment.class.getSimpleName();

    private RecyclerView gameNameList;
    private ScoreGameListAdapter gameListAdapter;

    private Context ctx;
    private static ScoresFragment instance;

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
        ctx = view.getContext();

        gameNameList = view.findViewById(R.id.score_game_name_list);
        gameListAdapter = new ScoreGameListAdapter(getActivity());
        gameNameList.setAdapter(gameListAdapter);
        gameNameList.setLayoutManager(new LinearLayoutManager(getActivity()));

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

