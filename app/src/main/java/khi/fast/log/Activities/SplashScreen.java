package khi.fast.log.Activities;

/**
 * Created by Hamza Ahmed on 25-Sep-17.
 */

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;

import khi.fast.log.R;
import khi.fast.log.Utils.Utils;


/**
 * Created by Hamza Ahmed on 17-Jul-17.
 */

public class SplashScreen extends Activity {

    ProgressBar mprogressBar;
    Utils utils;
    Animation anim1;
    ImageView appLogo;
    ObjectAnimator anim;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        initialization();
        launchingActivity();
    }

    public void initialization() {
        anim1 = AnimationUtils.loadAnimation(this, R.anim.anim_down);
        appLogo = (ImageView) findViewById(R.id.app_logo);
        appLogo.setAnimation(anim1);
        utils = new Utils(this);
        mprogressBar = (ProgressBar) findViewById(R.id.progressBar);
        anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
        anim.setDuration(4000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
        handler = new Handler();
    }

    public void launchingActivity() {

        if (utils.isFirstTime()) {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();

                }
            }, 3000);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(SplashScreen.this, LogOverviewActivity.class));
                    finish();

                }
            }, 0);

        }
    }

}