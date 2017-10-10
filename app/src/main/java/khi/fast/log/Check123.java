package khi.fast.log;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

/**
 * Created by Hamza Ahmed on 25-Sep-17.
 */

public class Check123 extends AppCompatActivity {


    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout1;
    LinearLayout layout7;
    LinearLayout layout4;
    LinearLayout layout5;
    LinearLayout layout6;
    LinearLayout layout8;
    LinearLayout layout9;
    String name1;

    private FirebaseDatabase mFirebaseDatabase;
    private ImageView signout;
    public static final int RC_SIGN_IN =1;
    private ChildEventListener mChildEventListener;
    private ChildEventListener mChildEventListener2;
    private DatabaseReference mMessageDatabaseReference;
    private DatabaseReference mMessageDatabaseReference2;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("IndivisualPoints");
        mMessageDatabaseReference2 =mFirebaseDatabase.getReference().child("IndivisualRank");

        mFirebaseAuth = FirebaseAuth.getInstance();
        signout=(ImageView)findViewById(R.id.logout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance().signOut(Check123.this);
            }
        });


        layout2 = (LinearLayout) findViewById(R.id.lay2);
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Check123.this, CricketActivity.class);
                startActivity(i);
            }
        });

        layout3 = (LinearLayout) findViewById(R.id.lay3);
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Check123.this, BasketballActivity.class);
                startActivity(i);
            }
        });
        layout1 = (LinearLayout) findViewById(R.id.lay1);
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Check123.this, FutsalActivity.class);
                startActivity(i);
            }
        });
        layout7 = (LinearLayout) findViewById(R.id.lay7);
        layout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Check123.this, BadmintonActivity.class);
                startActivity(i);
            }
        });
        layout8 = (LinearLayout) findViewById(R.id.lay8);
        if (isFirstTime()) {
            layout8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    IndivisualPoints indivisualPoints = new IndivisualPoints(name1,0);
                    IndivisualRanks indivisualRank = new IndivisualRanks(name1,0);
                    mMessageDatabaseReference.push().setValue(indivisualPoints);
                    mMessageDatabaseReference2.push().setValue(indivisualRank);
                    Intent i = new Intent(Check123.this, SplashScreenFLOG.class);
                    startActivity(i);
                }
            });
        }else{

            layout8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(Check123.this, FlogMainActivity.class);
                    i.putExtra("name1",name1);
                    startActivity(i);
                }
            });

        }


        layout6 = (LinearLayout) findViewById(R.id.lay6);
        layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Check123.this, TableTennisActivity.class);
                startActivity(i);
            }
        });

        layout9 = (LinearLayout) findViewById(R.id.lay9);
        layout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Check123.this, FanFavouriteActivity.class);
                startActivity(i);
            }
        });
        layout4 = (LinearLayout) findViewById(R.id.lay4);
        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Check123.this, VolleyballActivity.class);
                startActivity(i);
            }
        });

        layout5 = (LinearLayout) findViewById(R.id.lay5);
        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Check123.this, ThrowballActivity.class);
                startActivity(i);
            }
        });

        if (isFirstTime()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay1).setVisibility(View.VISIBLE);
                }
            }, 100);


            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay2).setVisibility(View.VISIBLE);
                }
            }, 300);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay3).setVisibility(View.VISIBLE);
                }
            }, 500);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay9).setVisibility(View.VISIBLE);
                }
            }, 750);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay6).setVisibility(View.VISIBLE);
                }
            }, 1000);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay5).setVisibility(View.VISIBLE);
                }
            }, 1250);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay4).setVisibility(View.VISIBLE);
                }
            }, 1600);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay7).setVisibility(View.VISIBLE);
                }
            }, 1900);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    //findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.lay8).setVisibility(View.VISIBLE);
                }
            }, 2100);

        }
        else{
            findViewById(R.id.lay1).setVisibility(View.VISIBLE);
            findViewById(R.id.lay2).setVisibility(View.VISIBLE);
            findViewById(R.id.lay3).setVisibility(View.VISIBLE);
            findViewById(R.id.lay4).setVisibility(View.VISIBLE);
            findViewById(R.id.lay5).setVisibility(View.VISIBLE);
            findViewById(R.id.lay6).setVisibility(View.VISIBLE);
            findViewById(R.id.lay7).setVisibility(View.VISIBLE);
            findViewById(R.id.lay8).setVisibility(View.VISIBLE);
            findViewById(R.id.lay9).setVisibility(View.VISIBLE);
        }




        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    name1=user.getDisplayName();

                }else{
                    //user is signed out
                    onSignedOutInitialize();

                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setTheme(R.style.FirebaseLoginTheme)
                                    .setLogo(R.drawable.floggg)
                                    .setProviders(
                                            AuthUI.GOOGLE_PROVIDER
                                    ).build(),
                            RC_SIGN_IN);

                }
            };
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

    private void  onSignedInInitialize(String username){
        //mUsername = username;
        attachDatabaseReadListener();

    }
    private void  onSignedOutInitialize(){
        //mUsername = ANONYMOUS;

       // detachDatabaseReadListener();
    }


    private boolean isFirstTime()
    {
        SharedPreferences preferences = this.getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }
    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    IndivisualPoints indivisualPoints = dataSnapshot.getValue(IndivisualPoints.class);


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
        if (mChildEventListener2 == null) {
            mChildEventListener2 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    IndivisualPoints indivisualPoints = dataSnapshot.getValue(IndivisualPoints.class);


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
            mMessageDatabaseReference2.addChildEventListener(mChildEventListener2);
            mMessageDatabaseReference2.addValueEventListener(new ValueEventListener() {
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
        if(mChildEventListener2!=null)
            mMessageDatabaseReference.removeEventListener(mChildEventListener2);
        mChildEventListener2=null;
    }
}
