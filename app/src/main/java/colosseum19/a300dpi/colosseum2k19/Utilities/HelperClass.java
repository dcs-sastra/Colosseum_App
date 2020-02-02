package colosseum19.a300dpi.colosseum2k19.Utilities;

import java.util.ArrayList;
import java.util.List;

import colosseum19.a300dpi.colosseum2k19.Database.FixtureEntry;
import colosseum19.a300dpi.colosseum2k19.Database.ScoreEntry;
import colosseum19.a300dpi.colosseum2k19.Model.Fixture;
import colosseum19.a300dpi.colosseum2k19.Model.Score;

public class HelperClass {

    public List<FixtureEntry> convertFixtureIntoFixtureEntry(List<Fixture> fixtures) {
        List<FixtureEntry> fixtureEntries = new ArrayList<>();
        for (Fixture fixture : fixtures) {
            FixtureEntry entry = new FixtureEntry(fixture.getId(), fixture.getEvent_name(), fixture.getGame_name(),
                    fixture.isNextTime(), fixture.getRoundNumChess(), fixture.getBestPhysiqueCategory(),
                    fixture.getEvent_time(), fixture.getTeamA(), fixture.getTeamB());
            fixtureEntries.add(entry);
        }

        return fixtureEntries;
    }

    public List<Fixture> convertFixtureEntryIntoFixture(List<FixtureEntry> fixtureEntries) {
        List<Fixture> fixtures = new ArrayList<>();
        for (FixtureEntry entry : fixtureEntries) {
            Fixture fixture = new Fixture();
            fixture.setId(entry.getId());
            fixture.setEvent_name(entry.getEvent_name());
            fixture.setGame_name(entry.getGame_name());
            fixture.setEvent_time(entry.getEvent_time());
            fixture.setNextTime(entry.isNextTime());
            fixture.setBestPhysiqueCategory(entry.getBestPhysiqueCategory());
            fixture.setRoundNumChess(entry.getRoundNumChess());
            fixture.setTeamA(entry.getTeamA());
            fixture.setTeamB(entry.getTeamA());
            fixtures.add(fixture);
        }
        return fixtures;
    }

    public List<ScoreEntry> convertScoreIntoScoreEntry(List<Score> scoreList) {
        List<ScoreEntry> scoreEntries = new ArrayList<>();
        for (Score score : scoreList) {
            ScoreEntry entry = new ScoreEntry(score.getEvent_name(), score.getGame_name(), score.getTeamA(),
                    score.getTeamB(), score.getRoundNumChess(), score.getBestPhysiqueCategory(),
                    score.getBestPhysiqueCollgeName(), score.getBestPhysiqueParticipantName(),
                    score.getBestPhysiquePositioin(), score.getWinner(), score.getScore_teamA(), score.getScore_teamB());
            scoreEntries.add(entry);
        }

        return scoreEntries;
    }

    public List<Score> convertScoreEntryIntoScore(List<ScoreEntry> scoreEntries) {

        List<Score> scores = new ArrayList<>();
        for (ScoreEntry entry : scoreEntries) {
            Score score = new Score(entry.getEvent_name(), entry.getGame_name(), entry.getTeamA(), entry.getTeamB(),
                    entry.getRoundNumChess(), entry.getBestPhysiqueCategory(), entry.getBestPhysiqueCollgeName(),
                    entry.getBestPhysiqueParticipantName(), entry.getBestPhysiquePositioin(), entry.getWinner(),
                    entry.getScore_teamA(), entry.getScore_teamB());
            scores.add(score);
        }
        return scores;
    }
}
