package colosseum19.a300dpi.colosseum2k19.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import colosseum19.a300dpi.colosseum2k19.Adapters.EventAdapter;
import colosseum19.a300dpi.colosseum2k19.Adapters.GreenAdapter;
import colosseum19.a300dpi.colosseum2k19.R;

public class EventsFragment extends Fragment {

    private static RecyclerView eventList ;
    private Context context;
    private EventAdapter adapter;
    public EventsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_events,container,false);
        ButterKnife.bind(this,view);
        context = view.getContext();
        eventList = view.findViewById(R.id.recycler_event_view);
        adapter = new EventAdapter(context);
        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        eventList.setLayoutManager(sglm);
        eventList.setHasFixedSize(true);
        eventList.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        Log.d("TAG", "onResume: Called");
        adapter = new EventAdapter(context);
        eventList.setAdapter(adapter);
        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        eventList.setLayoutManager(sglm);
        super.onResume();
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
