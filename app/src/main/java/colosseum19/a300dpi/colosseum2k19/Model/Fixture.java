package colosseum19.a300dpi.colosseum2k19.Model;

public class Fixture {

    private String id;
    private String event_name;
    private String game_name;
    private boolean isNextTime;
    private String roundNumChess;
    private String bestPhysiqueCategory;
    private String event_time;
    private String teamA;
    private String teamB;


    public Fixture() {
    }

    public Fixture(String id, String event_name, String game_name, boolean isNextTime,
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
