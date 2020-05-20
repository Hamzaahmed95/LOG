package khi.fast.log;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Hamza Ahmed on 25-Sep-17.
 */

public class LogOverviewActivity extends AppCompatActivity {


    LinearLayout cricket;
    LinearLayout basketball;
    LinearLayout futsal;
    LinearLayout badminton;
    LinearLayout volley;
    LinearLayout throwball;
    LinearLayout tabletennis;
    LinearLayout flog;
    LinearLayout fanfav;
    String name1;

    private FirebaseDatabase mFirebaseDatabase;
    private ImageView signout;
    public static final int RC_SIGN_IN = 1;
    private ChildEventListener mChildEventListener;
    private DatabaseReference mMessageDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private Dialog dialog;
    private FirebaseAuth.AuthStateListener mAuthStateListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        initialization();
        handleClickListener();
        AuthListener();
    }

    private void AuthListener() {

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    System.out.println("Hamza Ahmed: user signedIn" + user.getDisplayName());
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    name1 = user.getDisplayName();

                } else {
                    //user is signed out
                    onSignedOutInitialize();
                    System.out.println("Hamza Ahmed: user signed out");
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
            }
        };
    }

    private void initialization() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("IndivisualPoints");


        mFirebaseAuth = FirebaseAuth.getInstance();
        signout = (ImageView) findViewById(R.id.logout);
        flog = (LinearLayout) findViewById(R.id.flog);
        cricket = (LinearLayout) findViewById(R.id.cricket);
        basketball = (LinearLayout) findViewById(R.id.basketball);
        futsal = (LinearLayout) findViewById(R.id.futsal);
        badminton = (LinearLayout) findViewById(R.id.badminton);
        tabletennis = (LinearLayout) findViewById(R.id.tabletennis);
        fanfav = (LinearLayout) findViewById(R.id.fanfav);
        volley = (LinearLayout) findViewById(R.id.volley);
        throwball = (LinearLayout) findViewById(R.id.throwball);
    }

    private void handleClickListener() {
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance().signOut(LogOverviewActivity.this);
            }
        });


        cricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogOverviewActivity.this, CricketActivity.class);
                startActivity(i);
            }
        });


        basketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        futsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogOverviewActivity.this, CricketActivity.class);
                startActivity(i);
            }
        });

        badminton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogOverviewActivity.this, CricketActivity.class);
                startActivity(i);
            }
        });


        if (isFirstTime()) {
            flog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    IndivisualPoints indivisualPoints = new IndivisualPoints(name1, 0, 0, 0);
                    mMessageDatabaseReference.push().setValue(indivisualPoints);
                    Intent i = new Intent(LogOverviewActivity.this, SplashScreenFLOG.class);
                    startActivity(i);
                    finish();
                }
            });
        } else {

            flog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(LogOverviewActivity.this, FlogMainActivity.class);
                    i.putExtra("name1", name1);
                    startActivity(i);
                }
            });

        }


        tabletennis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogOverviewActivity.this, CricketActivity.class);
                startActivity(i);
            }
        });


        fanfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogOverviewActivity.this, FanFavouriteActivity.class);
                startActivity(i);
            }
        });

        volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogOverviewActivity.this, CricketActivity.class);
                startActivity(i);
            }
        });


        throwball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogOverviewActivity.this, CricketActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public void onPause() {
        System.out.println("Hamza Ahmed: onPause");
        super.onPause();
        if (mAuthStateListner != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
    }

    @Override
    public void onResume() {
        System.out.println("Hamza Ahmed: onResume");
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

    private void onSignedInInitialize(String username) {
        //mUsername = username;

        System.out.println("Hamza Ahmed: onSignedInInitialize");
        attachDatabaseReadListener();

    }

    private void onSignedOutInitialize() {

        System.out.println("Hamza Ahmed: onSignedOutInitialize");

    }


    private boolean isFirstTime() {
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

        System.out.println("Hamza Ahmed: attachDatabaseReadListener");
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

    }


    private void detachDatabaseReadListener() {

        System.out.println("Hamza Ahmed: detachDatabaseReadListener");
        if (mChildEventListener != null)
            mMessageDatabaseReference.removeEventListener(mChildEventListener);
        mChildEventListener = null;

    }


    private void showDialog() {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.text1);


        System.out.println("Hamza Ahmed: showDialog");
        TextView t1 = (TextView) dialog.findViewById(R.id.dialogText);
        t1.setText("Due to Some reasons, Basketball matches will not be played!");

        dialog.setCanceledOnTouchOutside(false);

        Button Close = (Button) dialog.findViewById(R.id.close1);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });
        Close.setText("Close");

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
