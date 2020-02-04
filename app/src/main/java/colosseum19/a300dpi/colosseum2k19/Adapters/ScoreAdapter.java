package colosseum19.a300dpi.colosseum2k19.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import colosseum19.a300dpi.colosseum2k19.API.BackupApi;
import colosseum19.a300dpi.colosseum2k19.Interfaces.CallbackInterface;
import colosseum19.a300dpi.colosseum2k19.Model.Fixture;
import colosseum19.a300dpi.colosseum2k19.Model.Score;
import colosseum19.a300dpi.colosseum2k19.R;
import colosseum19.a300dpi.colosseum2k19.ScoreListActivity;


public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreHolder> implements CallbackInterface {

    private Context ctx;
    private ArrayList<Score> points = new ArrayList<>();
    String gameName;
    int day;
    ProgressDialog progressDialog;
    private static String TAG = ScoreAdapter.class.getSimpleName();

    public ScoreAdapter(Context ctx, String gameName, int day, boolean isBackup) {
        this.ctx = ctx;
        this.gameName = gameName;
        this.day = day;
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        if(!isBackup){
            Log.d(TAG, "Getting data from Firebase");
            ((ScoreListActivity) ctx).getGameScores(this.gameName, this.day, this);
        }else{
            Log.d(TAG, "Getting data from backup server");
            BackupApi backupApi = new BackupApi();
            backupApi.getScores(ctx, this.gameName, this.day, this);
        }
    }

    @Override
    public ScoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_sub_item, parent, false);
        return new ScoreHolder(view);
    }

    @Override
    public void onBindViewHolder(ScoreHolder holder, int position) {
        Score score = points.get(position);
        holder.setDetails(score);
    }

    @Override
    public int getItemCount() {
        return points.size();
    }

    //only for fixtures
    @Override
    public void setFixtureData(ArrayList<Fixture> data, boolean isEmpty) {}

    @Override
    public void setScoreData(ArrayList<Score> data, boolean isEmpty) {
        progressDialog.cancel();
        if(!isEmpty){
            points = data;
            notifyDataSetChanged();
        }else{
            Toast.makeText(ctx, "Score data not available.", Toast.LENGTH_SHORT).show();
        }
    }

    //view holder for score
    public class ScoreHolder extends RecyclerView.ViewHolder {
        TextView gameName, teamA, teamB;
        TextView teamaScoreHeader, teambScoreHeader, teamaScoreValue, teambScoreValue;
        TextView physiqueText, chessRound,scoreHeader;
        TextView bestPhysiquePos,versusText,bestPhysiqueName;

        ScoreHolder(View itemView) {
            super(itemView);
            gameName = itemView.findViewById(R.id.score_sub_name);
            teamA = itemView.findViewById(R.id.score_team_a);
            teamB = itemView.findViewById(R.id.score_team_b);
            teamaScoreHeader = itemView.findViewById(R.id.team_a_score_header);
            teambScoreHeader = itemView.findViewById(R.id.team_b_score_header);
            teamaScoreValue = itemView.findViewById(R.id.team_a_score_value);
            teambScoreValue = itemView.findViewById(R.id.team_b_score_value);
            physiqueText = itemView.findViewById(R.id.score_physique_category);
            chessRound = itemView.findViewById(R.id.score_chess_round);
            scoreHeader = itemView.findViewById(R.id.score_header);
            bestPhysiquePos = itemView.findViewById(R.id.best_physique_position);
            versusText = itemView.findViewById(R.id.vs_text);
            bestPhysiqueName = itemView.findViewById(R.id.best_physique_name);
        }

        public void setDetails(Score score) {
            gameName.setText(score.getEvent_name());
            teamA.setText(score.getTeamA());
            teamB.setText(score.getTeamB());
            teamaScoreHeader.setText(score.getTeamA());
            teambScoreHeader.setText(score.getTeamB());
            teamaScoreValue.setText(String.valueOf(score.getScore_teamA()));
            teambScoreValue.setText(String.valueOf(score.getScore_teamB()));

            if(score.getGame_name().equals(ctx.getString(R.string.badminton)) ||
                    score.getGame_name().equals(ctx.getString(R.string.volleyball_query)) ||
                    score.getGame_name().equals(ctx.getString(R.string.tennis)) ||
                    score.getGame_name().equals(ctx.getString(R.string.table_tennis))){
                scoreHeader.setText(ctx.getString(R.string.set));
            }else{
                scoreHeader.setText(ctx.getString(R.string.score));
            }

            //for chess game
            if (score.getGame_name().equals(ctx.getString(R.string.chess))) {
                chessRound.setVisibility(View.VISIBLE);
                chessRound.setText(score.getRoundNumChess());
            }
            //for best physique
            if(score.getGame_name().equals(ctx.getString(R.string.best_physique))){
                physiqueText.setVisibility(View.VISIBLE);
                physiqueText.setText(score.getBestPhysiqueCategory());
                bestPhysiquePos.setVisibility(View.VISIBLE);
                bestPhysiquePos.setText(score.getBestPhysiquePositioin());
                teamA.setText(score.getBestPhysiqueCollgeName());
                bestPhysiqueName.setText(score.getBestPhysiqueParticipantName());
                bestPhysiqueName.setVisibility(View.VISIBLE);
                scoreHeader.setVisibility(View.GONE);
                teamaScoreHeader.setVisibility(View.GONE);
                teambScoreHeader.setVisibility(View.GONE);
                teamaScoreValue.setVisibility(View.GONE);
                teambScoreValue.setVisibility(View.GONE);
                versusText.setVisibility(View.GONE);
            }
        }
    }
}

