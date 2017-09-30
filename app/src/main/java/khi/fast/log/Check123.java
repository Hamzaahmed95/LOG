package khi.fast.log;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Hamza Ahmed on 25-Sep-17.
 */

public class Check123 extends AppCompatActivity {


    Dialog dialog;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout1;
    LinearLayout layout7;
    LinearLayout layout4;
    LinearLayout layout5;
    LinearLayout layout6;
    LinearLayout layout9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        layout2 = (LinearLayout) findViewById(R.id.lay2);
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Check123.this, CricketActivity.class);
                startActivity(i);
            }
        });

        layout3 = (LinearLayout) findViewById(R.id.lay3);
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Check123.this, BasketballActivity.class);
                startActivity(i);
            }
        });
        layout1 = (LinearLayout) findViewById(R.id.lay1);
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Check123.this, FutsalActivity.class);
                startActivity(i);
            }
        });
        layout7 = (LinearLayout) findViewById(R.id.lay7);
        layout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Check123.this, BadmintonActivity.class);
                startActivity(i);
            }
        });

        layout6 = (LinearLayout) findViewById(R.id.lay6);
        layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Check123.this, TableTennisActivity.class);
                startActivity(i);
            }
        });

        layout9 = (LinearLayout) findViewById(R.id.lay9);
        layout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Check123.this, FanFavouriteActivity.class);
                startActivity(i);
            }
        });
        layout4 = (LinearLayout) findViewById(R.id.lay4);
        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Check123.this, VolleyballActivity.class);
                startActivity(i);
            }
        });

        layout5 = (LinearLayout) findViewById(R.id.lay5);
        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Check123.this, ThrowballActivity.class);
                startActivity(i);
            }
        });

        if (isFirstTime()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay1).setVisibility(View.VISIBLE);
                }
            }, 100);


            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay2).setVisibility(View.VISIBLE);
                }
            }, 300);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay3).setVisibility(View.VISIBLE);
                }
            }, 500);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay9).setVisibility(View.VISIBLE);
                }
            }, 750);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay6).setVisibility(View.VISIBLE);
                }
            }, 1000);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay5).setVisibility(View.VISIBLE);
                }
            }, 1250);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay4).setVisibility(View.VISIBLE);
                }
            }, 1600);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay7).setVisibility(View.VISIBLE);
                }
            }, 1900);

        }
        else{
            findViewById(R.id.lay1).setVisibility(View.VISIBLE);
            findViewById(R.id.lay2).setVisibility(View.VISIBLE);
            findViewById(R.id.lay3).setVisibility(View.VISIBLE);
            findViewById(R.id.lay4).setVisibility(View.VISIBLE);
            findViewById(R.id.lay5).setVisibility(View.VISIBLE);
            findViewById(R.id.lay6).setVisibility(View.VISIBLE);
            findViewById(R.id.lay7).setVisibility(View.VISIBLE);
            findViewById(R.id.lay9).setVisibility(View.VISIBLE);
        }
    }


    private boolean isFirstTime()
    {
        SharedPreferences preferences = this.getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }
}
