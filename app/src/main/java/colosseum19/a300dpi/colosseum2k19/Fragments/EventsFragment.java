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

import butterknife.ButterKnife;
import colosseum19.a300dpi.colosseum2k19.Adapters.GreenAdapter;
import colosseum19.a300dpi.colosseum2k19.R;

public class EventsFragment extends Fragment {

    private static RecyclerView EventList ;
    private Context context;
    private GreenAdapter mAdapter;

    public EventsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_events,container,false);
        ButterKnife.bind(this,view);
        context = view.getContext();
        EventList = (RecyclerView) view.findViewById(R.id.recycler_event_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        EventList.setLayoutManager(layoutManager);
        EventList.setHasFixedSize(true);
        mAdapter = new GreenAdapter(context);
        EventList.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onResume() {
        Log.d("TAG", "onResume: Called");
        super.onResume();
        mAdapter = new GreenAdapter(context);
        EventList.setAdapter(mAdapter);
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
