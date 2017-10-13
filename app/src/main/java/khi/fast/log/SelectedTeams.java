package khi.fast.log;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

import static khi.fast.log.SilverPlayers.RC_SIGN_IN;

/**
 * Created by Hamza Ahmed on 09-Oct-17.
 */

public class SelectedTeams extends AppCompatActivity {



    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private DatabaseReference mMessageDatabaseReference;
    private String house;
    private TextView GoalKeeper;
    private TextView Defender1;
    private TextView Defender2;
    private TextView Striker1;
    private TextView Striker2;
    String name;

    private ChildEventListener mChildEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectedteam);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        Bundle extra =this.getIntent().getExtras();

        GoalKeeper=(TextView)findViewById(R.id.goalkeeper1);
        Defender1=(TextView)findViewById(R.id.defender1);
        Defender2=(TextView)findViewById(R.id.defender2);

        Striker1 =(TextView)findViewById(R.id.striker1);
        Striker2 =(TextView)findViewById(R.id.striker2);


        if(extra!=null) {
            house = extra.getString("NAME");

            System.out.println("name:==> " + house);
        }

        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("IndivisualTeams");

        Query mHouseDatabaseReferenceteam =mFirebaseDatabase.getReference().child("IndivisualTeams").orderByChild("userId");

        mHouseDatabaseReferenceteam.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count = 0;
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        //System.out.println("issue"+issue.child("check").getValue());
                        if (issue.child("userId").getValue().equals(house)) {

                            System.out.println("Defender1 " + issue.child("defender1").getValue());
                            System.out.println("Defender2 " + issue.child("defender2").getValue());
                            System.out.println("Striker1 " + issue.child("striker1").getValue());
                            System.out.println("Striker2 " + issue.child("striker2").getValue());
                            System.out.println("Goal Keeper " + issue.child("goalkeeper").getValue());
                            GoalKeeper.setText(issue.child("goalkeeper").getValue().toString());
                            Defender1.setText(issue.child("defender1").getValue().toString());
                            Defender2.setText(issue.child("defender2").getValue().toString());
                            Striker1.setText(issue.child("striker1").getValue().toString());
                            Striker2.setText(issue.child("striker2").getValue().toString());

                        }
                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    name = user.getDisplayName();


//

                    ;


                } else {
                    onSignedOutInitialize();

                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setTheme(R.style.FirebaseLoginTheme)
                                    .setLogo(R.drawable.wb5)
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER
                                    ).build(),
                            RC_SIGN_IN);
                }
            }

            ;
        };





    }
    @Override
    protected void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
    }
    @Override
    protected void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SelectedTeams.this,FlogMainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("name1",name);
        startActivity(i);
        finish();
    }

    private void  onSignedInInitialize(String username){
        //mUsername = username;
        attachDatabaseReadListener();

    }
    private void  onSignedOutInitialize(){
       // mUsername = ANONYMOUS;
        //mPlayerListAdapter.clear();

        detachDatabaseReadListener();
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mMessageDatabaseReference.removeEventListener(mChildEventListener);
        mChildEventListener=null;

    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    UsersFantacyTeam usersFantacyTeam = dataSnapshot.getValue(UsersFantacyTeam.class);
                    GoalKeeper.setText(usersFantacyTeam.getGoalkeeper());
                    Defender1.setText(usersFantacyTeam.getDefender1());
                    Defender2.setText(usersFantacyTeam.getDefender2());
                    Striker1.setText(usersFantacyTeam.getStriker1());
                    Striker2.setText(usersFantacyTeam.getStriker2());
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
            mMessageDatabaseReference.addChildEventListener(mChildEventListener);
            mMessageDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }




    }


}
