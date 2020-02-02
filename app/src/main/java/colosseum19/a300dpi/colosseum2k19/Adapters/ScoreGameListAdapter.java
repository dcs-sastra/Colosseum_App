package colosseum19.a300dpi.colosseum2k19.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import colosseum19.a300dpi.colosseum2k19.Fragments.ScoresFragment;
import colosseum19.a300dpi.colosseum2k19.Interfaces.CallbackInterface;
import colosseum19.a300dpi.colosseum2k19.Model.Fixture;
import colosseum19.a300dpi.colosseum2k19.Model.Score;
import colosseum19.a300dpi.colosseum2k19.R;
import colosseum19.a300dpi.colosseum2k19.ScoreListActivity;

public class ScoreGameListAdapter extends RecyclerView.Adapter<ScoreGameListAdapter.ScoreGameHolder>{

    private ArrayList<String> gameNames = new ArrayList<>();
    private ArrayList<Drawable> gameIcons = new ArrayList<>();
    private Context ctx;
    private String selectedGame;

    public ScoreGameListAdapter(Context ctx){
        this.ctx = ctx;

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
        selectedGame = gameNames.get(i);
        scoreGameHolder.gameIcon.setImageDrawable(gameIcons.get(i));
        scoreGameHolder.gameName.setText(gameNames.get(i));
        scoreGameHolder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGame = getQueryWord(selectedGame);
                scoreGameHolder.displayDays();
            }
        });
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ScoreGameHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.hideAll();
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


    public class ScoreGameHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView gameIcon,gameDropDown;
        TextView gameName, dayOne, dayTwo, dayThree;
        CardView rootLayout;

        public ScoreGameHolder(@NonNull View itemView) {
            super(itemView);
            gameIcon = itemView.findViewById(R.id.score_game_image);
            gameDropDown = itemView.findViewById(R.id.score_drop_down);
            gameName = itemView.findViewById(R.id.score_game_name);
            dayOne = itemView.findViewById(R.id.day_one_score);
            dayOne.setOnClickListener(this);
            dayTwo = itemView.findViewById(R.id.day_two_score);
            dayTwo.setOnClickListener(this);
            dayThree = itemView.findViewById(R.id.day_three_score);
            dayThree.setOnClickListener(this);
            rootLayout = itemView.findViewById(R.id.score_root_layout);
        }

        private boolean isNextDay(String date){
            Date currentDateTime = Calendar.getInstance().getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
            try {
                currentDateTime = dateFormat.parse(dateFormat.format(currentDateTime));
                Date dateObj = dateFormat.parse(date);

                if(currentDateTime.compareTo(dateObj) >= 0){
                    return true;
                }else{
                    return false;
                }
            }catch(Exception e){
                Log.d("DATE_CHECK", "Error: "+e.toString());
            }

            return false;
        }

        private void displayDays(){
            if(isNextDay("2020-01-06")){
                dayOne.setVisibility(View.VISIBLE);
            }
            if(isNextDay("2020-01-07")){
                dayOne.setVisibility(View.VISIBLE);
                dayTwo.setVisibility(View.VISIBLE);
            }
            if(isNextDay("2020-01-08")){
                dayOne.setVisibility(View.VISIBLE);
                dayTwo.setVisibility(View.VISIBLE);
                dayThree.setVisibility(View.VISIBLE);
            }
        }

        private void hideAll(){
            dayOne.setVisibility(View.GONE);
            dayTwo.setVisibility(View.GONE);
            dayThree.setVisibility(View.GONE);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(ctx, ScoreListActivity.class);
            i.putExtra(ctx.getString(R.string.game_name), selectedGame);
            switch(v.getId()){
                case R.id.day_one_score:
                    i.putExtra(ctx.getString(R.string.day_key), 1);
                    break;
                case R.id.day_two:
                    i.putExtra(ctx.getString(R.string.day_key), 2);
                    break;
                case R.id.day_three:
                    i.putExtra(ctx.getString(R.string.day_key), 3);
                    break;
            }
            ctx.startActivity(i);
        }
    }


}
