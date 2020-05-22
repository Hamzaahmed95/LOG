package khi.fast.log.Activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import khi.fast.log.R;


public class SplashBack extends AppCompatActivity {


    ProgressBar mprogressBar;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashback);
        initialization();
        startingActivity();
    }

    private void initialization(){
        mprogressBar = (ProgressBar) findViewById(R.id.progressBar);
        handler = new Handler();

    }
    private void startingActivity(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashBack.this, LogOverviewActivity.class));
                finish();

            }
        }, 3000);
    }
}
