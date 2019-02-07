package colosseum19.a300dpi.colosseum2k19.Interfaces;

import java.util.ArrayList;

import colosseum19.a300dpi.colosseum2k19.Adapters.FixtureGameListAdapter;
import colosseum19.a300dpi.colosseum2k19.Adapters.ScoreGameListAdapter;
import colosseum19.a300dpi.colosseum2k19.Model.Fixture;
import colosseum19.a300dpi.colosseum2k19.Model.Score;

public interface CallbackInterface {

     void callback(String queryGame);
     void setFixtureData(ArrayList<Fixture> data, FixtureGameListAdapter.GameHolder gameHolder, boolean isEmpty);
     void setScoreData(ArrayList<Score> data, ScoreGameListAdapter.ScoreGameHolder gameHolder, boolean isEmpty);
}
