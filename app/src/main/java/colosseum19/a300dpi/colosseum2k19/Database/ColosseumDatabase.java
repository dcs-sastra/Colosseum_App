package colosseum19.a300dpi.colosseum2k19.Database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

@Database(entities = {FixtureEntry.class, ScoreEntry.class}, version = 1, exportSchema = false)
public abstract class ColosseumDatabase extends RoomDatabase {

    private static final String LOG_TAG = ColosseumDatabase.class.getSimpleName();
    private static final Object OBJECT = new Object();
    private static final String DATABASE_NAME = "colosseum19";
    private static ColosseumDatabase colosseumDatabase;

    public static ColosseumDatabase getInstance(Context context){

        if (colosseumDatabase == null){
            synchronized (OBJECT){
                Log.d(LOG_TAG, "Creating new Database");
                colosseumDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        ColosseumDatabase.class,
                        ColosseumDatabase.DATABASE_NAME).build();
            }
        }

        return colosseumDatabase;
    }

    public abstract ColosseumDao colosseumDao();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
