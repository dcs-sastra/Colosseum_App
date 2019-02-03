package colosseum19.a300dpi.colosseum2k19.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

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
    public void onBindViewHolder(@NonNull ScoreGameHolder scoreGameHolder, int i) {
        scoreGameHolder.gameIcon.setImageDrawable(gameIcons.get(i));
        scoreGameHolder.gameName.setText(gameNames.get(i));
        scoreGameHolder.gameIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return gameIcons.size();
    }

    //callback containing score of specific game
    @Override
    public void callback(String queryGame) {

    }

    @Override
    public void setFixtureData(ArrayList<Fixture> data, FixtureGameListAdapter.GameHolder gameHolder) {

    }

    @Override
    public void setScoreData(ArrayList<Score> data, ScoreGameHolder gameHolder) {

    }


    public class ScoreGameHolder extends RecyclerView.ViewHolder {
        ImageView gameIcon,gameDropDown;
        TextView gameName;
        RecyclerView gameScoreList;

        public ScoreGameHolder(@NonNull View itemView) {
            super(itemView);
            gameIcon = itemView.findViewById(R.id.score_game_image);
            gameDropDown = itemView.findViewById(R.id.score_drop_down);
            gameName = itemView.findViewById(R.id.score_game_name);
            gameScoreList = itemView.findViewById(R.id.score_game_specific_score_list);
        }

        public void setRecyclerView(){

        }

        public void hideRecyclerView(){

        }
    }
}
