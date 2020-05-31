package khi.fast.log.log_details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import khi.fast.log.R;
import khi.fast.log.activities.SelectTeamsActivity;
import khi.fast.log.utils.Utils;

public class TeamsFragment extends Fragment {



    private TextView text;
    private LinearLayout stags;
    private LinearLayout dragons;
    private LinearLayout jaguars;
    private LinearLayout falcons;
    private LinearLayout hunters;
    private LinearLayout dires;

    ProgressBar mprogressBar;
    private ImageView backButton6;
    String name1;
    private LinearLayout team;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    public TeamsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teams_overview, container, false);

        initializations(view);
        handleClickListener();

        return view;
    }
    private void initializations(View view) {
        team = (LinearLayout) view.findViewById(R.id.team);

        backButton6 = (ImageView) view.findViewById(R.id.backButton6);
        mFirebaseAuth = FirebaseAuth.getInstance();
        stags = (LinearLayout) view.findViewById(R.id.stags);
        dragons = (LinearLayout) view.findViewById(R.id.dragons);
        jaguars = (LinearLayout) view.findViewById(R.id.jaguars);
        falcons = (LinearLayout) view.findViewById(R.id.falcons);
        hunters = (LinearLayout) view.findViewById(R.id.hunters);
        dires = (LinearLayout) view.findViewById(R.id.dires);
    }
    private void handleClickListener(){
        backButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(getActivity(), LogDetailsActivity.class,true);
            }
        });


        stags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(getActivity(), SelectTeamsActivity.class,"TEAM","selected_teams",true);
            }
        });
        dragons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(getActivity(),SelectTeamsActivity.class,"TEAM","dragons",true);
            }
        });
        jaguars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(getActivity(),SelectTeamsActivity.class,"TEAM","jaguars",true);
            }
        });
        falcons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(getActivity(),SelectTeamsActivity.class,"TEAM","falcons",true);

            }
        });
        hunters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(getActivity(),SelectTeamsActivity.class,"TEAM","hunters",true);

            }
        });
        dires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(getActivity(),SelectTeamsActivity.class,"TEAM","dires",true);
            }
        });

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    name1 = user.getDisplayName();
                }
            }
        };
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mAuthStateListner != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
    }

    public void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

}


