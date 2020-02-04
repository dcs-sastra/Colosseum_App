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
import colosseum19.a300dpi.colosseum2k19.Adapters.FixtureGameListAdapter;
import colosseum19.a300dpi.colosseum2k19.Interfaces.DateInterface;
import colosseum19.a300dpi.colosseum2k19.R;

public class FixturesFragment extends Fragment implements DateInterface {

    private static final String TAG = FixturesFragment.class.getSimpleName();

    private RecyclerView gameList;
    private FixtureGameListAdapter fixtureGameListAdapter;
    private static FixturesFragment instance;
    private BackupApi backupApi;
    private Context ctx;

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
        ctx = view.getContext();

        backupApi = new BackupApi();
        backupApi.getDate(view.getContext(), this);

        //recycler adapter for game names
        gameList = view.findViewById(R.id.fixtures_game_names_list);
        fixtureGameListAdapter = new FixtureGameListAdapter(getActivity());
        gameList.setAdapter(fixtureGameListAdapter);
        gameList.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void setDate(String date, boolean isSuccess) {
        if(isSuccess){
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(getString(R.string.shared_pref_name), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.day_key), date);
            editor.apply();
        }else{
            Toast.makeText(ctx, "Failed to get current date", Toast.LENGTH_SHORT).show();
        }
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

