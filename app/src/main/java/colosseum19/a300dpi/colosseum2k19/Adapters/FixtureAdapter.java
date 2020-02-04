package colosseum19.a300dpi.colosseum2k19.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import colosseum19.a300dpi.colosseum2k19.API.BackupApi;
import colosseum19.a300dpi.colosseum2k19.FixtureListActivity;
import colosseum19.a300dpi.colosseum2k19.Interfaces.CallbackInterface;
import colosseum19.a300dpi.colosseum2k19.Model.Fixture;
import colosseum19.a300dpi.colosseum2k19.Model.Score;
import colosseum19.a300dpi.colosseum2k19.R;

import static com.google.firebase.inappmessaging.internal.Logging.TAG;


public class FixtureAdapter extends RecyclerView.Adapter<FixtureAdapter.FixtureHolder> implements CallbackInterface {

    private Context ctx;
    private List<Fixture> fixtureList = new ArrayList<>();
    private String gameName;
    private int day;
    ProgressDialog progressDialog;
    private static String TAG = FixtureAdapter.class.getSimpleName();

    public FixtureAdapter(Context ctx, String gameName, int day, boolean isbackup){
        this.ctx = ctx;
        this.gameName = gameName;
        this.day = day;
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Log.d(TAG, "game name: "+this.gameName);
        Log.d(TAG, "day: "+this.day);

        if(!isbackup){
            Log.d(TAG, "Getting data from Firebase");
            ((FixtureListActivity) ctx).getGameFixturesFirebase(this.gameName, this.day, this);
        }else{
            Log.d(TAG, "Getting data from backup server");
            BackupApi backupApi = new BackupApi();
            backupApi.getFixtures(ctx, this.gameName, this.day, this);
        }

    }


    @NonNull
    @Override
    public FixtureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fixtures_sub_item,parent, false);
        return new FixtureHolder(view);
    }

    @Override
    public void onBindViewHolder(FixtureHolder holder, int position) {
        Fixture fixture = fixtureList.get(position);
        holder.setDetails(fixture);
    }

    @Override
    public int getItemCount() {
        return fixtureList.size();
    }



    @Override
    public void setFixtureData(ArrayList<Fixture> data, boolean isEmpty) {
        progressDialog.cancel();
        if(!isEmpty){
            Log.d("DATA TEST", data.toString());
            this.fixtureList = data;
            notifyDataSetChanged();
        }else{
            Toast.makeText(ctx, "Fixtures not posted.", Toast.LENGTH_SHORT).show();
        }
    }

    //only to be used in displaying scores
    @Override
    public void setScoreData(ArrayList<Score> data, boolean isEmpty) {

    }


    //View Holder for this recycler view
    public class FixtureHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtA, txtB, txtTime;
        private TextView chessRound,physiqueCategory;
        private TextView versusText;

        private FixtureHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.sub_name);
            txtA = itemView.findViewById(R.id.team_a);
            txtB = itemView.findViewById(R.id.team_b);
            txtTime = itemView.findViewById(R.id.time);
            chessRound = itemView.findViewById(R.id.chess_round);
            physiqueCategory = itemView.findViewById(R.id.physique_category);
            versusText = itemView.findViewById(R.id.vs_text);
        }

        private void setDetails(Fixture fixture) {
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

}

