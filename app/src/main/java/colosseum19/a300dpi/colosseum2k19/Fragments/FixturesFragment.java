package colosseum19.a300dpi.colosseum2k19.Fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import colosseum19.a300dpi.colosseum2k19.Adapters.FixtureGameListAdapter;
import colosseum19.a300dpi.colosseum2k19.R;

public class FixturesFragment extends Fragment{

    private static final String TAG = FixturesFragment.class.getSimpleName();

    private RecyclerView gameList;
    private FixtureGameListAdapter fixtureGameListAdapter;
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

        //recycler adapter for game names
        gameList = view.findViewById(R.id.fixtures_game_names_list);
        fixtureGameListAdapter = new FixtureGameListAdapter(getActivity());
        gameList.setAdapter(fixtureGameListAdapter);
        gameList.setLayoutManager(new LinearLayoutManager(getActivity()));
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


}

