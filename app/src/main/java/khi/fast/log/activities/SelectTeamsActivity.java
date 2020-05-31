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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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


    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_teams);
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

        Button = (ImageView) findViewById(R.id.backButton);
        settings = getSharedPreferences("teams", 0);
        extras = getIntent().getExtras();

        if (extras != null) {
            Team = extras.getString("TEAM");
        }
        TAGS = settings.getString("TAG", "CRIC");

    }

    private void handleClickListener() {
        Button.setOnClickListener(new View.OnClickListener() {
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

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        if((issue.child("game").getValue()!=null)){
                            if (issue.child("game").getValue().toString().equalsIgnoreCase(TAGS)) {
                                Image image1 = new Image();
                                image1.setImage_ID(issue.child("image_ID").getValue().toString());
                                images.add(image1);
                            }
                        }


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

                    adapter = new TeamAdapter(SelectTeamsActivity.this, images);

                    if (recyclerView != null)
                        recyclerView.setAdapter(adapter);
                } else {


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


}