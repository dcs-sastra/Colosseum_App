package colosseum19.a300dpi.colosseum2k19.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import colosseum19.a300dpi.colosseum2k19.R;
import colosseum19.a300dpi.colosseum2k19.ScoreListActivity;

public class ScoreGameListAdapter extends RecyclerView.Adapter<ScoreGameListAdapter.ScoreGameHolder>{

    private ArrayList<String> gameNames = new ArrayList<>();
    //private ArrayList<Drawable> gameIcons = new ArrayList<>();
    int[] gameIcons = {
            R.drawable.icon_basketball,
            R.drawable.icon_badminton,
            R.drawable.icon_best_phy,
            R.drawable.icon_chess,
            R.drawable.icon_football,
            R.drawable.icon_handball,
            R.drawable.icon_table_tennis,
            R.drawable.icon_tennis,
            R.drawable.icon_volleyball
    };
    private Context ctx;
    Intent intent;
    private String selectedGame;
    private String day;

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

        intent = new Intent(ctx, ScoreListActivity.class);
        //get current day from shared preference
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(ctx.getString(R.string.shared_pref_name), Context.MODE_PRIVATE);
        day = sharedPreferences.getString(ctx.getString(R.string.day_key), "1");
    }

    @NonNull
    @Override
    public ScoreGameHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.score_item_row,viewGroup,false);
        return new ScoreGameHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ScoreGameHolder scoreGameHolder, final int i) {
        final String gameName = gameNames.get(i);

        scoreGameHolder.gameIcon.setImageResource(gameIcons[i]);
        scoreGameHolder.gameName.setText(gameName);
        scoreGameHolder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGame = getQueryWord(scoreGameHolder.gameName.getText().toString());
                scoreGameHolder.displayDays();
            }
        });

        scoreGameHolder.dayOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DAY ONE", "onClick: "+getQueryWord(gameName));
                intent.putExtra(ctx.getString(R.string.game_name), getQueryWord(gameName));
                intent.putExtra(ctx.getString(R.string.day_key), 1);
                ctx.startActivity(intent);
            }
        });
        scoreGameHolder.dayTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DAY TWO", "onClick: "+getQueryWord(gameName));
                intent.putExtra(ctx.getString(R.string.game_name), getQueryWord(gameName));
                intent.putExtra(ctx.getString(R.string.day_key), 2);
                ctx.startActivity(intent);
            }
        });
        scoreGameHolder.dayThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DAY THREE", "onClick: "+getQueryWord(gameName));
                intent.putExtra(ctx.getString(R.string.game_name), getQueryWord(gameName));
                intent.putExtra(ctx.getString(R.string.day_key), 3);
                ctx.startActivity(intent);
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
        return gameIcons.length;
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


    public class ScoreGameHolder extends RecyclerView.ViewHolder{
        ImageView gameIcon,gameDropDown;
        TextView gameName, dayOne, dayTwo, dayThree;
        ConstraintLayout rootLayout;

        public ScoreGameHolder(@NonNull View itemView) {
            super(itemView);
            gameIcon = itemView.findViewById(R.id.game_image);
            gameDropDown = itemView.findViewById(R.id.drop_down);
            gameName = itemView.findViewById(R.id.game_name);
            dayOne = itemView.findViewById(R.id.day_one);
            dayTwo = itemView.findViewById(R.id.day_two);
            dayThree = itemView.findViewById(R.id.day_three);
            rootLayout = itemView.findViewById(R.id.root_layout);
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
            //show only current day
            //show only current day
            switch(day){
                case "1":
                    dayOne.setVisibility(View.VISIBLE);
                    dayTwo.setVisibility(View.GONE);
                    dayThree.setVisibility(View.GONE);
                    break;
                case "2":
                    dayOne.setVisibility(View.VISIBLE);
                    dayTwo.setVisibility(View.VISIBLE);
                    dayThree.setVisibility(View.GONE);
                    break;
                case "3":
                    dayOne.setVisibility(View.VISIBLE);
                    dayTwo.setVisibility(View.VISIBLE);
                    dayThree.setVisibility(View.VISIBLE);
                    break;

            }

        }

        private void hideAll(){
            dayOne.setVisibility(View.GONE);
            dayTwo.setVisibility(View.GONE);
            dayThree.setVisibility(View.GONE);
        }

    }


}
