package colosseum19.a300dpi.colosseum2k19.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import colosseum19.a300dpi.colosseum2k19.Database.ColosseumDatabase;

public class ColosseumViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private ColosseumDatabase colosseumDatabase;

    public ColosseumViewModelFactory(ColosseumDatabase colosseumDatabase) {
        this.colosseumDatabase = colosseumDatabase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new ColosseumViewModel(colosseumDatabase);
    }
}
