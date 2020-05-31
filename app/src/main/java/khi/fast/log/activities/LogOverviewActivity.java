package khi.fast.log.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
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

import khi.fast.log.fan_favourite.FanFavouriteActivity;
import khi.fast.log.model.IndivisualPoints;
import khi.fast.log.R;
import khi.fast.log.utils.Utils;
import khi.fast.log.log_details.LogDetailsActivity;

import static khi.fast.log.utils.Constants.LOG_OVERVIEW_BADMINTON_TEXT;
import static khi.fast.log.utils.Constants.LOG_OVERVIEW_BASKETBALL_MESSAGE;
import static khi.fast.log.utils.Constants.LOG_OVERVIEW_BASKETBALL_TEXT;
import static khi.fast.log.utils.Constants.LOG_OVERVIEW_CRICKET_TEXT;
import static khi.fast.log.utils.Constants.LOG_OVERVIEW_FFT_TEXT;
import static khi.fast.log.utils.Constants.LOG_OVERVIEW_FLOG_TEXT;
import static khi.fast.log.utils.Constants.LOG_OVERVIEW_FUTSAL_TEXT;
import static khi.fast.log.utils.Constants.LOG_OVERVIEW_TABLE_TEXT;
import static khi.fast.log.utils.Constants.LOG_OVERVIEW_TAG;
import static khi.fast.log.utils.Constants.LOG_OVERVIEW_THROW_TEXT;
import static khi.fast.log.utils.Constants.LOG_OVERVIEW_VOLLEY_TEXT;

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
    TextView Cricket;
    TextView Futsal;
    TextView Basketball;
    TextView Badminton;
    TextView Volley;
    TextView TableTennis;
    TextView Throw;
    TextView FFT;
    TextView Flog;
    SwitchCompat switch_button;
    Handler handler;


    private FirebaseDatabase mFirebaseDatabase;
    private ImageView signout;
    public static final int RC_SIGN_IN = 1;
    private ChildEventListener mChildEventListener;
    private DatabaseReference IndivisualPointsDB;
    private FirebaseAuth mFirebaseAuth;
    private Dialog dialog;
    private FirebaseAuth.AuthStateListener mAuthStateListner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        initialization();
        settingValue();
        handleClickListener();
        AuthListener();
    }

    private void AuthListener() {

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    onSignedInInitialize(user.getDisplayName());
                    name1 = user.getDisplayName();

                } else {


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
        IndivisualPointsDB = mFirebaseDatabase.getReference().child("IndivisualPoints");
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
        Cricket = (TextView) findViewById(R.id.Cricket);
        Futsal = (TextView) findViewById(R.id.Futsal);
        Basketball = (TextView) findViewById(R.id.Basketball);
        FFT = (TextView) findViewById(R.id.FFT);
        TableTennis = (TextView) findViewById(R.id.Table);
        Throw = (TextView) findViewById(R.id.Throw);
        Volley = (TextView) findViewById(R.id.Volley);
        Badminton = (TextView) findViewById(R.id.Badminton);
        Flog = (TextView) findViewById(R.id.FLOG);
        switch_button = (SwitchCompat) findViewById(R.id.switch_button);
        handler = new Handler();
    }

    private void settingValue() {
        Cricket.setText(LOG_OVERVIEW_CRICKET_TEXT);
        Futsal.setText(LOG_OVERVIEW_FUTSAL_TEXT);
        Basketball.setText(LOG_OVERVIEW_BASKETBALL_TEXT);
        FFT.setText(LOG_OVERVIEW_FFT_TEXT);
        TableTennis.setText(LOG_OVERVIEW_TABLE_TEXT);
        Throw.setText(LOG_OVERVIEW_THROW_TEXT);
        Volley.setText(LOG_OVERVIEW_VOLLEY_TEXT);
        Badminton.setText(LOG_OVERVIEW_BADMINTON_TEXT);
        Flog.setText(LOG_OVERVIEW_FLOG_TEXT);
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
                Utils.startingActivity(LogOverviewActivity.this, LogDetailsActivity.class, LOG_OVERVIEW_TAG, "log_details", false);
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
                Utils.startingActivity(LogOverviewActivity.this, LogDetailsActivity.class, LOG_OVERVIEW_TAG, "futsal", false);
            }
        });

        badminton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(LogOverviewActivity.this, FlogMainActivity.class, LOG_OVERVIEW_TAG, "badminton", false);
            }
        });

        switch_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                }, 1500);
            }
        });
        flog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isFirstTime(view.getContext())) {
                    IndivisualPoints indivisualPoints = new IndivisualPoints(name1, 0, 0, 0);
                    IndivisualPointsDB.push().setValue(indivisualPoints);
                    Utils.startingActivity(LogOverviewActivity.this, SplashScreenFLOG.class, "", "", false);

                } else {
                    Utils.startingActivity(LogOverviewActivity.this, FlogMainActivity.class, "name1", name1, false);
                }

            }
        });


        tabletennis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(LogOverviewActivity.this, LogDetailsActivity.class, LOG_OVERVIEW_TAG, "tt", false);
            }
        });


        fanfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(LogOverviewActivity.this, FanFavouriteActivity.class, "", "", false);
            }
        });

        volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(LogOverviewActivity.this, LogDetailsActivity.class, LOG_OVERVIEW_TAG, "volley", false);
            }
        });


        throwball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(LogOverviewActivity.this, LogDetailsActivity.class, LOG_OVERVIEW_TAG, "throwball", false);
            }
        });
    }

    @Override
    public void onPause() {

        super.onPause();
        if (mAuthStateListner != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
    }

    @Override
    public void onResume() {

        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

    private void onSignedInInitialize(String username) {
        attachDatabaseReadListener();
    }

    private void startingActivity(String extra) {
        Utils.startingActivity(LogOverviewActivity.this, LogDetailsActivity.class, LOG_OVERVIEW_TAG, extra, false);
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

            IndivisualPointsDB.addChildEventListener(mChildEventListener);
            IndivisualPointsDB.addValueEventListener(new ValueEventListener() {
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


        if (mChildEventListener != null)
            IndivisualPointsDB.removeEventListener(mChildEventListener);
        mChildEventListener = null;

    }



    private void showDialog() {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.text1);

        TextView t1 = (TextView) dialog.findViewById(R.id.dialogText);
        t1.setText(LOG_OVERVIEW_BASKETBALL_MESSAGE);

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
