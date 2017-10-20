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
import java.util.Collections;
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
    private DatabaseReference mPointsDatabaseReference22;
    private DatabaseReference mRanksDatabaseReference22;
    private ToggleButton toggle;
    private Button button;
    private ChildEventListener mChildEventListener;
    private ChildEventListener mChildEventListener1;
    private ChildEventListener mChildEventListener2;
    private TextView textHide;
    private TextView points;
    private TextView ranks;
    String goals;
    private int count_goals;
    private int count_assists;
    private int count_cleansheets;
    String assists;
    String cleansheets;
    String savePenaltyGolkeeper;
    private String name1;
    private int countPoints;
    private int countPoints2;
    private int countPoints3;
    private int countPoints4;
    private String Ranks="";
    private LinearLayout result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flog_main_activity);
        p=(ProgressBar)findViewById(R.id.progressBar);
        points=(TextView)findViewById(R.id.points);
        ranks=(TextView)findViewById(R.id.ranks);
        l1 = (LinearLayout) findViewById(R.id.selectedplayer);
        l2=(LinearLayout)findViewById(R.id.l1);
        textHide =(TextView)findViewById(R.id.textHide);
        toggle = (ToggleButton) findViewById(R.id.toggleButton);
        button = (Button) findViewById(R.id.Button2);
        result = (LinearLayout) findViewById(R.id.result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FlogMainActivity.this,FantasyScoringActivity.class);
                startActivity(i);
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FlogMainActivity.this,ResultFantasyActivity.class);
                startActivity(i);
            }
        });

        Bundle extra=this.getIntent().getExtras();
        if(extra!=null){
            name1=extra.getString("name1");
        }

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mScoreDatabaseReference22 = mFirebaseDatabase.getReference().child("onOfFantasy");
        mPointsDatabaseReference22 = mFirebaseDatabase.getReference().child("IndivisualPoints");
        mRanksDatabaseReference22 = mFirebaseDatabase.getReference().child("IndivisualRank");
        l2.setVisibility(View.GONE);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FlogMainActivity.this, SelectedTeams.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("NAME",NAME);
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





        Query mHouseDatabaseReference10 =mFirebaseDatabase.getReference().child("IndivisualPoints").orderByChild("name");

        mHouseDatabaseReference10.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count = 0;
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                       // System.out.println("king" + NAME);
                        System.out.println("here!"+issue.child("name").getValue()+"=="+name1);

                        if (issue.child("name").getValue().equals(name1)) {
                            if (Integer.parseInt(issue.child("count").getValue().toString()) == 0) {
                                issue.getRef().child("count").setValue(1);

                                Query mHouseDatabaseReference33 = mFirebaseDatabase.getReference().child("FantasyScoringLineUp").limitToLast(1);

                                mHouseDatabaseReference33.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {

                                            for (DataSnapshot issue : dataSnapshot.getChildren()) {

                                                // System.out.println("issue" + issue.child("userId").getValue());
                                                goals=issue.child("goalScorer").getValue().toString();
                                                final String[] arr = goals.split("-");
                                                final int arr1=arr.length;
                                                System.out.println("array: " + arr.length);
                                                String a1[] = new String[arr.length];
                                                for (int i = 0; i < arr.length; i++) {
                                                    a1[i] = arr[i];
                                                }
                                                assists=issue.child("assist").getValue().toString();
                                                final String[] assist = assists.split("-");
                                                final int assistlength=assist.length;
                                                System.out.println("array: " + assist.length);
                                                String a2[] = new String[assist.length];
                                                for (int i = 0; i < assist.length; i++) {
                                                    a2[i] = assist[i];
                                                }
                                                cleansheets=issue.child("cleanSheets").getValue().toString();
                                                final String [] cleansheet=cleansheets.split("-");
                                                final int cleansheetsLength=cleansheet.length;
                                                String a3[] = new String[cleansheetsLength];
                                                for (int i = 0; i < cleansheetsLength; i++) {
                                                    a3[i] = cleansheet[i];
                                                }
                                                savePenaltyGolkeeper=issue.child("savePenaltyGolkeeper").getValue().toString();


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
                                                                            + issue.child("striker2").getValue() + " " + issue.child("goalkeeper").getValue();

                                                                    d1=issue.child("defender1").getValue().toString();
                                                                    d2=issue.child("defender2").getValue().toString();
                                                                    s1=issue.child("striker1").getValue().toString();
                                                                    s2=issue.child("striker2").getValue().toString();
                                                                    g=issue.child("goalkeeper").getValue().toString();

                                                                }
                                                            }
                                                            int count_test=0;
                                                            count_goals=0;
                                                            for(int j=0;j<arr1;j++){
                                                               // System.out.println("here2: "+arr[j]+" "+d1);
                                                                if(arr[j].equals(d1) ||arr[j].equals(d2) ||arr[j].equals(s1) ||arr[j].equals(s2) ||arr[j].equals(g)){


                                                                    System.out.println("Goal Scored by: "+arr[j]);
                                                                    count_test++;
                                                                    count_goals=count_goals+3;
                                                                    Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("IndivisualPoints").orderByChild("name");

                                                                    mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                                            if (dataSnapshot.exists()) {

                                                                                for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                                                                    // do something with the individual "issues"
                                                                                    if(issue.child("name").getValue().equals(NAME)) {
                                                                                        countPoints = Integer.parseInt(issue.child("points").getValue().toString()) + count_goals;
                                                                                        System.out.println("countGoals: "+countPoints);
                                                                                        issue.getRef().child("points").setValue(countPoints);
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
                                                            if(count_test==arr.length)
                                                                count_assists=0;
                                                            for(int j=0;j<assistlength;j++){
                                                                //System.out.println("assist: "+assist[j]+" "+d1);
                                                                if(assist[j].equals(d1) ||assist[j].equals(d2) ||assist[j].equals(s1) ||assist[j].equals(s2) ||assist[j].equals(g)){

                                                                    System.out.println("assist by: "+assist[j]);
                                                                    count_assists=count_assists+2;


                                                                    Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("IndivisualPoints").orderByChild("name");

                                                                    mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                                            if (dataSnapshot.exists()) {
                                                                                int count=0;
                                                                                for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                                                                    // do something with the individual "issues"
                                                                                    if(issue.child("name").getValue().equals(NAME)) {
                                                                                    //    count = Integer.parseInt(issue.child("points").getValue().toString()) + count_assists;

                                                                                        if(countPoints==0){

                                                                                            countPoints2=Integer.parseInt(issue.child("points").getValue().toString())+count_assists;
                                                                                            issue.getRef().child("points").setValue(countPoints2);
                                                                                        }
                                                                                        else {
                                                                                            countPoints2 = countPoints + count_assists;
                                                                                            System.out.println("countAssist: " + countPoints2);
                                                                                            issue.getRef().child("points").setValue(countPoints2);
                                                                                        }

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
                                                            count_cleansheets=0;
                                                            for(int j=0;j<cleansheetsLength;j++){
                                                                System.out.println("cleansheets: "+cleansheet[j]+" ");
                                                                if(cleansheet[j].equals(d1) ||cleansheet[j].equals(d2) ||cleansheet[j].equals(s1) ||cleansheet[j].equals(s2) ||cleansheet[j].equals(g)){

                                                                    System.out.println("cleansheets by: "+cleansheet[j]);
                                                                    count_cleansheets=count_cleansheets+2;


                                                                    Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("IndivisualPoints").orderByChild("name");

                                                                    mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                                            if (dataSnapshot.exists()) {

                                                                                for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                                                                    // do something with the individual "issues"
                                                                                    if(issue.child("name").getValue().equals(NAME)) {
                                                                                        //    count = Integer.parseInt(issue.child("points").getValue().toString()) + count_assists;

                                                                                        if(countPoints2==0){
                                                                                            if(countPoints==0) {

                                                                                                countPoints3 = Integer.parseInt(issue.child("points").getValue().toString()) + count_cleansheets;
                                                                                                issue.getRef().child("points").setValue(countPoints3);
                                                                                            }
                                                                                            else{
                                                                                                countPoints3 = countPoints + count_cleansheets;
                                                                                                issue.getRef().child("points").setValue(countPoints3);

                                                                                            }
                                                                                        }
                                                                                        else {
                                                                                            countPoints3 = countPoints2 + count_cleansheets;
                                                                                            System.out.println("cleansheets: " + countPoints3);
                                                                                            System.out.println("countPoints2: " + countPoints2);
                                                                                            issue.getRef().child("points").setValue(countPoints3);

                                                                                        }
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
                                                            System.out.println("save p"+savePenaltyGolkeeper);
                                                            if(savePenaltyGolkeeper.equals(d1) ||savePenaltyGolkeeper.equals(d2) ||savePenaltyGolkeeper.equals(s1) ||savePenaltyGolkeeper.equals(s2) ||savePenaltyGolkeeper.equals(g)){

                                                                System.out.println("savePenalty: "+savePenaltyGolkeeper);



                                                                Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("IndivisualPoints").orderByChild("name");

                                                                mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                        if (dataSnapshot.exists()) {
                                                                            for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                                                                // do something with the individual "issues"
                                                                                if(issue.child("name").getValue().equals(NAME)) {
                                                                                    //    count = Integer.parseInt(issue.child("points").getValue().toString()) + count_assists;

                                                                                    if(countPoints3==0){
                                                                                        if(countPoints2==0) {
                                                                                            if (countPoints == 0) {

                                                                                                countPoints4 = Integer.parseInt(issue.child("points").getValue().toString()) + 3;
                                                                                                issue.getRef().child("points").setValue(countPoints4);
                                                                                                System.out.println("countPoint3=0 and countPoints4: " + countPoints4);
                                                                                            } else {
                                                                                                countPoints4 = countPoints + 3;
                                                                                                issue.getRef().child("points").setValue(countPoints4);
                                                                                                System.out.println("countPoint3=0 and countPoints4: " + countPoints4);

                                                                                            }
                                                                                        }
                                                                                        else{
                                                                                            countPoints4 = countPoints2 + 3;
                                                                                            issue.getRef().child("points").setValue(countPoints4);
                                                                                            System.out.println("countPoint3=0 and countPoints4: " + countPoints4);
                                                                                        }

                                                                                    }
                                                                                    else {
                                                                                        countPoints4 = countPoints3 + 3;
                                                                                      //  System.out.println("savePenalty: " + countPoints4);
                                                                                        System.out.println("countPoints4: " + countPoints4);
                                                                                        issue.getRef().child("points").setValue(countPoints4);

                                                                                    }
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
                                                    }


                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });



                                            }


                                        }
                                    }


                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });





                            } else {
                                System.out.println("not exist");
                            }


                        }

                    }



                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Query mHouseDatabaseReference233 = mFirebaseDatabase.getReference().child("IndivisualPoints").orderByChild("points").limitToLast(3);


        mHouseDatabaseReference233.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList a1 = new ArrayList();

                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        System.out.println("sheryy pagal"+issue.child("name").getValue());
                        a1.add(issue.child("name").getValue());

                    }
                    Collections.reverse(a1);

                    StringBuffer text = new StringBuffer();

                    for(int i=0;i<a1.size();i++){
                        Ranks=Ranks.concat(a1.get(i).toString()+"-");

                    }



                  //  markdisplayTextArea.setText(text.toString());
                    System.out.println("After Reverse Order, ArrayList Contains : " + Ranks);
                    int count=0;
                    for (int i=0;i<Ranks.length();i++){

                        if(Ranks.charAt(i)=='-'){
                            Ranks=Ranks.replace(Ranks.charAt(i),'\n');
                        }
                    }
                    System.out.println("After Reverse : " + Ranks);



                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
        Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("IndivisualPoints").orderByChild("name");

        mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count = 0;
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        System.out.println("king" +name1);
                        System.out.println("true or false "+issue.child("name").getValue().equals(name1));
                        if( issue.child("name").getValue().equals(name1)){
                            points.setText(issue.child("points").getValue().toString()+" Points");
                            ranks.setText(issue.child("rank").getValue().toString()+" Rank");
                            System.out.println("issue" + issue.child("points").getValue()+" "+name1);
                        }


                    }


                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
      /*  Query mHouseDatabaseReference4 =mFirebaseDatabase.getReference().child("IndivisualRank").orderByChild("name");

        mHouseDatabaseReference4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count = 0;
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        System.out.println("king" +name1);
                        if( issue.child("name").getValue().equals(name1)){
                            ranks.setText(issue.child("ranks").getValue().toString()+" Rank");
                            System.out.println("issue" + issue.child("ranks").getValue());
                        }


                    }


                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



*/

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
                        button.setVisibility(View.GONE);
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
    private static String myReverse(String str) {
        String reverse = "";
        int length = str.length();
        for( int i = length - 1 ; i >= 0 ; i-- ) {
            reverse = reverse + str.charAt(i);
        }
        return reverse;
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
        if (mChildEventListener1 == null) {
            mChildEventListener1 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    IndivisualPoints indivisualPoints = dataSnapshot.getValue(IndivisualPoints.class);
                    if(indivisualPoints.getName().equals(name1)) {
                        points.setText(String.valueOf(indivisualPoints.getPoints()) + " Points");
                        System.out.println("String : " + String.valueOf(indivisualPoints.getPoints()));
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    IndivisualPoints indivisualPoints = dataSnapshot.getValue(IndivisualPoints.class);
                    points.setText(String.valueOf(indivisualPoints.getPoints())+" Points");
                    ranks.setText(String.valueOf(indivisualPoints.getRank())+" Rank");

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
        if (mChildEventListener2 == null) {
            mChildEventListener2 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    IndivisualRanks indivisualRanks = dataSnapshot.getValue(IndivisualRanks.class);
                  //  ranks.setText(String.valueOf(indivisualP)+" Ranks");


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


            mRanksDatabaseReference22.addChildEventListener(mChildEventListener2);
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
        t1.setText("You have to select 5 players \n 1 Goal Keeper \n 2 Defenders \n 2 Strikers");

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