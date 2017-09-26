package khi.fast.log;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Hamza Ahmed on 25-Sep-17.
 */

public class Check123 extends AppCompatActivity {



    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                //findViewById(R.id.layout1).setVisibility(View.GONE);
                findViewById(R.id.lay1).setVisibility(View.VISIBLE);
            }
        }, 300);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                //findViewById(R.id.layout1).setVisibility(View.GONE);
                findViewById(R.id.lay2).setVisibility(View.VISIBLE);
            }
        }, 600);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                //findViewById(R.id.layout1).setVisibility(View.GONE);
                findViewById(R.id.lay3).setVisibility(View.VISIBLE);
            }
        }, 900);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                //findViewById(R.id.layout1).setVisibility(View.GONE);
                findViewById(R.id.lay9).setVisibility(View.VISIBLE);
            }
        }, 1200);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                //findViewById(R.id.layout1).setVisibility(View.GONE);
                findViewById(R.id.lay6).setVisibility(View.VISIBLE);
            }
        }, 1500);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                //findViewById(R.id.layout1).setVisibility(View.GONE);
                findViewById(R.id.lay5).setVisibility(View.VISIBLE);
            }
        }, 1800);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                //findViewById(R.id.layout1).setVisibility(View.GONE);
                findViewById(R.id.lay4).setVisibility(View.VISIBLE);
            }
        }, 2100);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                //findViewById(R.id.layout1).setVisibility(View.GONE);
                findViewById(R.id.lay7).setVisibility(View.VISIBLE);
            }
        }, 2400);
    }
}
