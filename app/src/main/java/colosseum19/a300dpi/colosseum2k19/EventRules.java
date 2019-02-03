package colosseum19.a300dpi.colosseum2k19;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import colosseum19.a300dpi.colosseum2k19.Adapters.GreenAdapter;

public class EventRules extends AppCompatActivity {

    private static RecyclerView EventList ;
    private GreenAdapter mAdapter;
    private Boolean rules=true;
    private int position;
    private String[] r;
    private String contact="Null";
    private int[] prize;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_event_rules);
        position=(int)(getIntent().getIntExtra("pos",0));
        TextView contactact_tv=findViewById(R.id.contact_details);
        ImageView img=findViewById(R.id.event_title_image);
        TextView prize1=findViewById(R.id.prize_1_details);
        TextView prize2=findViewById(R.id.prize_2_details);
        TextView prize3=findViewById(R.id.prize_3_details);
//        "badminton_men_women.png",
//                "basketball_men_and_women.png",
//                "best_physique_men.png",
//                "chess_men_and_women.png",
//                "football_men.png",
//                "handball_men.png",
//                "table_tennis_men_women.png",
//                "tennis_men_women.png",
//                "volleyball_men_women.png"
        switch (position){
            case 0:     //Badmitton
                img.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.badminton_men_women));
                r=new String[]{ "Tournament will be conducted on knockout/ league basis" ,
                        "Games will be based on 21 Points, 3 Sets rally System" ,
                        "3 Matches per game (Singles, Doubles, Reverse singles)" ,
                        "Maximum of 4 players per team" ,
                        "Players should bring their own shuttlecocks (Yonex AS2)" ,
                        "Registration on first - come - first serve basis" };
                contact= "Bharath - " +
                        "9952579573\n" +
                        "Sandhiya";
                prize=new int[]{3000,2000,500};
                break;
            case 1:   //BasketBall
                img.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.basketball_men_and_women));
                r=new String[]{ "Tournament will be conducted on knockout/league basis" ,
                        "There will be 4 quarters of 10 minutes each" ,
                        "Maximum of 12 players per team" ,
                        "Registration on first - come - first serve basis"
                };
                contact= "Surya - " +
                        "9087839219\n" +
                        "Soundharya";
                prize=new int[]{7000,4000,2000};
                break;
            case 2:
                img.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.best_physique_men));
                r=new String[]{" Total of 9 weight categories " +
                        "(below 55, 60, 65, 70, 75, 80, 85, 90 and above 90 kg)" ,
                        "Maximum of 1 O participants per team and 2 participants in each category" ,
                        "Registration on first - come - first serve basis"};
                contact= "Pavaleesh - " +
                        "8973180401";
                prize=new int[]{750,500,250};
                break;
            case 3:
                img.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.chess_men_and_women));
                r=new String[]{"Tournament will be a Team Championship under Swiss format and FIDE rules" +
                        "Maximum of 5 players per team (4+1)" ,
                        "Players should bring their own board and chess clock" ,
                        "Each round will be of 2 hours (1 hour per player)" ,
                        "Matches will be held separately for men and women" ,
                        "Registration on first - come - first serve basis"};
                contact= "Karthick - " +
                        "7338986997\n" +
                        "Kowshika";
                prize=new int[]{3000,2000,500};
                break;
            case 4:
                img.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.football_men));
                r=new String[]{"Tournament will be conducted on knockout/league basis " ,
                        "Each half will be 30 minutes (30-5-30) " ,
                        "Maximum of 16 players per team " ,
                        "Maximum of 3 substitutes per team " ,
                        "Proper kit is necessary (shoes and shin pads are must) " ,
                        "In case of tie, winner will be decided by penalty shootout " ,
                        "Registration on first - come - first serve basis"};
                contact= "Karthikeyan - " +
                        "8608683180";
                prize=new int[]{7000,4000,2000};
                break;
            case 5:
                img.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.handball_men));
                r=new String[]{"Tournament will be conducted on knockout/league basis" ,
                        "Maximum of 12 players per team (7+5)" ,
                        "Each half will be 20 minutes (20-5-20)" ,
                        "Registration on first - come - first serve basis"};
                contact= "Mithun\n" +
                        "9566819616";
                prize=new int[]{7000,4000,2000};
                break;
            case 6:
                img.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.table_tennis_men_women));
                r=new String[]{" Matches will be conducted on ABC-XYZ format. Knockout/League basis" ,
                        "Maximum of 4 and minimum of 3 players per team" ,
                        "Matches will be played for 11 points, 5 sets per match" ,
                        "Matches will be played as follows A vs X, B vs Y, C vs Z" ,
                        "In case neither team wins all 3 matches, A vs Y, B vs X will be played (1st team to win 3 matches win)" ,
                        "Order of preference should be given to referee before the match" ,
                        "Registration on first - come - first serve basis"};
                contact= "Ashuthosh - " +
                        "9445824769\n" +
                        "Manasa";
                prize=new int[]{3000,2000,500};
                break;
            case 7:
                img.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.tennis_men_women));
                r=new String[]{"Tournament will be conducted on knockout/league basis" ,
                        "All matches will be best of 15 games" ,
                        "Semifinals and finals will be best of 3 sets" ,
                        "Team should consist of singles, doubles and reverse singles" ,
                        "Players should bring their own tennis balls (Slazenger)" ,
                        "All matches will be played on synthetic court" ,
                        "Registration on first - come - first serve basis"};
                contact= "Vivek - " +
                        "9962102609\n" +
                        "Harshitha";
                prize=new int[]{3000,2000,500};
                break;
            case 8:
                img.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.volleyball_men_women));
                r=new String[]{"Tournament will be conducted on knockout/league basis" ,
                        "Maximum of 12 players per team" ,
                        "Current FIVB rules will be followed" ,
                        "All matches will be conducted on best of 3 format" ,
                        "Registration on first - come - first serve basis"};
                contact= "Srinivas - 9488259436\n" +
                        "Sakthibala";
                prize=new int[]{7000,4000,2000};
                break;
        }



        contactact_tv.setText(contact);
        prize1.setText("Winner\n"+prize[0]);
        prize2.setText("Runner\n"+prize[1]);
        prize3.setText("Third\n"+prize[2]);

        Context context=getApplicationContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        EventList =  findViewById(R.id.recycler_rule_view);
        EventList.setLayoutManager(layoutManager);
        EventList.setHasFixedSize(true);
        mAdapter = new GreenAdapter(context,position,r);
        EventList.setAdapter(mAdapter);


    }

}
