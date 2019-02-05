package colosseum19.a300dpi.colosseum2k19.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import colosseum19.a300dpi.colosseum2k19.Fragments.FixturesFragment;
import colosseum19.a300dpi.colosseum2k19.Interfaces.CallbackInterface;
import colosseum19.a300dpi.colosseum2k19.Model.Fixture;
import colosseum19.a300dpi.colosseum2k19.Model.Score;
import colosseum19.a300dpi.colosseum2k19.R;

public class FixtureGameListAdapter extends RecyclerView.Adapter<FixtureGameListAdapter.GameHolder> implements CallbackInterface{

    private ArrayList<String> gameNames = new ArrayList<>();
    private ArrayList<Drawable> gameIcons = new ArrayList<>();
    private Context ctx;
    private ProgressDialog progressDialog;
    private FixtureGameListAdapter callBack;

    public FixtureGameListAdapter(Context ctx){
        this.ctx = ctx;
        this.callBack = this;

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
    public GameHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fixture_item_row,viewGroup,false);
        return new GameHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final GameHolder gameHolder, int i) {
        final String gameName = gameNames.get(i);

        gameHolder.gameName.setText(gameName);
        gameHolder.gameIcon.setImageDrawable(gameIcons.get(i));
        gameHolder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                FixturesFragment.getInstance().getGameFixtures(getQueryWord(gameName),callBack,gameHolder);
            }
        });
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull GameHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.hideRecyclerView();
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

    @Override
    public int getItemCount() {
        return gameIcons.size();
    }

    //call back containing data from querying
    @Override
    public void callback(String queryGame) {

    }

    @Override
    public void setFixtureData(ArrayList<Fixture> data, GameHolder gameHolder) {
        gameHolder.setRecyclerView(data);
    }

    @Override
    public void setScoreData(ArrayList<Score> data, ScoreGameListAdapter.ScoreGameHolder gameHolder) {

    }


    //view holder
    public class GameHolder extends RecyclerView.ViewHolder{
        ConstraintLayout rootLayout;
        TextView gameName;
        ImageView gameIcon;
        ImageView dropDown;
        RecyclerView specificGameList;
        FixtureAdapter adapter;

        public GameHolder(@NonNull View itemView) {
            super(itemView);
            rootLayout = itemView.findViewById(R.id.root_layout);
            gameName = itemView.findViewById(R.id.game_name);
            gameIcon = itemView.findViewById(R.id.game_image);
            dropDown = itemView.findViewById(R.id.drop_down);
            specificGameList = itemView.findViewById(R.id.game_specific_fixture_list);
            adapter = new FixtureAdapter(ctx);
            specificGameList.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            specificGameList.setAdapter(adapter);

        }

        public void setRecyclerView(ArrayList<Fixture>data){
            specificGameList.setVisibility(View.VISIBLE);
            adapter.setData(data);
            progressDialog.cancel();
        }
        public void hideRecyclerView(){
            specificGameList.setVisibility(View.GONE);
        }

    }


}
