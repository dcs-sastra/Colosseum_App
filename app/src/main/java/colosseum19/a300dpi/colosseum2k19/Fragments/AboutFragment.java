package colosseum19.a300dpi.colosseum2k19.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import colosseum19.a300dpi.colosseum2k19.R;
import colosseum19.a300dpi.colosseum2k19.Utilities.ConstantsStorage;

public class AboutFragment extends Fragment {

    private Context context;
    private ImageView dscLogo;

    public AboutFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_about, container, false);
        ButterKnife.bind(this, view);
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

    @OnClick(R.id.dsc_logo)
    public void onClickDSCLogo(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String url = ConstantsStorage.DSC_APP_URL;
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @OnClick(R.id.dpi_logo)
    public void onClickdpiLogo(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String url = ConstantsStorage.DPI_LINKEDIN_URL;
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @OnClick(R.id.dsc_insta)
    public void onClickdsc_insta(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String url = ConstantsStorage.DSC_INSTA_URL;
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @OnClick(R.id.dsc_linkedin)
    public void onClickdsc_linkedin(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String url = ConstantsStorage.DSC_LINKEDIN_URL;
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @OnClick(R.id.dsc_twitter)
    public void onClickdsc_twitter(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String url = ConstantsStorage.DSC_TWITTER_URL;
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
