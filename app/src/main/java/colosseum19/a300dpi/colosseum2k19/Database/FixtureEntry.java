package colosseum19.a300dpi.colosseum2k19.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "fixtures")
public class FixtureEntry {

    @PrimaryKey(autoGenerate = true)
    private int idd;
    private String id;
    private String event_name;
    private String game_name;
    @ColumnInfo(name = "is_next_time")
    private boolean isNextTime;
    @ColumnInfo(name = "round_num_chess")
    private String roundNumChess;
    @ColumnInfo(name = "best_physique_category")
    private String bestPhysiqueCategory;
    private String event_time;
    private String teamA;
    private String teamB;

    @Ignore
    public FixtureEntry(String id, String event_name, String game_name, boolean isNextTime,
                        String roundNumChess, String bestPhysiqueCategory, String event_time, String teamA, String teamB) {
        this.id =  id;
        this.event_name = event_name;
        this.game_name = game_name;
        this.isNextTime = isNextTime;
        this.roundNumChess = roundNumChess;
        this.bestPhysiqueCategory = bestPhysiqueCategory;
        this.event_time = event_time;
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public FixtureEntry(int idd, String id, String event_name, String game_name, boolean isNextTime,
                        String roundNumChess, String bestPhysiqueCategory, String event_time, String teamA, String teamB) {
        this.idd = idd;
        this.id = id;
        this.event_name = event_name;
        this.game_name = game_name;
        this.isNextTime = isNextTime;
        this.roundNumChess = roundNumChess;
        this.bestPhysiqueCategory = bestPhysiqueCategory;
        this.event_time = event_time;
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public boolean isNextTime() {
        return isNextTime;
    }

    public void setNextTime(boolean nextTime) {
        isNextTime = nextTime;
    }

    public String getRoundNumChess() {
        return roundNumChess;
    }

    public void setRoundNumChess(String roundNumChess) {
        this.roundNumChess = roundNumChess;
    }

    public String getBestPhysiqueCategory() {
        return bestPhysiqueCategory;
    }

    public void setBestPhysiqueCategory(String bestPhysiqueCategory) {
        this.bestPhysiqueCategory = bestPhysiqueCategory;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }
}


