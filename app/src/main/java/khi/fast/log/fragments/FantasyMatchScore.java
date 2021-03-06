package khi.fast.log.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import khi.fast.log.activities.LogOverviewActivity;
import khi.fast.log.log_details.LogDetailsActivity;
import khi.fast.log.model.Ball;
import khi.fast.log.model.TeamNames;
import khi.fast.log.model.TeamsPicture;
import khi.fast.log.model.onOff;
import khi.fast.log.R;

import static android.app.Activity.RESULT_OK;


/**
 * Created by Hamza Ahmed on 07-Aug-17.
 */

public class FantasyMatchScore extends Fragment {

    private FirebaseStorage firebaseStorage;
    private StorageReference PointsTableStorageReference2;
    private DatabaseReference mMessageDatabaseReference2;

    private StorageReference PointsTableStorageReference;
    private DatabaseReference mMessageDatabaseReference;
    private ImageButton mPhotoPickerButton1;
    private ImageButton mPhotoPickerButton2;
    private ImageView imageView;
    private ImageView imageView2;
    private TextView Runs1;
    private TextView Overs1;
    private TextView Wicket1;
    private static final int RC_PHOTO_PICKER = 2;
    private static final int RC_PHOTO_PICKER2 = 3;

    private ImageView backButton4;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mScoreDatabaseReference1;
    private DatabaseReference mScoreDatabaseReference2;
    private DatabaseReference mScoreDatabaseReference3;
    private DatabaseReference mScoreDatabaseReference4;
    private DatabaseReference mScoreDatabaseReference5;
    private DatabaseReference mScoreDatabaseReference6;
    private DatabaseReference mScoreDatabaseReference7;
    private DatabaseReference mScoreDatabaseReference8;
    private DatabaseReference mScoreDatabaseReference22;
    private LinearLayout l1;
    private LinearLayout l3;
    private EditText e1;
    private TextView team;
    private TextView team0;
    private Button send;
    private ImageView f1;
    private ImageView f2;
    private ImageView f3;
    private ImageView f4;
    public static final int RC_SIGN_IN =1;
    private ChildEventListener mChildEventListener;
    private ChildEventListener mChildEventListener2;
    private ChildEventListener mChildEventListener3;
    private ChildEventListener mChildEventListener4;
    private ChildEventListener mChildEventListener5;
    private ChildEventListener mChildEventListener6;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    String name;
    private ImageButton Incrementruns1;
    private ImageButton Decrementruns1;
    private ImageButton Incrementwicket1;
    private ImageButton Decrementwicket1;
    private ImageButton Incrementover1;
    private ImageButton Decrementover1;
    private int RunsINCTeam1;
    private int WicketINCTeam1;
    private int OverIncOvers1;
    private ToggleButton toggle;
    private TextView matchUpdate;
    private TextView msg;
    String url1;
    String url2;
    private ImageView sadFace;
    private LinearLayout l2;
    private TextView player1;
    private TextView player2;
    private EditText e1player1;
    private EditText e1player2;
    private Button sendplayer1;
    private Button sendplayer2;

    private TextView player3;
    private TextView player4;
    private EditText e1player3;
    private EditText e1player4;
    private Button sendplayer3;
    private Button sendplayer4;

    private ProgressBar mProgressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getFragmentManager().getBackStackEntryCount() == 0) {

                    Intent i = new Intent(getActivity(), LogDetailsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(getActivity(), LogDetailsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fanmatch, container, false);

        firebaseStorage = FirebaseStorage.getInstance();
        matchUpdate = (TextView)view.findViewById(R.id.status);
        sadFace = (ImageView)view.findViewById(R.id.sadFace);
        l3=(LinearLayout)view.findViewById(R.id.teamNamesMiss) ;
        e1=(EditText)view.findViewById(R.id.e1);
        send=(Button)view.findViewById(R.id.send);
        mPhotoPickerButton1 = (ImageButton) view.findViewById(R.id.photoPickerTeam1);
        mPhotoPickerButton2 = (ImageButton) view.findViewById(R.id.photoPickerTeam2);
        imageView=(ImageView)view.findViewById(R.id.teamplaying1);
        imageView2=(ImageView)view.findViewById(R.id.teamplaying2);
        team=(TextView)view.findViewById(R.id.team1Name);
        team0=(TextView)view.findViewById(R.id.team2Name);
        Runs1 = (TextView) view.findViewById(R.id.livsScoreRuns1);
        Overs1 = (TextView) view.findViewById(R.id.livsScoreOvers1);
        Wicket1 = (TextView) view.findViewById(R.id.livsScoreWicket1);
        msg= (TextView)view.findViewById(R.id.msg);
        l2= (LinearLayout)view.findViewById(R.id.l2);
        l1 = (LinearLayout)view.findViewById(R.id.hide);
        l1.setVisibility(View.GONE);
        backButton4 =(ImageView) view.findViewById(R.id.backButton4);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        PointsTableStorageReference =firebaseStorage.getReference().child("futsalteam1");
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("futsal_team1");
        PointsTableStorageReference2 =firebaseStorage.getReference().child("futsalteam2");
        mMessageDatabaseReference2 =mFirebaseDatabase.getReference().child("futsal_team2");
        mScoreDatabaseReference1 = mFirebaseDatabase.getReference().child("futsalTeam1Goal");
        mScoreDatabaseReference2 = mFirebaseDatabase.getReference().child("futsalTeam2Goal");
        mScoreDatabaseReference3 = mFirebaseDatabase.getReference().child("futsalMatchTime");
        mScoreDatabaseReference22 = mFirebaseDatabase.getReference().child("onOfFutsalScore");
        mScoreDatabaseReference4 = mFirebaseDatabase.getReference().child("teamNames");
        mScoreDatabaseReference5 = mFirebaseDatabase.getReference().child("player1");
        mScoreDatabaseReference6 = mFirebaseDatabase.getReference().child("player2");
        mScoreDatabaseReference7 = mFirebaseDatabase.getReference().child("player3");
        mScoreDatabaseReference8 = mFirebaseDatabase.getReference().child("player4");
        toggle = (ToggleButton) view.findViewById(R.id.toggleButton);
        mProgressBar = (ProgressBar)view.findViewById(R.id.progressBar);

        player1=(TextView)view.findViewById(R.id.player1);
        player2=(TextView)view.findViewById(R.id.player2);
        e1player1=(EditText)view.findViewById(R.id.e1player1);
        e1player2=(EditText)view.findViewById(R.id.e1player2);
        sendplayer1=(Button)view.findViewById(R.id.sendplayer1);
        sendplayer2=(Button)view.findViewById(R.id.sendplayer2);

        player3=(TextView)view.findViewById(R.id.player3);
        player4=(TextView)view.findViewById(R.id.player4);
        e1player3=(EditText)view.findViewById(R.id.e1player3);
        e1player4=(EditText)view.findViewById(R.id.e1player4);
        sendplayer3=(Button)view.findViewById(R.id.sendplayer3);
        sendplayer4=(Button)view.findViewById(R.id.sendplayer4);

        f1 = (ImageView)view.findViewById(R.id.f1);
        f2 = (ImageView)view.findViewById(R.id.f2);
        f3 = (ImageView)view.findViewById(R.id.f3);
        f4 = (ImageView)view.findViewById(R.id.f4);

        if(isNetworkAvailable()) {

            sadFace.setVisibility(View.GONE);
            msg.setVisibility(View.GONE);
            Query mHouseDatabaseReference23 = mFirebaseDatabase.getReference().child("onOfFutsalScore").limitToLast(1);


            mHouseDatabaseReference23.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // dataSnapshot is the "issue" node with all children with id 0
                        for (DataSnapshot issue : dataSnapshot.getChildren()) {
                            mProgressBar.setVisibility(View.GONE);

                            if (issue.child("bit").getValue().equals("1")) {

                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                params.weight = 2.0f;
                                params.gravity = Gravity.CENTER;

                                matchUpdate.setText("No Match is in Progress");
                                matchUpdate.setLayoutParams(params);
                            } else {
                                l1.setVisibility(View.VISIBLE);
                                matchUpdate.setText("");
                            }
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }



        else{
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight=1.0f;
            l1.setVisibility(View.GONE);
            params.gravity = Gravity.CENTER;
            sadFace.setVisibility(View.VISIBLE);
            msg.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
            matchUpdate.setText(CapsFirst("Unfortunately, Scoring is \n not available right now"));
            msg.setText("Please Check your Internet Connection and try again.");
            l2.setLayoutParams(params);

        }



        Query crick1 = mFirebaseDatabase.getReference().child("futsal_team1").limitToLast(1);


        crick1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println(issue.child("photoUrl").getValue());
                        // if(issue.child("photoUrl").getValue().toString()!=null)
                        url1 = issue.child("photoUrl").getValue().toString();
                        Glide.with(imageView.getContext())
                                .load(url1)
                                .into(imageView);
                        //mprogressBar.setVisibility(View.GONE);
                        //   System.out.println();
                        //array[i]=issue.child("username").getValue().toString();
                        //i++;
                    }

                    //for(int j=0;j<i;j++){
                    //  System.out.println(j+""+array[j]);
                    // }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query player11 = mFirebaseDatabase.getReference().child("player1").limitToLast(1);


        player11.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        player1.setText(issue.child("name1").getValue().toString());
                        f1.setVisibility(View.VISIBLE);
                        //mprogressBar.setVisibility(View.GONE);
                        //   System.out.println();
                        //array[i]=issue.child("username").getValue().toString();
                        //i++;
                    }

                    //for(int j=0;j<i;j++){
                    //  System.out.println(j+""+array[j]);
                    // }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query player33 = mFirebaseDatabase.getReference().child("player3").limitToLast(1);


        player33.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        player4.setText(issue.child("name1").getValue().toString());

                        f4.setVisibility(View.VISIBLE);
                        //mprogressBar.setVisibility(View.GONE);
                        //   System.out.println();
                        //array[i]=issue.child("username").getValue().toString();
                        //i++;
                    }

                    //for(int j=0;j<i;j++){
                    //  System.out.println(j+""+array[j]);
                    // }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query player44 = mFirebaseDatabase.getReference().child("player4").limitToLast(1);


        player44.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        player3.setText(issue.child("name1").getValue().toString());

                        f3.setVisibility(View.VISIBLE);
                        //mprogressBar.setVisibility(View.GONE);
                        //   System.out.println();
                        //array[i]=issue.child("username").getValue().toString();
                        //i++;
                    }

                    //for(int j=0;j<i;j++){
                    //  System.out.println(j+""+array[j]);
                    // }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query player22 = mFirebaseDatabase.getReference().child("player2").limitToLast(1);


        player22.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        player2.setText(issue.child("name1").getValue().toString());

                        f2.setVisibility(View.VISIBLE);
                        //mprogressBar.setVisibility(View.GONE);
                        //   System.out.println();
                        //array[i]=issue.child("username").getValue().toString();
                        //i++;
                    }

                    //for(int j=0;j<i;j++){
                    //  System.out.println(j+""+array[j]);
                    // }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query crick2 = mFirebaseDatabase.getReference().child("futsal_team2").limitToLast(1);
        ;

        crick2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println(issue.child("photoUrl").getValue());
                        // if(issue.child("photoUrl").getValue().toString()!=null)
                        url2 = issue.child("photoUrl").getValue().toString();
                        Glide.with(imageView2.getContext())
                                .load(url2)
                                .into(imageView2);
                        //mprogressBar.setVisibility(View.GONE);
                        //   System.out.println();
                        //array[i]=issue.child("username").getValue().toString();
                        //i++;
                    }

                    //for(int j=0;j<i;j++){
                    //  System.out.println(j+""+array[j]);
                    // }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        Query mHouseDatabaseReference16 = mFirebaseDatabase.getReference().child("futsalMatchTime").limitToLast(1);

        mHouseDatabaseReference16.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello" + issue.getValue());
                        //  OverIncOvers1 = Integer.parseInt(issue.child("ball").getValue().toString());
                        // Overs1.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query mHouseDatabaseReference4 = mFirebaseDatabase.getReference().child("teamNames").limitToLast(1);

        mHouseDatabaseReference4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println(issue.child("name1").getValue().toString());
                        team.setText(issue.child("name1").getValue().toString());
                        team0.setText(issue.child("name2").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        Query mHouseDatabaseReference9 = mFirebaseDatabase.getReference().child("futsalTeam2Goal").limitToLast(1);

        mHouseDatabaseReference9.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello" + issue.child("ball").getValue());
                        WicketINCTeam1 = Integer.parseInt(issue.child("ball").getValue().toString());
                        Wicket1.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Query mHouseDatabaseReferenc8 = mFirebaseDatabase.getReference().child("futsalTeam1Goal").limitToLast(1);

        mHouseDatabaseReferenc8.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println("hello" + issue.child("ball").getValue());
                        RunsINCTeam1 = Integer.parseInt(issue.child("ball").getValue().toString());
                        Runs1.setText(issue.child("ball").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Incrementruns1 = (ImageButton)view.findViewById(R.id.incrementruns1);
        Incrementruns1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ball ball = new Ball(++RunsINCTeam1,1);
                mScoreDatabaseReference1.push().setValue(ball);
            }
        });
        Decrementruns1 = (ImageButton)view.findViewById(R.id.decrementruns1);
        Decrementruns1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--RunsINCTeam1,1);
                mScoreDatabaseReference1.push().setValue(ball);

            }
        });
        Incrementwicket1 = (ImageButton)view.findViewById(R.id.incrementwicket1);
        Incrementwicket1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++WicketINCTeam1,2);
                mScoreDatabaseReference2.push().setValue(ball);
            }
        });

        Decrementwicket1 = (ImageButton)view.findViewById(R.id.decrementwicket1);
        Decrementwicket1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--WicketINCTeam1,2);
                mScoreDatabaseReference2.push().setValue(ball);
            }
        });
        Incrementover1 = (ImageButton)view.findViewById(R.id.incrementover1);
        Incrementover1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(++OverIncOvers1,3);
                mScoreDatabaseReference3.push().setValue(ball);

            }
        });
        Decrementover1 = (ImageButton)view.findViewById(R.id.decrementover1);
        Decrementover1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ball ball = new Ball(--OverIncOvers1,3);
                mScoreDatabaseReference3.push().setValue(ball);
            }
        });

        backButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LogOverviewActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().finish();
                startActivity(i);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String score = e1.getText().toString();
                String[] arr = score.split("-");
                String team1 = arr[0];
                String team2 = arr[1];
                TeamNames teamNames = new TeamNames(team1,team2);
                mScoreDatabaseReference4.push().setValue(teamNames);

            }
        });

        sendplayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TeamNames teamNames = new TeamNames(e1player1.getText().toString(),"");
                mScoreDatabaseReference5.push().setValue(teamNames);

            }
        });

        sendplayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TeamNames teamNames = new TeamNames(e1player2.getText().toString(),"");
                mScoreDatabaseReference6.push().setValue(teamNames);

            }
        });
        sendplayer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TeamNames teamNames = new TeamNames(e1player4.getText().toString(),"");
                mScoreDatabaseReference7.push().setValue(teamNames);

            }
        });

        sendplayer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("333");
                TeamNames teamNames = new TeamNames(e1player3.getText().toString(),"");
                mScoreDatabaseReference8.push().setValue(teamNames);

            }
        });

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

        //mUsername = ANONYMOUS;

        if(mPhotoPickerButton1!=null)
            mPhotoPickerButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    // intent.setType("image/png");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER);

                }
            });
        if(mPhotoPickerButton2!=null)
            mPhotoPickerButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    // intent.setType("image/png");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER2);

                }
            });
        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //user is signed in

                    onSignedInInitialize(user.getDisplayName());
                    Log.d("user: ", user.getDisplayName());
                    name=user.getDisplayName();
                    if (!user.getDisplayName().equals("K142805 Hamza Ahmed")) {
                        mPhotoPickerButton1.setVisibility(View.GONE);
                        mPhotoPickerButton2.setVisibility(View.GONE);
                        Incrementruns1.setVisibility(View.GONE);
                        Decrementruns1.setVisibility(View.GONE);
                        Incrementwicket1.setVisibility(View.GONE);
                        Decrementwicket1.setVisibility(View.GONE);
                        Incrementover1.setVisibility(View.GONE);
                        Decrementover1.setVisibility(View.GONE);
                        toggle.setVisibility(View.GONE);
                        e1.setVisibility(View.GONE);
                        send.setVisibility(View.GONE);
                        sendplayer1.setVisibility(View.GONE);
                        sendplayer2.setVisibility(View.GONE);
                        e1player1.setVisibility(View.GONE);
                        e1player2.setVisibility(View.GONE);
                        sendplayer3.setVisibility(View.GONE);
                        sendplayer4.setVisibility(View.GONE);
                        e1player3.setVisibility(View.GONE);
                        e1player4.setVisibility(View.GONE);
                    }
                } else {
                    //user is signed out
                    onSignedOutInitialize();

                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER
                                    ).build(),
                            RC_SIGN_IN);

                }
            }

            ;
        };


        return view;
    }
    String CapsFirst(String str) {
        String[] words = str.split(" ");
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < words.length; i++) {
            ret.append(Character.toUpperCase(words[i].charAt(0)));
            ret.append(words[i].substring(1));
            if(i < words.length - 1) {
                ret.append(' ');
            }
        }
        return ret.toString();
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == RC_PHOTO_PICKER && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    PointsTableStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            TeamsPicture pointTablePicture = new TeamsPicture(downloadURL.toString(),null);
                            //Log.d("Musername","here-> "+PointTablePicture.getName().substring(7));

                            boolean isPhoto = downloadURL.toString() != null;
                            if (isPhoto) {
                                Toast.makeText(getActivity(), "Loading your picture!!", Toast.LENGTH_SHORT).show();
                                //mprogressBar.setVisibility(View.INVISIBLE);
                                imageView.setVisibility(View.VISIBLE);
                                Glide.with(imageView.getContext())
                                        .load(pointTablePicture.getPhotoUrl())
                                        .into(imageView);
                                mMessageDatabaseReference.push().setValue(pointTablePicture);
                            }
                            //checking=PointTablePicture.getPhotoUrl();

                            //Log.d("pointTablePicture1",""+checking);
                        }
                    });
        }

        if(requestCode == RC_PHOTO_PICKER2 && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    PointsTableStorageReference2.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            TeamsPicture pointTablePicture = new TeamsPicture(downloadURL.toString(),null);
                            //Log.d("Musername","here-> "+PointTablePicture.getName().substring(7));

                            boolean isPhoto = downloadURL.toString() != null;
                            if (isPhoto) {
                                Toast.makeText(getActivity(), "Loading your picture!!", Toast.LENGTH_SHORT).show();
                                //mprogressBar.setVisibility(View.INVISIBLE);
                                imageView2.setVisibility(View.VISIBLE);
                                Glide.with(imageView2.getContext())
                                        .load(pointTablePicture.getPhotoUrl())
                                        .into(imageView2);
                                mMessageDatabaseReference2.push().setValue(pointTablePicture);
                            }
                            //checking=PointTablePicture.getPhotoUrl();

                            //Log.d("pointTablePicture1",""+checking);
                        }
                    });
        }
    }



    private void  onSignedInInitialize(String username){

        attachDatabaseReadListener();

    }
    private void  onSignedOutInitialize(){
        // mUsername = ANONYMOUS;

        detachDatabaseReadListener();
    }

    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Ball ball = dataSnapshot.getValue(Ball.class);
                    //System.out.println(ball.toString());
                    System.out.println(ball.getTurn());
                    if(ball.getTurn() ==1) {
                        Runs1.setText(String.valueOf(ball.getBall()));
                    }
                    else if(ball.getTurn() ==2) {
                        Wicket1.setText(String.valueOf(ball.getBall()));
                    }
                    else if(ball.getTurn() ==3) {
                        Overs1.setText(String.valueOf(ball.getBall()));
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    //  FriendlyMessage f =dataSnapshot.getValue(FriendlyMessage.class);
                    // Log.d("ooo = ",f.getName());
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

            mScoreDatabaseReference1.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference2.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference3.addChildEventListener(mChildEventListener);
            mScoreDatabaseReference22.addChildEventListener(mChildEventListener);
            mMessageDatabaseReference.addChildEventListener(mChildEventListener);
            mMessageDatabaseReference2.addChildEventListener(mChildEventListener);
        }
        if(mChildEventListener2==null) {
            mChildEventListener2 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    TeamNames teamNames = dataSnapshot.getValue(TeamNames.class);
                    System.out.println("team names: "+teamNames.getName1());

                    team.setText(teamNames.getName1());
                    team0.setText(teamNames.getName2());

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    //  FriendlyMessage f =dataSnapshot.getValue(FriendlyMessage.class);
                    // Log.d("ooo = ",f.getName());
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

            mScoreDatabaseReference4.addChildEventListener(mChildEventListener2);
        }
        if(mChildEventListener3==null) {
            mChildEventListener3 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    TeamNames teamNames = dataSnapshot.getValue(TeamNames.class);

                    player1.setText(teamNames.getName1());

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    //  FriendlyMessage f =dataSnapshot.getValue(FriendlyMessage.class);
                    // Log.d("ooo = ",f.getName());
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

            mScoreDatabaseReference5.addChildEventListener(mChildEventListener3);
        }
        if(mChildEventListener4==null) {
            mChildEventListener4 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    TeamNames teamNames = dataSnapshot.getValue(TeamNames.class);
                    System.out.println("team names: "+teamNames.getName1());

                    player2.setText(teamNames.getName1());

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    //  FriendlyMessage f =dataSnapshot.getValue(FriendlyMessage.class);
                    // Log.d("ooo = ",f.getName());
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

            mScoreDatabaseReference6.addChildEventListener(mChildEventListener4);
        }
        if(mChildEventListener5==null) {
            mChildEventListener5 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    TeamNames teamNames = dataSnapshot.getValue(TeamNames.class);
                    System.out.println("team names: "+teamNames.getName1());

                    player4.setText(teamNames.getName1());

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    //  FriendlyMessage f =dataSnapshot.getValue(FriendlyMessage.class);
                    // Log.d("ooo = ",f.getName());
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

            mScoreDatabaseReference7.addChildEventListener(mChildEventListener5);
        }if(mChildEventListener6==null) {
            mChildEventListener6 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    TeamNames teamNames = dataSnapshot.getValue(TeamNames.class);
                    System.out.println("team names: "+teamNames.getName1());

                    player3.setText(teamNames.getName1());

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    //  FriendlyMessage f =dataSnapshot.getValue(FriendlyMessage.class);
                    // Log.d("ooo = ",f.getName());
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

            mScoreDatabaseReference8.addChildEventListener(mChildEventListener6);
        }
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mScoreDatabaseReference1.removeEventListener(mChildEventListener);
        mScoreDatabaseReference2.removeEventListener(mChildEventListener);
        mScoreDatabaseReference3.removeEventListener(mChildEventListener);

        mScoreDatabaseReference4.removeEventListener(mChildEventListener2);
        mScoreDatabaseReference5.removeEventListener(mChildEventListener3);
        mScoreDatabaseReference6.removeEventListener(mChildEventListener4);
        mScoreDatabaseReference7.removeEventListener(mChildEventListener5);
        mScoreDatabaseReference8.removeEventListener(mChildEventListener6);
        mScoreDatabaseReference22.removeEventListener(mChildEventListener);
        mMessageDatabaseReference.removeEventListener(mChildEventListener);
        mMessageDatabaseReference2.removeEventListener(mChildEventListener);
        mChildEventListener=null;
    }
    @Override
    public void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
    }
    @Override
    public void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }



}