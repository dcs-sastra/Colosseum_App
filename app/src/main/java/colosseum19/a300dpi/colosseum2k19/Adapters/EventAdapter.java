package colosseum19.a300dpi.colosseum2k19.Adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import colosseum19.a300dpi.colosseum2k19.EventRules;
import colosseum19.a300dpi.colosseum2k19.R;
import colosseum19.a300dpi.colosseum2k19.Utilities.ConstantsStorage;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewholder> {
    String TAG = EventAdapter.class.getSimpleName();

    Context ctx;
    /*int[] imageList = {R.drawable.badminton_new,
                        R.drawable.basketball_new,
                        R.drawable.physique_new,
                        R.drawable.chess_new,
                        R.drawable.football_new,
                        R.drawable.handball_new,
                        R.drawable.table_tennis_new,
                        R.drawable.tennis_new,
                        R.drawable.volleyball_new};*/
    String[] imageList = {
            ConstantsStorage.BADMINTON_URL,
            ConstantsStorage.BASKETBALL_URL,
            ConstantsStorage.BESTPHYSIQUE_URL,
            ConstantsStorage.CHESS_URL,
            ConstantsStorage.FOOTBALL_URL,
            ConstantsStorage.HANDBALL_URL,
            ConstantsStorage.TABLETENNIS_URL,
            ConstantsStorage.TENNIS_URL,
            ConstantsStorage.VOLLEYBALL_URL
    };

    public EventAdapter(Context ctx){
        this.ctx = ctx;
    }
    @NonNull
    @Override
    public EventViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View v = LayoutInflater.from(ctx).inflate(R.layout.new_event_item, null, false);
        return new EventViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewholder eventViewholder, int i) {
        Glide.with(ctx).load(imageList[i]).into(eventViewholder.gameImage);
    }

    @Override
    public int getItemCount() {
        return imageList.length;
    }



    public class EventViewholder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView gameImage;

        public EventViewholder(@NonNull View itemView) {
            super(itemView);
            gameImage = itemView.findViewById(R.id.game_image);
            gameImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent i = new Intent(ctx, EventRules.class);
            i.putExtra("pos", (int) position);
            Log.d(TAG, "onClick: id is="+ position);
            ctx.startActivity(i);
        }
    }
}
