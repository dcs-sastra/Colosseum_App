package colosseum19.a300dpi.colosseum2k19.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import colosseum19.a300dpi.colosseum2k19.Database.ColosseumDatabase;
import colosseum19.a300dpi.colosseum2k19.Database.FixtureEntry;
import colosseum19.a300dpi.colosseum2k19.Database.ScoreEntry;

public class ColosseumViewModel extends ViewModel {

    private static String TAG = ColosseumViewModel.class.getSimpleName();

    private ColosseumDatabase colosseumDatabase;
    private LiveData<List<FixtureEntry>> fixtureEntries;
    private LiveData<List<ScoreEntry>> scoreEntries;

    ColosseumViewModel(ColosseumDatabase colosseumDatabase, String game_name) {
        this.colosseumDatabase = colosseumDatabase;
        Log.d("Querying from Database", "ColosseumViewModel");
    }

    public LiveData<List<FixtureEntry>> getFixtureEntries(String game_name) {
        fixtureEntries = colosseumDatabase.colosseumDao().loadFixtures(game_name);
        Log.d(TAG, "getFixtureEntries");
        return fixtureEntries;
    }

    public LiveData<List<ScoreEntry>> getScoreEntries(String game_name) {
        scoreEntries = colosseumDatabase.colosseumDao().loadScores(game_name);
        Log.d(TAG, "getScoreEntries");
        return scoreEntries;
    }

    public void loadFixturesIntoDatabase(ArrayList<FixtureEntry> fixtureEntries) {
        colosseumDatabase.colosseumDao().insertFixtures(fixtureEntries);
    }

    public void deleteFixtureTable() {
        colosseumDatabase.colosseumDao().deleteFixtureTable();
    }
}
