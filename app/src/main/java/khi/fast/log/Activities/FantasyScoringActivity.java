package khi.fast.log.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

import khi.fast.log.POJO.Count;
import khi.fast.log.POJO.FantasyScoring;
import khi.fast.log.POJO.IndivisualPoints;
import khi.fast.log.R;

import static khi.fast.log.Activities.MOM.RC_SIGN_IN;


/**
 * Created by Hamza Ahmed on 11-Oct-17.
 */

public class FantasyScoringActivity extends AppCompatActivity {

    private static final String ANONYMOUS = "Anonymous";
    private EditText e1;
    private EditText e2;
    private EditText e3;
    private EditText e4;
    private EditText e5;
    private Button b1;
    private Button b2;
    String goals;
    String assist;
    String cleansheet;
    String penalty;
    private DatabaseReference score;
    private ChildEventListener mChildEventListener2;
    private ChildEventListener mChildEventListener1;
    private ChildEventListener mChildEventListener;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    public String NAME;

    private DatabaseReference mPointsDatabaseReference22;
    private DatabaseReference mScoringsLineupDatabaseReference22;
    private FirebaseDatabase mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fantacy_scoring_activity);
        e1 = (EditText) findViewById(R.id.Goals);
        NAME = ANONYMOUS;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mPointsDatabaseReference22 = mFirebaseDatabase.getReference().child("IndivisualPoints");
        score = mFirebaseDatabase.getReference().child("Score");





        //mMessageDatabaseReference = mFirebaseDatabase.getReference().child("NAMEID");
        Query mHouseDatabaseReference30 =mFirebaseDatabase.getReference().child("NAMEID").orderByChild("name");

        mHouseDatabaseReference30.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count = 0;
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        System.out.println("king" +NAME);
                        if( issue.child("name").getValue().equals(NAME)){

                            if(issue.child("id").getValue().equals("hamza123ABC")){
                                System.out.println("hamza ahmed");
                            }
                        }
                        else{
                            System.out.println("not exist");
                        }


                    }



                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        mScoringsLineupDatabaseReference22 = mFirebaseDatabase.getReference().child("FantasyScoringLineUp");

        mFirebaseAuth = FirebaseAuth.getInstance();
        e2 = (EditText) findViewById(R.id.Assist);
        e3 = (EditText) findViewById(R.id.CleanSheet);
        e4 = (EditText) findViewById(R.id.PenaltySaved);
        e5 = (EditText) findViewById(R.id.count);
        b1 = (Button) findViewById(R.id.send);
        b2 = (Button) findViewById(R.id.sharepoints);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FantasyScoring fantasyscoring = new FantasyScoring(e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), e4.getText().toString(),Integer.parseInt(e5.getText().toString()));
                mScoringsLineupDatabaseReference22.push().setValue(fantasyscoring);


                Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("IndivisualPoints");

                mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int count=0;
                            for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                // do something with the individual "issues"

                                //    count = Integer.parseInt(issue.child("points").getValue().toString()) + 3;
                                    System.out.println("child value: "+issue.child("count").getValue());
                                    issue.getRef().child("count").setValue(0);
                                    //issue.getRef().child("points").setValue(count);
                                    //issue.getRef().child("count").setValue(Integer.parseInt(issue.child("count").getValue().toString())+1);


                            }


                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                Count count= new Count(Integer.parseInt(e5.getText().toString()));
                score.push().setValue(count);

                //NAMEID nameid= new NAMEID(NAME,"hamza123ABC");
                //mMessageDatabaseReference.push().setValue(nameid);


            }});



















               /* goals = e1.getText().toString();
                final String[] arr = goals.split("-");
                final int arr1=arr.length;
                System.out.println("array: " + arr.length);
                String a1[] = new String[arr.length];
                for (int i = 0; i < arr.length; i++) {
                    a1[i] = arr[i];
                }
                Query mHouseDatabaseReference03 = mFirebaseDatabase.getReference().child("IndivisualTeams").orderByChild("userId");

                mHouseDatabaseReference03.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int count = 0;
                            String d1="";
                            String d2="";
                            String s1="";
                            String s2="";
                            String g="";
                            String test = "";
                            for (DataSnapshot issue : dataSnapshot.getChildren()) {

                                System.out.println("issue" + issue.child("userId").getValue());

                                if (issue.child("userId").getValue().equals(NAME)) {
                                    test = issue.child("defender1").getValue() + "" + issue.child("defender2").getValue() + " " + issue.child("striker1").getValue() + " "
                                            + issue.child("striker2").getValue() + " " + issue.child("goli").getValue();

                                    d1=issue.child("defender1").getValue().toString();
                                    d2=issue.child("defender2").getValue().toString();
                                    s1=issue.child("striker1").getValue().toString();
                                    s2=issue.child("striker2").getValue().toString();
                                    g=issue.child("goalkeeper").getValue().toString();

                                }
                            }
                            System.out.println("here1: ");
                            for(int j=0;j<arr1;j++){
                                System.out.println("here2: "+arr[j]+" "+d1);
                                if(arr[j].equals(d1) ||arr[j].equals(d2) ||arr[j].equals(s1) ||arr[j].equals(s2) ||arr[j].equals(g)){

                                    System.out.println("here3: ");


                                    Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("IndivisualPoints").orderByChild("name");

                                    mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.exists()) {
                                                int count=0;
                                                for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                                    // do something with the individual "issues"
                                                    if(issue.child("name").getValue().equals(NAME)) {
                                                        count = Integer.parseInt(issue.child("points").getValue().toString()) + 3;
                                                        System.out.println("count: "+count);
                                                        issue.getRef().child("points").setValue(count);
                                                    }

                                                }


                                            }
                                        }


                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                }

                            }
                            System.out.println("Team is :" + test);


                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                /*for (int i = 0; i < arr.length; i++) {






                    System.out.println(a1[i]);
                }*/
                //   String Batsman1 = arr[0];
                /*assist = e2.getText().toString();
                String[] arr2 = assist.split("-");
                System.out.println("array: " + arr2.length);
                String a2[] = new String[arr2.length];
                for (int i = 0; i < arr2.length; i++) {
                    a2[i] = arr2[i];
                }
                for (int i = 0; i < arr2.length; i++) {
                    System.out.println(a2[i]);
                }
                cleansheet = e3.getText().toString();
                cleansheet = e3.getText().toString();
                String[] arr3 = cleansheet.split("-");
                String goli = arr3[0];
                String defender = arr3[1];
                String striker = arr3[2];


                System.out.println("cleansheet: " + goli + " " + defender + " " + striker);
                //String a3 [] = new String[arr2.length];

                penalty = e4.getText().toString();
                String golisave = penalty;
                System.out.println("hamza" + goals + " " + assist + " " + cleansheet + " " + penalty);?*/

        Query mHouseDatabaseReference03 = mFirebaseDatabase.getReference().child("IndivisualTeams").orderByChild("userId");

        mHouseDatabaseReference03.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count = 0;
                    String test = "";
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {

                        System.out.println("issue" + issue.child("userId").getValue());

                        if (issue.child("userId").getValue().equals(NAME)) {
                            test = issue.child("defender1").getValue() + "" + issue.child("defender2").getValue() + " " + issue.child("striker1").getValue() + " "
                                    + issue.child("striker2").getValue() + " " + issue.child("goli").getValue();
                        }


                    }
                    System.out.println("Team is :" + test);


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
                    NAME = user.getDisplayName();


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

    private void  onSignedInInitialize(String username){
        attachDatabaseReadListener();

    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListner != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        //detachDatabaseReadListener();
    }

    private void  onSignedOutInitialize(){
        detachDatabaseReadListener();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

    private void attachDatabaseReadListener() {

        if (mChildEventListener1 == null) {
            mChildEventListener1 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    IndivisualPoints indivisualPoints = dataSnapshot.getValue(IndivisualPoints.class);
                   // points.setText(String.valueOf(indivisualPoints.getPoints())+" Points");


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


            mPointsDatabaseReference22.addChildEventListener(mChildEventListener1);
        }
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    IndivisualPoints indivisualPoints = dataSnapshot.getValue(IndivisualPoints.class);
                    // points.setText(String.valueOf(indivisualPoints.getPoints())+" Points");


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


            score.addChildEventListener(mChildEventListener);
        }
        if (mChildEventListener2 == null) {
            mChildEventListener2 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    IndivisualPoints indivisualPoints = dataSnapshot.getValue(IndivisualPoints.class);
                    // points.setText(String.valueOf(indivisualPoints.getPoints())+" Points");


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


            mScoringsLineupDatabaseReference22.addChildEventListener(mChildEventListener2);
        }
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener2!=null)
            mScoringsLineupDatabaseReference22.removeEventListener(mChildEventListener2);
        mChildEventListener2=null;
        if(mChildEventListener1!=null)
            mPointsDatabaseReference22.removeEventListener(mChildEventListener1);
        mChildEventListener1=null;
        if(mChildEventListener!=null)
            score.removeEventListener(mChildEventListener);
        mChildEventListener=null;
    }

}
