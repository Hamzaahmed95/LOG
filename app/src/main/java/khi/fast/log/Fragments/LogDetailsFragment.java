package khi.fast.log.Fragments;

/**
 * Created by Hamza Ahmed on 26-Sep-17.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import khi.fast.log.Activities.LogOverviewActivity;
import khi.fast.log.Activities.MOM;
import khi.fast.log.Activities.PointsTableActivity;
import khi.fast.log.Activities.PollingActivity;
import khi.fast.log.Activities.TeamsOverview;
import khi.fast.log.R;
import khi.fast.log.Scoring.CricketScoreCardActivity;
import khi.fast.log.Utils.Utils;

import static khi.fast.log.Utils.Constants.LOG_Details_MOM_TEXT;
import static khi.fast.log.Utils.Constants.LOG_Details_POINTS_TABLE_TEXT;
import static khi.fast.log.Utils.Constants.LOG_Details_POLLS_TEXT;
import static khi.fast.log.Utils.Constants.LOG_Details_SCORE_TEXT;
import static khi.fast.log.Utils.Constants.LOG_Details_TEAMS_TEXT;

/**
 * Created by Hamza Ahmed on 16-Jul-17.
 */

public class LogDetailsFragment extends Fragment {

    private TextView name;
    private LinearLayout Polls;
    private LinearLayout Score;
    private LinearLayout OPCAPS;
    private LinearLayout PointsTable;
    private LinearLayout Teams;

    private TextView polls;
    private TextView score;
    private TextView man_of_the_match;
    private TextView points_table;
    private TextView teams;

    private ImageView backButton5;
    private FirebaseAuth mFirebaseAuth;
    private LinearLayout game;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private String TAG = "";
    SharedPreferences.Editor editor;
    Bundle extra;

    SharedPreferences settings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getFragmentManager().getBackStackEntryCount() == 0) {
                    getActivity().finish();
                    Intent i = new Intent(getActivity(), LogOverviewActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(getActivity(), LogOverviewActivity.class);
                    startActivity(i);

                }
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.log_details, container, false);
        initialization(view);
        settingValues();
        handleClickListener();

        return view;
    }

    public void initialization(View view){
        mFirebaseAuth = FirebaseAuth.getInstance();
        game=(LinearLayout)view.findViewById(R.id.game);
        Polls = (LinearLayout)view.findViewById(R.id.layout1);
        Score = (LinearLayout)view.findViewById(R.id.layout2);
        OPCAPS = (LinearLayout)view.findViewById(R.id.layout3);
        PointsTable = (LinearLayout)view.findViewById(R.id.layout4);
        Teams = (LinearLayout)view.findViewById(R.id.layout6);
        name=(TextView)view.findViewById(R.id.optionUsername);
        polls=(TextView)view.findViewById(R.id.polls);
        score=(TextView)view.findViewById(R.id.score) ;
        man_of_the_match=(TextView)view.findViewById(R.id.man_of_the_match);
        points_table = (TextView)view.findViewById(R.id.points_table);
        teams=(TextView)view.findViewById(R.id.teams);
        backButton5=(ImageView)view.findViewById(R.id.backButton5);
        settings = getActivity().getSharedPreferences("teams",0);
        editor = settings.edit();
        extra =getActivity().getIntent().getExtras();
    }

    private void settingValues(){
      //  game.setBackgroundResource(R.drawable.bg_gradient14);

        if(extra!=null) {
            TAG = extra.getString("TAG");
            editor.putString("TAG",TAG);
            editor.commit();
        }

        polls.setText(LOG_Details_POLLS_TEXT);
        score.setText(LOG_Details_SCORE_TEXT);
        man_of_the_match.setText(LOG_Details_MOM_TEXT);
        points_table.setText(LOG_Details_POINTS_TABLE_TEXT);
        teams.setText(LOG_Details_TEAMS_TEXT);
    }



    private void handleClickListener(){

        backButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(getActivity(),LogOverviewActivity.class,true);
            }
        });

        Polls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(getActivity(),PollingActivity.class,"username",name.getText().toString(),false);
            }
        });

        Score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(getActivity(),CricketScoreCardActivity.class,"username",name.getText().toString(),false);
            }
        });

        OPCAPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(getActivity(),MOM.class,"username",name.getText().toString(),false);
            }
        });

        PointsTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(getActivity(),PointsTableActivity.class,"username",name.getText().toString(),false);

            }
        });

        Teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(getActivity(),TeamsOverview.class,"username",name.getText().toString(),false);
            }
        });

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    name.setText(Utils.CapsFirst(user.getDisplayName()));
                }
            }
        };
    }

    public void onStart() {
        super.onStart();
    }


    @Override
    public void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

}
