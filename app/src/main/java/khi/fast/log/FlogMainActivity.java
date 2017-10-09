package khi.fast.log;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.firebase.ui.auth.AuthUI;
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
import java.util.List;

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
    private DatabaseReference mScoreDatabaseReference22;
    private ToggleButton toggle;
    private ChildEventListener mChildEventListener;
    private TextView textHide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flog_main_activity);
        p=(ProgressBar)findViewById(R.id.progressBar);
        l1 = (LinearLayout) findViewById(R.id.selectedplayer);
        l2=(LinearLayout)findViewById(R.id.l1);
        textHide =(TextView)findViewById(R.id.textHide);
        toggle = (ToggleButton) findViewById(R.id.toggleButton);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mScoreDatabaseReference22 = mFirebaseDatabase.getReference().child("onOfFantasy");
        l2.setVisibility(View.GONE);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FlogMainActivity.this, SelectedTeams.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

        mFirebaseAuth = FirebaseAuth.getInstance();
        pickTeam = (LinearLayout) findViewById(R.id.pickTeam);
        backbutton5 = (ImageView) findViewById(R.id.backButton);
        backbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FlogMainActivity.this, Check123.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(i);
            }
        });

        Query mHouseDatabaseReference23 = mFirebaseDatabase.getReference().child("onOfFantasy").limitToLast(1);


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
                    if(!NAME.equals("K142805 Hamza Ahmed")){
                        toggle.setVisibility(View.GONE);
                    }
                    //   onSignedInInitialize(NAME);


                }
            }

        };
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    onOff onOff = new onOff("1");
                    mScoreDatabaseReference22.push().setValue(onOff);

                } else {
                    // The toggle is disabled
                    onOff onOff = new onOff("0");

                    mScoreDatabaseReference22.push().setValue(onOff);

                }
            }
        });


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
        attachDatabaseReadListener();

    }

    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);


                    //mPlayerListAdapter2.add(friendlyMessage);
                    //mProgressBar.setVisibility(View.GONE);


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    // FriendlyMessage f =dataSnapshot.getValue(FriendlyMessage.class);

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };


            mScoreDatabaseReference22.addChildEventListener(mChildEventListener);
        }


    }


    private void showDialog(final String name) {
        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.text1);

        // set the custom dialog components - text, image and button

        // Close Button

        // Buy Button

        TextView t1 = (TextView) dialog.findViewById(R.id.dialogText);
        t1.setText("You have 100 coins to buy 8 players \n 1 Goal Keeper \n 4 Defenders \n 3 Strikers");

        dialog.setCanceledOnTouchOutside(false);
        System.out.println("name:==> " + NAME);
        Button Close = (Button) dialog.findViewById(R.id.close1);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                Intent i = new Intent(FlogMainActivity.this, PlatinumPlayers.class);


                i.putExtra("username", NAME);
                startActivity(i);


            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


}