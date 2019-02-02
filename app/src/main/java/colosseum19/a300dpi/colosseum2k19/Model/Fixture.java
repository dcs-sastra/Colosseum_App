package colosseum19.a300dpi.colosseum2k19.Model;

public class Fixture {

    private String event_name;
    private String event_time;
    private String teamA;
    private String teamB;

    public Fixture() {
    }

    public Fixture(String event_name, String event_time, String teamA, String teamB) {
        this.event_name = event_name;
        this.event_time = event_time;
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
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
