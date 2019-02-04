package colosseum19.a300dpi.colosseum2k19.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import colosseum19.a300dpi.colosseum2k19.Fragments.ScoresFragment;
import colosseum19.a300dpi.colosseum2k19.Interfaces.CallbackInterface;
import colosseum19.a300dpi.colosseum2k19.Model.Fixture;
import colosseum19.a300dpi.colosseum2k19.Model.Score;
import colosseum19.a300dpi.colosseum2k19.R;

public class ScoreGameListAdapter extends RecyclerView.Adapter<ScoreGameListAdapter.ScoreGameHolder> implements CallbackInterface {

    private ArrayList<String> gameNames = new ArrayList<>();
    private ArrayList<Drawable> gameIcons = new ArrayList<>();
    private Context ctx;
    private ProgressDialog progressDialog;
    private ScoreGameListAdapter callback;

    public ScoreGameListAdapter(Context ctx){
        this.ctx = ctx;
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        callback = this;

        gameNames.add(ctx.getString(R.string.basketball));
        gameNames.add(ctx.getString(R.string.badminton));
        gameNames.add(ctx.getString(R.string.best_physique));
        gameNames.add(ctx.getString(R.string.chess));
        gameNames.add(ctx.getString(R.string.football));
        gameNames.add(ctx.getString(R.string.handball));
        gameNames.add(ctx.getString(R.string.table_tennis));
        gameNames.add(ctx.getString(R.string.tennis));
        gameNames.add(ctx.getString(R.string.volleyball));

        gameIcons.add(ctx.getDrawable(R.drawable.icon_basketball));
        gameIcons.add(ctx.getDrawable(R.drawable.icon_badminton));
        gameIcons.add(ctx.getDrawable(R.drawable.icon_best_phy));
        gameIcons.add(ctx.getDrawable(R.drawable.icon_chess));
        gameIcons.add(ctx.getDrawable(R.drawable.icon_football));
        gameIcons.add(ctx.getDrawable(R.drawable.icon_handball));
        gameIcons.add(ctx.getDrawable(R.drawable.icon_table_tennis));
        gameIcons.add(ctx.getDrawable(R.drawable.icon_tennis));
        gameIcons.add(ctx.getDrawable(R.drawable.icon_volleyball));
    }

    @NonNull
    @Override
    public ScoreGameHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.score_item_row,viewGroup,false);
        return new ScoreGameHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ScoreGameHolder scoreGameHolder, final int i) {
        scoreGameHolder.gameIcon.setImageDrawable(gameIcons.get(i));
        scoreGameHolder.gameName.setText(gameNames.get(i));
        scoreGameHolder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                ScoresFragment.getInstance().getGameScores(getQueryWord(gameNames.get(i)),callback,scoreGameHolder);
            }
        });
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ScoreGameHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.hideRecyclerView();
    }

    @Override
    public int getItemCount() {
        return gameIcons.size();
    }

    private String getQueryWord(String gameName){
        if(gameName.equals(ctx.getString(R.string.basketball))){
            return ctx.getString(R.string.basketball_query);
        }else if(gameName.equals(ctx.getString(R.string.volleyball))){
            return ctx.getString(R.string.volleyball_query);
        }else if(gameName.equals(ctx.getString(R.string.handball))){
            return ctx.getString(R.string.handball_query);
        }else if(gameName.equals(ctx.getString(R.string.football))){
            return ctx.getString(R.string.football_query);
        }else{
            return gameName;
        }
    }

    //callback containing score of specific game
    @Override
    public void setScoreData(ArrayList<Score> data, ScoreGameHolder gameHolder) {
        if(data == null){
            Toast.makeText(ctx,"Scores not updated",Toast.LENGTH_SHORT).show();
        }else{
            gameHolder.setRecyclerView(data);
        }

    }


    public class ScoreGameHolder extends RecyclerView.ViewHolder {
        ImageView gameIcon,gameDropDown;
        TextView gameName;
        RecyclerView gameScoreList;
        ScoreAdapter adapter;
        CardView rootLayout;
        public ScoreGameHolder(@NonNull View itemView) {
            super(itemView);
            gameIcon = itemView.findViewById(R.id.score_game_image);
            gameDropDown = itemView.findViewById(R.id.score_drop_down);
            gameName = itemView.findViewById(R.id.score_game_name);
            gameScoreList = itemView.findViewById(R.id.score_game_specific_score_list);
            rootLayout = itemView.findViewById(R.id.score_root_layout);
            adapter = new ScoreAdapter();
            gameScoreList.setAdapter(adapter);
            gameScoreList.setLayoutManager(new LinearLayoutManager(ctx));
        }

        public void setRecyclerView(ArrayList<Score>data){
            gameScoreList.setVisibility(View.VISIBLE);
            adapter.setScores(data);
            progressDialog.cancel();
        }

        public void hideRecyclerView(){
            gameScoreList.setVisibility(View.GONE);
        }
    }

    @Override
    public void callback(String queryGame) {

    }

    @Override
    public void setFixtureData(ArrayList<Fixture> data, FixtureGameListAdapter.GameHolder gameHolder) {

    }
}
