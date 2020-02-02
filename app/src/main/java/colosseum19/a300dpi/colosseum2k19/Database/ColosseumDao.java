package colosseum19.a300dpi.colosseum2k19.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ColosseumDao {

    @Query("SELECT * FROM fixtures")
    LiveData<List<FixtureEntry>> loadAllFixtures();

    @Query("SELECT * FROM scores")
    LiveData<List<ScoreEntry>> loadAllSCores();

    @Query("SELECT * FROM fixtures WHERE game_name = :game_name")
    LiveData<List<FixtureEntry>> loadFixtures(String game_name);

    @Query("SELECT * FROM scores WHERE game_name = :game_name")
    LiveData<List<ScoreEntry>> loadScores(String game_name);

    @Insert
    void insertFixture(FixtureEntry fixtureEntry);

    @Insert
    void insertScore(ScoreEntry scoreEntry);

    @Insert
    void insertFixtures(List<FixtureEntry> fixtureEntryList);

    @Insert
    void insertScores(List<ScoreEntry> scoreEntries);

    @Query("DELETE FROM fixtures")
    void deleteFixtureTable();

    @Query("DELETE FROM scores")
    void deleteScoreTable();
}
