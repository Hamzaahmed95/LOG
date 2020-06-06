package khi.fast.log.activities;

/**
 * Created by Hamza Ahmed on 28-Sep-17.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.util.ArrayList;

import khi.fast.log.adapter.TeamAdapter;
import khi.fast.log.log_details.LogDetailsActivity;
import khi.fast.log.model.Image;
import khi.fast.log.R;
import khi.fast.log.utils.Utils;


public class SelectTeamsActivity extends AppCompatActivity {

    private static final String TAG = "SelectTeamsActivity";
    public static final int RC_SIGN_IN = 1;
    private ImageView Button;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private TeamAdapter adapter;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    FirebaseUser user;
    private RecyclerView recyclerView;
    private ArrayList<Image> images;

    private LinearLayout stags;
    private String TAGS = "";
    private String Team = "";
    SharedPreferences settings;
    Bundle extras;
    TextView optionUsername;
    ImageView backbutton;
    private DatabaseReference mStoriesDatabaseReference;
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_teams);
        System.out.println("SelectTeamsActivity: 123");
        initialization();
        handleClickListener();
        queryingData();
    }
    private void initialization() {
        stags = (LinearLayout) findViewById(R.id.stags);
       // stags.setBackgroundResource(R.drawable.bg_gradient14);

        recyclerView = (RecyclerView) findViewById(R.id.nawaitJanbaz);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        images = new ArrayList<>();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mStoriesDatabaseReference =mFirebaseDatabase.getReference().child("stags");

        Button = (ImageView) findViewById(R.id.backButton);
        settings = getSharedPreferences("teams", 0);
        extras = getIntent().getExtras();

        if (extras != null) {
            Team = extras.getString("TEAM");
        }
        TAGS = Utils.getStringPref("TAG");
        System.out.println("Teams: "+Team+" "+TAGS);
        optionUsername = (TextView)findViewById(R.id.optionUsername);
        optionUsername.setText("Players");
        optionUsername.setTextColor(getResources().getColor(R.color.common_text_color));
        backbutton = (ImageView)findViewById(R.id.backButton5);

    }

    private void handleClickListener() {
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(SelectTeamsActivity.this, LogDetailsActivity.class,true);
            }
        });
    }

    private void queryingData() {

        Query mHouseDatabaseReference2 = mFirebaseDatabase.getReference().child(Team);

        mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    System.out.println("SelectTeamsActivity: 0");
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        if((issue.child("game").getValue()!=null)){
                            System.out.println("SelectTeamsActivity: 1");
                            if (issue.child("game").getValue().toString().equalsIgnoreCase(TAGS)) {
                                Image image1 = new Image();
                                image1.setImage_ID(issue.child("image_ID").getValue().toString());
                                images.add(image1);
                            }
                        }
                        System.out.println("SelectTeamsActivity: 2"+images);


                    }
                }
                adapter = new TeamAdapter(SelectTeamsActivity.this, getmMatch());
                recyclerView.setAdapter(adapter);
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
                    onSignedInInitialize();
                    System.out.println("SelectTeamsActivity1: images= "+images);
                    adapter = new TeamAdapter(SelectTeamsActivity.this, images);

                    if (recyclerView != null)
                        recyclerView.setAdapter(adapter);
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

            ;
        };
    }


    public ArrayList<Image> getmMatch() {

        return images;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListner != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }
    private void  onSignedInInitialize(){

        attachDatabaseReadListener();

    }
    private void  onSignedOutInitialize(){

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    System.out.println("SelectTeamsActivity: we here"+ dataSnapshot);
                    System.out.println("SelectTeamsActivity1: images= "+images);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

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
            mStoriesDatabaseReference.addChildEventListener(mChildEventListener);
            mStoriesDatabaseReference.addValueEventListener(new ValueEventListener() {
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
            mStoriesDatabaseReference.removeEventListener(mChildEventListener);
        mChildEventListener=null;
    }


}