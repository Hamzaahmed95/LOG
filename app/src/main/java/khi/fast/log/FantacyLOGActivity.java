package khi.fast.log;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
    private TextView tagline;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fantacy_log);
        tagline=(TextView)findViewById(R.id.tagline);
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
                showDialog();
                mainTeams.setVisibility(View.GONE);
                tagline.setVisibility(View.GONE);
                

            }
        });
        dragons=(ImageView)findViewById(R.id.dragons);
        dragons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamName="dragons";
                showDialog();
                tagline.setVisibility(View.GONE);
                mainTeams.setVisibility(View.GONE);
            }
        });
        jaguars=(ImageView)findViewById(R.id.jaguars);
        jaguars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamName="jaguars";
                showDialog();
                tagline.setVisibility(View.GONE);
                mainTeams.setVisibility(View.GONE);

            }
        });
        pythons=(ImageView)findViewById(R.id.pythons);
        pythons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamName="pythons";
                showDialog();
                tagline.setVisibility(View.GONE);
                mainTeams.setVisibility(View.GONE);

            }
        });

        hunters=(ImageView)findViewById(R.id.hunters);
        hunters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamName="hunters";
                showDialog();
                tagline.setVisibility(View.GONE);
                mainTeams.setVisibility(View.GONE);

            }
        });
        falcons=(ImageView)findViewById(R.id.falcons);
        falcons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamName="falcons";
                showDialog();
                tagline.setVisibility(View.GONE);
                mainTeams.setVisibility(View.GONE);
            }
        });
        dires=(ImageView)findViewById(R.id.dires);
        dires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamName="dires";
                showDialog();
                tagline.setVisibility(View.GONE);
                mainTeams.setVisibility(View.GONE);
            }
        });

        pickTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainTeams.setVisibility(View.VISIBLE);
                tagline.setVisibility(View.VISIBLE);
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
    private void showDialog() {
        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.text1);

        // set the custom dialog components - text, image and button

        // Close Button

        // Buy Button

        TextView t1 =(TextView)dialog.findViewById(R.id.dialogText);
        t1.setText("You have 100 coins to buy 8 players \n 1 Goal Keeper \n 4 Defenders \n 3 Strikers");


        ImageButton Close = (ImageButton) dialog.findViewById(R.id.close1);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

                platinum.setVisibility(View.VISIBLE);
                silver.setVisibility(View.VISIBLE);
                gold.setVisibility(View.VISIBLE);
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
