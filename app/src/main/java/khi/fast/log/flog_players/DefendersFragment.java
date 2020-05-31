package khi.fast.log.flog_players;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

import khi.fast.log.activities.SelectedTeams;
import khi.fast.log.adapter.FlogPlayersAdapter;
import khi.fast.log.model.FriendlyMessage;
import khi.fast.log.model.UsersFantacyTeam;
import khi.fast.log.R;

public class DefendersFragment extends Fragment {

    private static final String TAG = "ProfileActivity";

    public static final int RC_SIGN_IN =1;
    private ListView mMessageListView;
    private FlogPlayersAdapter mFlogPlayersAdapter;
    private ProgressBar mProgressBar;
    public String NAME;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private Button showTeam;
    private Dialog dialog;

    private String goalkeeper1;
    private String defender1;
    private String defender2;
    private String attacker1;
    private String attacker2;
    Query mHouseDatabaseReference3;
    Query mHouseDatabaseReferencegold;
    Query mHouseDatabaseReferenceplatinum;


    public DefendersFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.flog_players, container, false);

        initialization(view);
        settingValue();
        handleClickListener();
        AuthListener();

        return view;
    }
    private void initialization(View view){

        mProgressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        mMessageListView = (ListView)view.findViewById(R.id.messageListView);
        showTeam=(Button)view.findViewById(R.id.UserTeam);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("goldPlayers");
        mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("silverPlayers").orderByChild("check");
        mHouseDatabaseReferencegold =mFirebaseDatabase.getReference().child("goldPlayers").orderByChild("check");
        mHouseDatabaseReferenceplatinum =mFirebaseDatabase.getReference().child("platinumPlayers").orderByChild("check");
    }

    private void settingValue(){

    }

    private void handleClickListener(){
        mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count =0;
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {

                        System.out.println("issue"+issue.child("check").getValue());
                        if(issue.child("check").getValue().equals(true)) {
                            System.out.println("selected players: " + issue.child("text").getValue());
                            count++;
                            if(count==1){
                                attacker1= issue.child("text").getValue().toString();
                            }
                            else if(count==2){

                                attacker2= issue.child("text").getValue().toString();
                            }
                        }
                    }
                    if(count==0) {
                        attacker1 = "not selected";
                        attacker2 = "not selected";
                    }
                    else if(count==1){
                        attacker1 = "atleast 2 attackers should be selected";
                        attacker2 = "atleast 2 attackers should be selected";
                    }
                    else if(count>2){
                        attacker1="more than 2";
                        attacker2="more than 2";
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mHouseDatabaseReferencegold.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count =0;

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        System.out.println("issue"+issue.child("check").getValue());
                        if(issue.child("check").getValue().equals(true)) {
                            System.out.println("selected players: " + issue.child("text").getValue());
                            count++;
                            if(count==1){
                                defender1= issue.child("text").getValue().toString();
                            }
                            else if(count==2){

                                defender2= issue.child("text").getValue().toString();
                            }
                        }
                    }
                    if(count==0) {
                        defender1 = "not selected";
                        defender2 = "not selected";
                    }
                    else if(count==1){
                        defender1 = "atleast 2 defenders should be selected";
                        defender2 = "atleast 2 defenders should be selected";
                    }
                    else if(count>2){
                        defender1="more than 2";
                        defender2="more than 2";
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        mHouseDatabaseReferenceplatinum.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count = 0;
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        System.out.println("issue" + issue.child("check").getValue());
                        if (issue.child("check").getValue().equals(true)) {
                            count++;
                            System.out.println("selected players: " + issue.child("text").getValue());
                            if (count == 1)
                                goalkeeper1 = issue.child("text").getValue().toString();
                            else
                                goalkeeper1 = "more than 1";
                        } else {
                            goalkeeper1 = "not selected";
                        }

                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (showTeam != null)
            showTeam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(goalkeeper1.equals("more than 1"))
                        Toast.makeText(getActivity(),"Only 1 Goal Keeper is allowed! ",Toast.LENGTH_SHORT).show();
                    else if(goalkeeper1.equals("not selected"))
                        Toast.makeText(getActivity(),"No Goal Keeper is selected ",Toast.LENGTH_SHORT).show();
                    else if (defender1.equals("more than 2"))
                        Toast.makeText(getActivity(),"Only 2 Defenders are allowed! ",Toast.LENGTH_SHORT).show();
                    else if (defender1.equals("not selected"))
                        Toast.makeText(getActivity(),"No Defender is selected",Toast.LENGTH_SHORT).show();
                    else if(defender1.equals("atleast 2 defenders should be selected"))
                        Toast.makeText(getActivity(),"atleast 2 Defenders should be selected",Toast.LENGTH_SHORT).show();
                    else if (attacker1.equals("more than 2"))
                        Toast.makeText(getActivity(),"Only 2 Attackers are allowed! ",Toast.LENGTH_SHORT).show();
                    else if (attacker1.equals("not selected"))
                        Toast.makeText(getActivity(),"No Attackers is selected",Toast.LENGTH_SHORT).show();
                    else if(attacker1.equals("atleast 2 attackers should be selected"))
                        Toast.makeText(getActivity(),"atleast 2 Attackers should be selected",Toast.LENGTH_SHORT).show();
                    else {


                        System.out.println("user" + NAME + "goalkeeper: " + goalkeeper1 + " " + defender1 + " " + defender2 + " " + attacker1 + " " + attacker2);
                        UsersFantacyTeam usersFantacyTeam = new UsersFantacyTeam(NAME, goalkeeper1, defender1, defender2, attacker1, attacker2);


                        String text = NAME+" You set the team as: \n\n"+"GoalKeeper: "+goalkeeper1 + " \n Defender1: " + defender1 + " \n Defender2: " + defender2 + " \nStriker1: " + attacker1 + " \n Striker2: " + attacker2;
                        showDialog(text,usersFantacyTeam);
                    }
                }
            });

    }

    private void AuthListener(){
        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    onSignedInInitialize();
                    final List<FriendlyMessage> friendlyMessages = new ArrayList<>();
                    mFlogPlayersAdapter = new FlogPlayersAdapter(getActivity(), R.layout.item_players, friendlyMessages, NAME, "silverPlayers");

                    if (mMessageListView != null)
                        mMessageListView.setAdapter(mFlogPlayersAdapter);

                } else {
                    onSignedOutInitialize();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setTheme(R.style.AppTheme)
                                    .setLogo(R.drawable.wb5)
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER
                                    ).build(),
                            RC_SIGN_IN);
                }
            }
        };
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

    private void  onSignedInInitialize(){
        attachDatabaseReadListener();
    }
    private void  onSignedOutInitialize(){

        mFlogPlayersAdapter.clear();
        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);
                    mFlogPlayersAdapter.add(friendlyMessage);
                    mProgressBar.setVisibility(View.GONE);

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
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mMessageDatabaseReference.removeEventListener(mChildEventListener);
        mChildEventListener=null;
    }
    private void showDialog(String name,final UsersFantacyTeam usersFantacyTeam) {

        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.pop_up_teams);

        TextView t1 =(TextView)dialog.findViewById(R.id.dialogText);
        t1.setText(name);

        dialog.setCanceledOnTouchOutside (false);
        Button Close = (Button) dialog.findViewById(R.id.close1);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), SelectedTeams.class);
                startActivity(i);
                dialog.dismiss();

            }
        });
        Button Close1 = (Button) dialog.findViewById(R.id.close2);
        Close1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

}