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

    private Context ctx;
    private List<Fixture> fixtureList = new ArrayList<>();

    public FixtureAdapter(Context ctx){
        this.ctx = ctx;
    }

    public class FixtureHolder extends RecyclerView.ViewHolder {
        
        private TextView txtName, txtA, txtB, txtTime;
        private TextView chessRound,physiqueCategory;
        private TextView versusText;

        public FixtureHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.sub_name);
            txtA = itemView.findViewById(R.id.team_a);
            txtB = itemView.findViewById(R.id.team_b);
            txtTime = itemView.findViewById(R.id.time);
            chessRound = itemView.findViewById(R.id.chess_round);
            physiqueCategory = itemView.findViewById(R.id.physique_category);
            versusText = itemView.findViewById(R.id.vs_text);
        }

        public void setDetails(Fixture fixture) {
            txtName.setText(fixture.getEvent_name());
            txtA.setText(fixture.getTeamA());
            txtB.setText(fixture.getTeamB());
            txtTime.setText(fixture.getEvent_time());
            if(fixture.getGame_name().equals(ctx.getString(R.string.best_physique))){
                txtA.setVisibility(View.GONE);
                versusText.setVisibility(View.GONE);
                txtB.setVisibility(View.GONE);
                physiqueCategory.setVisibility(View.VISIBLE);
                physiqueCategory.setText(fixture.getBestPhysiqueCategory());
            }
            if(fixture.getGame_name().equals(ctx.getString(R.string.chess))){
                chessRound.setVisibility(View.VISIBLE);
                chessRound.setText(fixture.getRoundNumChess());
            }
            if(fixture.isNextTime()){
                txtTime.setText(ctx.getString(R.string.to_be_updated));
            }
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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fixtures_sub_item,parent, false);
        return new FixtureHolder(view);
    }

    public void setFixture(List<Fixture> fixtureArrayList) {
        this.fixtureList = fixtureArrayList;
    }

    public void addEvent(Fixture fixture){
        if (fixtureList != null)
            fixtureList.add(fixture);
    }

    public void setData(List<Fixture> data){
        this.fixtureList = data;
        notifyDataSetChanged();
    }

    public int numberOfevents(){
        if (fixtureList != null)
            return fixtureList.size()-1;
        else return 0;
    }

}

