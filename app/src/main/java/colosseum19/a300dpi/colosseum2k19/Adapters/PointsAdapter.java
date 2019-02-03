package colosseum19.a300dpi.colosseum2k19.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import colosseum19.a300dpi.colosseum2k19.Model.Score;
import colosseum19.a300dpi.colosseum2k19.R;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.PointsHolder> {

    private Context mContext;
    private List<Score> points;

    public class PointsHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtA, txtB, txtScore, txtWinner;
        public PointsHolder(View itemView) {
            super(itemView);
            /*txtName = itemView.findViewById(R.id.txtName);
            txtA = itemView.findViewById(R.id.txtA);
            txtB = itemView.findViewById(R.id.txtB);
            txtScore = itemView.findViewById(R.id.txtScore);
            txtWinner = itemView.findViewById(R.id.txtWinner);*/
        }

        public void setDetails(Score planet) {
            txtName.setText(planet.getEvent_name());
            txtA.setText(planet.getTeamA());
            txtB.setText(planet.getTeamB());
            txtScore.setText(String.valueOf(planet.getScore_teamA()));
            //txtWinner.setText(planet.get());
        }
    }

    @Override
    public int getItemCount() {
        return points.size();
    }

    @Override
    public void onBindViewHolder(PointsHolder holder, int position) {
        Score planet = points.get(position);
        holder.setDetails(planet);
    }

    @Override
    public PointsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.score_sub_item,parent, false);
        return new PointsHolder(view);
    }

    public void setScores(List<Score> scoresList) {
        this.points = scoresList;
    }
    public void addScores(Score scores) {
        if (points != null){
            this.points.add(scores);
        }
    }
    public int numberOfevents(){
        if (points != null)
            return points.size()-1;
        else return 0;
    }

}

