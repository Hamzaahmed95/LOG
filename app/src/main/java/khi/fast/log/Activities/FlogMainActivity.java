package khi.fast.log.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import khi.fast.log.FlogPlayers.FlogPlayersActivity;
import khi.fast.log.POJO.FriendlyMessage;
import khi.fast.log.POJO.IndivisualPoints;
import khi.fast.log.POJO.IndivisualRanks;
import khi.fast.log.POJO.onOff;
import khi.fast.log.R;

import static khi.fast.log.Utils.Constants.FLOG_MAIN_HEADING_TEXT;
import static khi.fast.log.Utils.Constants.FLOG_MAIN_PICK_YOUR_TEAM_TEXT;
import static khi.fast.log.Utils.Constants.FLOG_MAIN_RESULT_TEXT;
import static khi.fast.log.Utils.Constants.FLOG_MAIN_YOUR_PLAYERS_TEXT;

/**
 * Created by Hamza Ahmed on 07-Oct-17.
 */

public class FlogMainActivity extends AppCompatActivity {

    private LinearLayout pickTeam;
    private ProgressBar p;
    private Dialog dialog;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private ImageView backbutton5;
    private String NAME;
    private LinearLayout l1;
    private LinearLayout l2;

    private TextView textHide;
    private LinearLayout result;
    private TextView flog_text;
    private TextView pick_a_team;
    private TextView your_players;
    private TextView results;
    private Query mHouseDatabaseReference23;
    private Bundle extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flog_main_activity);

        initialization();
        settingValue();
        handleClickListener();

    }

    private void initialization() {
        p = (ProgressBar) findViewById(R.id.progressBar);

        l1 = (LinearLayout) findViewById(R.id.selectedplayer);
        l2 = (LinearLayout) findViewById(R.id.l1);
        textHide = (TextView) findViewById(R.id.textHide);


        result = (LinearLayout) findViewById(R.id.result);
        flog_text = (TextView) findViewById(R.id.optionUsername);
        pick_a_team = (TextView) findViewById(R.id.pick_a_team);
        your_players = (TextView) findViewById(R.id.your_players);
        results = (TextView) findViewById(R.id.results);
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mHouseDatabaseReference23 = mFirebaseDatabase.getReference().child("onOfFantasy").limitToLast(1);
        mFirebaseAuth = FirebaseAuth.getInstance();
        extra = this.getIntent().getExtras();
        pickTeam = (LinearLayout) findViewById(R.id.pickTeam);
        backbutton5 = (ImageView) findViewById(R.id.backButton5);
        l2.setVisibility(View.GONE);
    }

    private void settingValue() {
        flog_text.setText(FLOG_MAIN_HEADING_TEXT);
        pick_a_team.setText(FLOG_MAIN_PICK_YOUR_TEAM_TEXT);
        your_players.setText(FLOG_MAIN_YOUR_PLAYERS_TEXT);
        results.setText(FLOG_MAIN_RESULT_TEXT);
    }

    private void handleClickListener() {


        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FlogMainActivity.this, ResultFantasyActivity.class);
                startActivity(i);
            }
        });


        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FlogMainActivity.this, SelectedTeams.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("NAME", NAME);
                startActivity(i);
                finish();
            }
        });


        backbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FlogMainActivity.this, LogOverviewActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(i);
            }
        });
        mHouseDatabaseReference23.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        if (issue.child("bit").getValue().equals("1")) {
                            p.setVisibility(View.GONE);

                            textHide.setVisibility(View.VISIBLE);


                        } else {
                            l2.setVisibility(View.VISIBLE);
                            p.setVisibility(View.GONE);

                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        pickTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(NAME);
            }
        });

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //user is signed in

                    System.out.println("=>hamza here " + NAME);
                    onSignedInInitialize(user.getDisplayName());
                    NAME = user.getDisplayName();



                }
            }

        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListner != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
    }

    private void onSignedInInitialize(String username) {
        NAME = username;

    }


    private void showDialog(final String name) {
        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.text1);

        // set the custom dialog components - text, image and button

        // Close Button

        // Buy Button

        TextView t1 = (TextView) dialog.findViewById(R.id.dialogText);
        t1.setText("You have to select 5 players \n 1 Goal Keeper \n 2 Defenders \n 2 Strikers");

        dialog.setCanceledOnTouchOutside(false);
        System.out.println("name:==> " + NAME);
        Button Close = (Button) dialog.findViewById(R.id.close1);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                Intent i = new Intent(FlogMainActivity.this, FlogPlayersActivity.class);


                i.putExtra("username", NAME);
                startActivity(i);


            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


}