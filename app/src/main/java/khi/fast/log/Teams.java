package khi.fast.log;

/**
 * Created by Hamza Ahmed on 28-Sep-17.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class Teams extends Activity {

    private LinearLayout stags;
    private LinearLayout dragons;
    private LinearLayout jaguars;
    private LinearLayout falcons;
    private LinearLayout hunters;
    private LinearLayout dires;

    ProgressBar mprogressBar;
    private ImageView backButton6;
    String name1;
    private LinearLayout team;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teams_overview);
        System.out.println("Teams: here");

        team=(LinearLayout)findViewById(R.id.team);
        team.setBackgroundResource(R.drawable.bg_gradient14);
        backButton6=(ImageView)findViewById(R.id.backButton6);
        mFirebaseAuth = FirebaseAuth.getInstance();

        backButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Teams.this,CricketActivity.class);

                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
        stags = (LinearLayout) findViewById(R.id.stags);
        dragons = (LinearLayout) findViewById(R.id.dragons);
        jaguars = (LinearLayout) findViewById(R.id.jaguars);
        falcons = (LinearLayout) findViewById(R.id.falcons);
        hunters = (LinearLayout) findViewById(R.id.hunters);
        dires = (LinearLayout) findViewById(R.id.dires);



        stags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Teams.this,StagsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("TEAM" ,"stags");
                startActivity(i);
            }
        });
        dragons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Teams.this,StagsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("TEAM" ,"dragons");

                startActivity(i);
                finish();
            }
        });
        jaguars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Teams.this,StagsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("TEAM" ,"jaguars");
                startActivity(i);
                finish();
            }
        });
        falcons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Teams.this,StagsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("TEAM" ,"falcons");
                startActivity(i);
                finish();
            }
        });
        hunters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Teams.this,StagsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("TEAM" ,"hunters");
                startActivity(i);
                finish();
            }
        });
        dires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Teams.this,StagsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("TEAM" ,"dires");
                startActivity(i);
                finish();
            }
        });

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null) {
                    name1 = user.getDisplayName();
                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Teams.this,CricketActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
    @Override
    public void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
    }
    public void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }


}
