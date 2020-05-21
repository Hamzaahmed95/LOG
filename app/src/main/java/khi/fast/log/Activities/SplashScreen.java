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

    ProgressBar progressBar;
    Utils utils;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        initialization();
        launchingActivity();
    }

    public void initialization() {

        utils = new Utils(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
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
            }, 2500);
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