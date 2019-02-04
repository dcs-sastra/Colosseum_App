package colosseum19.a300dpi.colosseum2k19.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import colosseum19.a300dpi.colosseum2k19.Model.Score;
import colosseum19.a300dpi.colosseum2k19.R;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreHolder> {

    private Context mContext;
    private ArrayList<Score> points = new ArrayList<>();

    public ScoreAdapter() {

    }

    @Override
    public int getItemCount() {
        return points.size();
    }

    @Override
    public void onBindViewHolder(ScoreHolder holder, int position) {
        Score score = points.get(position);
        holder.setDetails(score);
    }

    @Override
    public ScoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.score_sub_item, parent, false);
        return new ScoreHolder(view);
    }

    public void setScores(ArrayList<Score> scoresList) {
        this.points = scoresList;
        notifyDataSetChanged();
    }

    //view holder for score
    public class ScoreHolder extends RecyclerView.ViewHolder {
        TextView gameName, teamA, teamB, winnerText;
        TextView teamaScoreHeader, teambScoreHeader, teamaScoreValue, teambScoreValue;
        TextView physiqueText, chessRound;

        ScoreHolder(View itemView) {
            super(itemView);
            gameName = itemView.findViewById(R.id.score_sub_name);
            teamA = itemView.findViewById(R.id.score_team_a);
            teamB = itemView.findViewById(R.id.score_team_b);
            winnerText = itemView.findViewById(R.id.winner_text);
            teamaScoreHeader = itemView.findViewById(R.id.team_a_score_header);
            teambScoreHeader = itemView.findViewById(R.id.team_b_score_header);
            teamaScoreValue = itemView.findViewById(R.id.team_a_score_value);
            teambScoreValue = itemView.findViewById(R.id.team_b_score_value);
            physiqueText = itemView.findViewById(R.id.score_physique_category);
            chessRound = itemView.findViewById(R.id.score_chess_round);
        }

        public void setDetails(Score score) {
            gameName.setText(score.getEvent_name());
            teamA.setText(score.getTeamA());
            teamB.setText(score.getTeamB());
            winnerText.setText(score.getWinner());
            teamaScoreHeader.setText(score.getTeamA());
            teambScoreHeader.setText(score.getTeamB());
            teamaScoreValue.setText(String.valueOf(score.getScore_teamA()));
            teambScoreValue.setText(String.valueOf(score.getScore_teamB()));
            if (score.getRoundNumChess() != null) {
                chessRound.setVisibility(View.VISIBLE);
                chessRound.setText(score.getRoundNumChess());
            }
            if (score.getBestPhysiqueCategory() != null) {
                physiqueText.setVisibility(View.VISIBLE);
                physiqueText.setText(score.getBestPhysiqueCategory());
            }
        }
    }
}

