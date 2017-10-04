package khi.fast.log;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Hamza Ahmed on 04-Oct-17.
 */

public class FantacyLOGActivity extends AppCompatActivity {


    private Button pickTeam;
    private Button platinum;
    private Button gold;
    private Button silver;
    private LinearLayout mainTeams;
    private ImageView stags;
    private ImageView dragons;
    private ImageView jaguars;
    private ImageView pythons;
    private ImageView hunters;
    private ImageView falcons;
    private ImageView dires;
    private String teamName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fantacy_log);
        pickTeam=(Button)findViewById(R.id.pickATeam);
        platinum=(Button)findViewById(R.id.platinum);
        gold=(Button)findViewById(R.id.gold);
        silver=(Button)findViewById(R.id.silver);
        mainTeams=(LinearLayout)findViewById(R.id.mainTeams);

        stags=(ImageView)findViewById(R.id.stags);
        stags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamName="stags";
                platinum.setVisibility(View.VISIBLE);
                silver.setVisibility(View.VISIBLE);
                gold.setVisibility(View.VISIBLE);
                mainTeams.setVisibility(View.GONE);

            }
        });
        dragons=(ImageView)findViewById(R.id.dragons);
        dragons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamName="dragons";
                platinum.setVisibility(View.VISIBLE);
                silver.setVisibility(View.VISIBLE);
                gold.setVisibility(View.VISIBLE);
                mainTeams.setVisibility(View.GONE);
            }
        });
        jaguars=(ImageView)findViewById(R.id.jaguars);
        jaguars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamName="jaguars";
                platinum.setVisibility(View.VISIBLE);
                silver.setVisibility(View.VISIBLE);
                gold.setVisibility(View.VISIBLE);
                mainTeams.setVisibility(View.GONE);

            }
        });
        pythons=(ImageView)findViewById(R.id.pythons);
        pythons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamName="pythons";
                platinum.setVisibility(View.VISIBLE);
                silver.setVisibility(View.VISIBLE);
                gold.setVisibility(View.VISIBLE);
                mainTeams.setVisibility(View.GONE);

            }
        });

        hunters=(ImageView)findViewById(R.id.hunters);
        hunters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamName="hunters";
                platinum.setVisibility(View.VISIBLE);
                silver.setVisibility(View.VISIBLE);
                gold.setVisibility(View.VISIBLE);
                mainTeams.setVisibility(View.GONE);

            }
        });
        falcons=(ImageView)findViewById(R.id.falcons);
        falcons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamName="falcons";
                platinum.setVisibility(View.VISIBLE);
                silver.setVisibility(View.VISIBLE);
                gold.setVisibility(View.VISIBLE);
                mainTeams.setVisibility(View.GONE);
            }
        });
        dires=(ImageView)findViewById(R.id.dires);
        dires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamName="dires";
                platinum.setVisibility(View.VISIBLE);
                silver.setVisibility(View.VISIBLE);
                gold.setVisibility(View.VISIBLE);
                mainTeams.setVisibility(View.GONE);
            }
        });

        pickTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainTeams.setVisibility(View.VISIBLE);
                pickTeam.setVisibility(View.GONE);
            }
        });

        platinum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FantacyLOGActivity.this,PlatinumPlayers.class);
                startActivity(i);
            }
        });

        gold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent i = new Intent(FantacyLOGActivity.this,GoldPlayers.class);
             startActivity(i);
            }
        });

        silver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FantacyLOGActivity.this,SilverPlayers.class);
                startActivity(i);
            }
        });
    }
}
