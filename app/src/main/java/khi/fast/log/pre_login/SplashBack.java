package khi.fast.log.pre_login;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import khi.fast.log.R;
import khi.fast.log.activities.LogOverviewActivity;
import khi.fast.log.utils.Utils;


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

                Utils.startingActivity(SplashBack.this, LogOverviewActivity.class,"","",true);

            }
        }, 3000);
    }
}
