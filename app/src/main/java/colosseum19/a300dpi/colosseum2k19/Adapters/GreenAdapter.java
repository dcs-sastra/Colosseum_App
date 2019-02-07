package colosseum19.a300dpi.colosseum2k19.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import colosseum19.a300dpi.colosseum2k19.EventRules;
import colosseum19.a300dpi.colosseum2k19.R;

public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

    private static final String TAG = GreenAdapter.class.getSimpleName();
    private static Integer noOfEvents ;
    private ViewGroup viewGroup;
    private Context context;
    private Boolean rules=false;
    private int id;
    private static final String[] events={"badminton_men_women.png",
            "basketball_men_and_women.png",
            "best_physique_men.png",
            "chess_men_and_women.png",
            "football_men.png",
            "handball_men.png",
            "table_tennis_men_women.png",
            "tennis_men_women.png",
            "volleyball_men_women.png"   };
    private String[] all_rules;

    GreenAdapter.NumberViewHolder viewHolder;
    View view;

    public GreenAdapter(Context context) {              //Only Main activity calls this constructor
        noOfEvents=9;
        this.context=context;
    }

    public GreenAdapter(Context context, int id, String[] s) {
        noOfEvents=s.length;
        this.context=context;
        this.rules=true;
        this.id=id;
        all_rules=s;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        context = viewGroup.getContext();
        boolean shouldAttachToParentImmediately = false;
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutIdForListItem;
        if(rules)
            layoutIdForListItem =R.layout.recycler_rule;
        else
            layoutIdForListItem = R.layout.recycler_event;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        viewHolder = new GreenAdapter.NumberViewHolder(view);
        return viewHolder;
    }

    public NumberViewHolder returnViewHolder(View view)
    {
        return new GreenAdapter.NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, int position) {
        numberViewHolder.bind(position,numberViewHolder);
    }

    @Override
    public int getItemCount() {
        return noOfEvents;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView img, icon_img;
        private TextView rule;

        public NumberViewHolder(View itemView) {
            super(itemView);
            if(!rules){
                img = itemView.findViewById(R.id.event_img);
                icon_img = itemView.findViewById(R.id.event_icon_img);
                img.setOnClickListener(this);
            }
        }

        @SuppressLint("ResourceAsColor")
        public void bind(int position, NumberViewHolder holder) {
            if(rules){
                rule=(TextView)itemView.findViewById(R.id.event_text);
                rule.setText(all_rules[position]);


            }else {
                img=(ImageView)itemView.findViewById(R.id.event_img);
                icon_img = itemView.findViewById(R.id.event_icon_img);
                switch (position) {
                    case 0:
                        loadImage(R.drawable.badminton_men_women,img);
                        loadImage(R.drawable.icon_badminton, icon_img);
                        //img.setImageDrawable(context.getResources().getDrawable(R.drawable.badminton_men_women));
                        break;
                    case 1:
                        loadImage(R.drawable.basketball_men_and_women,img);
                        loadImage(R.drawable.icon_basketball, icon_img);
                        //img.setImageDrawable(context.getResources().getDrawable(R.drawable.basketball_men_and_women));
                        break;
                    case 2:
                        loadImage(R.drawable.best_physique_men,img);
                        loadImage(R.drawable.icon_best_phy, icon_img);
                        //img.setImageDrawable(context.getResources().getDrawable(R.drawable.best_physique_men));
                        break;
                    case 3:
                        loadImage(R.drawable.chess_men_and_women,img);
                        loadImage(R.drawable.icon_chess, icon_img);
                        //img.setImageDrawable(context.getResources().getDrawable(R.drawable.chess_men_and_women));
                        break;
                    case 4:
                        loadImage(R.drawable.football_men,img);
                        loadImage(R.drawable.icon_football, icon_img);
                        //img.setImageDrawable(context.getResources().getDrawable(R.drawable.football_men));
                        break;
                    case 5:
                        loadImage(R.drawable.handball_men,img);
                        loadImage(R.drawable.icon_handball, icon_img);
                        //img.setImageDrawable(context.getResources().getDrawable(R.drawable.handball_men));
                        break;
                    case 6:
                        loadImage(R.drawable.table_tennis_men_women, img);
                        loadImage(R.drawable.icon_table_tennis, icon_img);
                        //img.setImageDrawable(context.getResources().getDrawable(R.drawable.table_tennis_men_women));
                        break;
                    case 7:
                        loadImage(R.drawable.tennis_men_women,img);
                        loadImage(R.drawable.icon_tennis, icon_img);
                        //img.setImageDrawable(context.getResources().getDrawable(R.drawable.tennis_men_women));
                        break;
                    case 8:
                        loadImage(R.drawable.volleyball_men_women,img);
                        loadImage(R.drawable.icon_volleyball, icon_img);
                        //img.setImageDrawable(context.getResources().getDrawable(R.drawable.volleyball_men_women));
                        break;

                }
            }

        };

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent i = new Intent(context, EventRules.class);
            i.putExtra("pos", (int) position);
            Log.d(TAG, "onClick: id is="+ position);
            context.startActivity(i);
        }
    }

    private void loadImage(int id, ImageView imageView){
        RequestOptions options = new RequestOptions()
                .error(R.drawable.logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(context)
                .load(id)
                .apply(options)
                .into(imageView);

        Animation a1 = AnimationUtils.loadAnimation(context, R.anim.slide_from_left_items);
        imageView.setAnimation(a1);

    }

}
