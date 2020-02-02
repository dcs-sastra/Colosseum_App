package colosseum19.a300dpi.colosseum2k19.Interfaces;

import java.util.ArrayList;
import java.util.List;

import colosseum19.a300dpi.colosseum2k19.Adapters.FixtureGameListAdapter;
import colosseum19.a300dpi.colosseum2k19.Adapters.ScoreGameListAdapter;
import colosseum19.a300dpi.colosseum2k19.Model.Fixture;
import colosseum19.a300dpi.colosseum2k19.Model.Score;

public interface CallbackInterface {

     void callback(String queryGame);

    void setFixtureData(List<Fixture> data, FixtureGameListAdapter.GameHolder gameHolder, boolean isEmpty);

    void setScoreData(List<Score> data, ScoreGameListAdapter.ScoreGameHolder gameHolder, boolean isEmpty);
}
