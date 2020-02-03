package colosseum19.a300dpi.colosseum2k19.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import colosseum19.a300dpi.colosseum2k19.FixtureListActivity;
import colosseum19.a300dpi.colosseum2k19.R;

public class FixtureGameListAdapter extends RecyclerView.Adapter<FixtureGameListAdapter.GameHolder>{

    private ArrayList<String> gameNames = new ArrayList<>();
    private ArrayList<Drawable> gameIcons = new ArrayList<>();
    private Context ctx;
    private ProgressDialog progressDialog;
    private FixtureGameListAdapter callBack;
    private String selectedGameName = "";

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
                selectedGameName = getQueryWord(gameName);
                gameHolder.displayDays();
            }
        });
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull GameHolder holder) {
        super.onViewDetachedFromWindow(holder);
        //holder.hideRecyclerView();
        holder.hideAll();
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

    //view holder
    public class GameHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ConstraintLayout rootLayout;
        TextView gameName, dayOne, dayTwo, dayThree;
        ImageView gameIcon;
        ImageView dropDown;


        public GameHolder(@NonNull View itemView) {
            super(itemView);
            rootLayout = itemView.findViewById(R.id.root_layout);
            gameName = itemView.findViewById(R.id.game_name);
            gameIcon = itemView.findViewById(R.id.game_image);
            dropDown = itemView.findViewById(R.id.drop_down);
            dayOne = itemView.findViewById(R.id.day_one);
            dayOne.setOnClickListener(this);
            dayTwo = itemView.findViewById(R.id.day_two);
            dayTwo.setOnClickListener(this);
            dayThree = itemView.findViewById(R.id.day_three);
            dayThree.setOnClickListener(this);
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
            Intent i = new Intent(ctx, FixtureListActivity.class);
            i.putExtra(ctx.getString(R.string.game_name), selectedGameName);
            switch(v.getId()){
                case R.id.day_one:
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
