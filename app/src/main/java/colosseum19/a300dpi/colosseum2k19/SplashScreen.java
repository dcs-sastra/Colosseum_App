package colosseum19.a300dpi.colosseum2k19;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

public class SplashScreen extends AppCompatActivity {
    int SPLASH_TIME_OUT = 1500;

    ImageView iv_logo, iv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        iv_logo  = findViewById(R.id.iv_logo);
        iv_text = findViewById(R.id.iv_txt);

        Animation a = AnimationUtils.loadAnimation(this, R.anim.anim_textview);
        iv_logo.startAnimation(a);
        iv_text.startAnimation(a);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_from_right , R.anim.slide_to_left);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
