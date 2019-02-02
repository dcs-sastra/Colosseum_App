package colosseum19.a300dpi.colosseum2k19.Model;

public class Score {

    private String event_name;
    private String teamA;
    private String teamB;
    private String winner;
    private float score_teamA;
    private float score_teamB;

    public Score() {
    }

    public Score(String event_name, String teamA, String teamB, String winner, float score_teamA, float score_teamB) {
        this.event_name = event_name;
        this.teamA = teamA;
        this.teamB = teamB;
        this.winner = winner;
        this.score_teamA = score_teamA;
        this.score_teamB = score_teamB;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
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

    public String iswinner() {
        return winner;
    }

    public void setwinner(String winner) {
        this.winner = winner;
    }

    public float getScore_teamA() {
        return score_teamA;
    }

    public void setScore_teamA(float score_teamA) {
        this.score_teamA = score_teamA;
    }

    public float getScore_teamB() {
        return score_teamB;
    }

    public void setScore_teamB(float score_teamB) {
        this.score_teamB = score_teamB;
    }
}
