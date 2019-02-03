package colosseum19.a300dpi.colosseum2k19.Interfaces;

import java.util.ArrayList;

import colosseum19.a300dpi.colosseum2k19.Adapters.GameListAdapter;
import colosseum19.a300dpi.colosseum2k19.Model.Fixture;

public interface CallbackInterface {

     void callback(String queryGame);
     void setData(ArrayList<Fixture> data, GameListAdapter.GameHolder gameHolder);
}
