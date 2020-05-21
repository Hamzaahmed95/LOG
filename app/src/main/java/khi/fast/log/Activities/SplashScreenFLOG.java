package khi.fast.log.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import khi.fast.log.R;

/**
 * Created by Hamza Ahmed on 05-Oct-17.
 */

public class SplashScreenFLOG extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreenflog);
        Handler handler = new Handler();




        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreenFLOG.this, FlogMainActivity.class));
                finish();

            }
        }, 3000);

    }

}
