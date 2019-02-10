package colosseum19.a300dpi.colosseum2k19.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import colosseum19.a300dpi.colosseum2k19.Database.FixtureEntry;
import colosseum19.a300dpi.colosseum2k19.Database.ScoreEntry;
import colosseum19.a300dpi.colosseum2k19.Model.Fixture;
import colosseum19.a300dpi.colosseum2k19.Model.Score;

public class HelperClass {

    public List<FixtureEntry> convertFixtureIntoFixtureEntry(List<Fixture> fixtures){
        List<FixtureEntry> fixtureEntries = new ArrayList<>();
        for (Fixture fixture: fixtures){
            FixtureEntry entry = new FixtureEntry(fixture.getId(),fixture.getEvent_name(), fixture.getGame_name(),
                    fixture.isNextTime(), fixture.getRoundNumChess(), fixture.getBestPhysiqueCategory(),
                    fixture.getEvent_time(), fixture.getTeamA(), fixture.getTeamB());
            fixtureEntries.add(entry);
        }

        return fixtureEntries;
    }

    public List<Fixture> convertFixtureEntryIntoFixture(List<FixtureEntry> fixtureEntries){
        List<Fixture> fixtures = new ArrayList<>();
        for(FixtureEntry entry: fixtureEntries){
            Fixture fixture = new Fixture();
            fixture.setId(entry.getId());
            fixture.setEvent_name(entry.getEvent_name());
            fixture.setGame_name(entry.getGame_name());
            fixture.setEvent_time(entry.getEvent_time());
            fixture.setNextTime(entry.isNextTime());
            fixture.setBestPhysiqueCategory(entry.getBestPhysiqueCategory());
            fixture.setRoundNumChess(entry.getRoundNumChess());
            fixture.setTeamA(entry.getTeamA());
            fixture.setTeamB(entry.getTeamB());
            fixtures.add(fixture);
        }
        return fixtures;
    }

    public List<ScoreEntry> convertScoreIntoScoreEntry(List<Score> scoreList) {
        List<ScoreEntry> scoreEntries = new ArrayList<>();
        for (Score score: scoreList){
            ScoreEntry entry = new ScoreEntry(score.getEvent_name(), score.getGame_name(), score.getTeamA(),
                    score.getTeamB(), score.getRoundNumChess(), score.getBestPhysiqueCategory(),
                    score.getBestPhysiqueCollgeName(), score.getBestPhysiqueParticipantName(),
                    score.getBestPhysiquePositioin(), score.getWinner(),score.getScore_teamA(), score.getScore_teamB());
            scoreEntries.add(entry);
        }

        return scoreEntries;
    }

    public List<Score> convertScoreEntryIntoScore(List<ScoreEntry> scoreEntries){

        List<Score> scores = new ArrayList<>();
        for (ScoreEntry entry: scoreEntries){
            Score score = new Score(entry.getEvent_name(), entry.getGame_name(), entry.getTeamA(), entry.getTeamB(),
                    entry.getRoundNumChess(), entry.getBestPhysiqueCategory(), entry.getBestPhysiqueCollgeName(),
                    entry.getBestPhysiqueParticipantName(), entry.getBestPhysiquePositioin(), entry.getWinner(),
                    entry.getScore_teamA(), entry.getScore_teamB());
            scores.add(score);
        }
        return  scores;
    }

    public Date getDateFromString(String dateString){
        String[] dateList= dateString.split(" ");
        String[] monList = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        List<String> monL = Arrays.asList(monList);
        int mon = monL.indexOf(dateList[1]);
        int dd = Integer.parseInt(dateList[2]);
        int hr = Integer.parseInt(dateList[3].split(":")[0]);
        int min = Integer.parseInt(dateList[3].split(":")[1]);
        int ss = Integer.parseInt(dateList[3].split(":")[2]);
        int year = Integer.parseInt(dateList[5]);
        return new Date(year,mon,dd,hr,min,ss);

    }

}
