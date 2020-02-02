package colosseum19.a300dpi.colosseum2k19.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.annotation.Nullable;

import colosseum19.a300dpi.colosseum2k19.Database.ColosseumDatabase;

public class ColosseumViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private ColosseumDatabase colosseumDatabase;
    private String game_name;

    public ColosseumViewModelFactory(ColosseumDatabase colosseumDatabase, String game_name) {
        this.colosseumDatabase = colosseumDatabase;
        this.game_name = game_name;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ColosseumViewModel(colosseumDatabase, game_name);
    }
}
