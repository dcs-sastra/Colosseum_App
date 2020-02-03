package colosseum19.a300dpi.colosseum2k19.Fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.ButterKnife;
import colosseum19.a300dpi.colosseum2k19.R;

public class AboutFragment extends Fragment {

    private Context context;
    private ImageView dscLogo;

    public AboutFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_about,container,false);
        ButterKnife.bind(this,view);
        context = view.getContext();
        dscLogo = view.findViewById(R.id.dsc_logo);
        dscLogo.setImageResource(R.drawable.dsc_logo);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
