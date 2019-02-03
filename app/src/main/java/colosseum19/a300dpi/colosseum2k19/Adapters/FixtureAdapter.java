package colosseum19.a300dpi.colosseum2k19.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import colosseum19.a300dpi.colosseum2k19.Model.Fixture;
import colosseum19.a300dpi.colosseum2k19.R;


public class FixtureAdapter extends RecyclerView.Adapter<FixtureAdapter.FixtureHolder> {

    private Context mContext;
    private List<Fixture> fixtureList = new ArrayList<>();

    public class FixtureHolder extends RecyclerView.ViewHolder {
        
        private TextView txtName, txtA, txtB, txtTime;
        
        public FixtureHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.sub_name);
            txtA = itemView.findViewById(R.id.team_a);
            txtB = itemView.findViewById(R.id.team_b);
            txtTime = itemView.findViewById(R.id.time);
        }

        public void setDetails(Fixture fixture) {
            txtName.setText(fixture.getEvent_name());
            txtA.setText(fixture.getTeamA());
            txtB.setText(fixture.getTeamB());
            txtTime.setText(fixture.getEvent_time());
        }
    }


    @Override
    public int getItemCount() {
        return fixtureList.size();
    }

    @Override
    public void onBindViewHolder(FixtureHolder holder, int position) {
        Fixture fixture = fixtureList.get(position);
        holder.setDetails(fixture);
    }

    @NonNull
    @Override
    public FixtureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.fixtures_sub_item,parent, false);
        return new FixtureHolder(view);
    }

    public void setFixture(List<Fixture> fixtureArrayList) {
        this.fixtureList = fixtureArrayList;
    }

    public void addEvent(Fixture fixture){
        if (fixtureList != null)
            fixtureList.add(fixture);
    }

    public void setData(ArrayList<Fixture> data){
        this.fixtureList = data;
        notifyDataSetChanged();
    }

    public int numberOfevents(){
        if (fixtureList != null)
            return fixtureList.size()-1;
        else return 0;
    }

}

