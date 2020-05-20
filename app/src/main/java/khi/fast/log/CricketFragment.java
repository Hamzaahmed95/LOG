package khi.fast.log;

/**
 * Created by Hamza Ahmed on 26-Sep-17.
 */

import android.animation.AnimatorSet;
import android.content.Intent;
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

/**
 * Created by Hamza Ahmed on 16-Jul-17.
 */

public class CricketFragment extends Fragment {
    AnimatorSet set;
    private TextView name;
    private LinearLayout polls;
    private LinearLayout Score;
    private LinearLayout OPCAPS;
    private LinearLayout PointsTable;
    private LinearLayout Teams;

    private ImageView backButton5;
    private FirebaseAuth mFirebaseAuth;
    private LinearLayout game;
    private FirebaseAuth.AuthStateListener mAuthStateListner;



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


        View view = inflater.inflate(R.layout.cricket, container, false);
        game=(LinearLayout)view.findViewById(R.id.game);
        game.setBackgroundResource(R.drawable.bg_gradient14);
        polls = (LinearLayout)view.findViewById(R.id.layout1);
        Score = (LinearLayout)view.findViewById(R.id.layout2);
        OPCAPS = (LinearLayout)view.findViewById(R.id.layout3);
        PointsTable = (LinearLayout)view.findViewById(R.id.layout4);
        Teams = (LinearLayout)view.findViewById(R.id.layout6);
        name=(TextView)view.findViewById(R.id.optionUsername);


        backButton5=(ImageView)view.findViewById(R.id.backButton5);
        backButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LogOverviewActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });



        mFirebaseAuth = FirebaseAuth.getInstance();



        polls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),PollingActivity.class);
                i.putExtra("username",name.getText());

                startActivity(i);
            }
        });
        Score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),CricketScoreCardActivity.class);
                i.putExtra("username",name.getText());

                startActivity(i);
            }
        });
        OPCAPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),MOM.class);
                i.putExtra("username",name.getText());
                startActivity(i);
            }
        });

        PointsTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),PointsTableActivity.class);
                i.putExtra("username",name.getText());
                startActivity(i);
            }
        });

        Teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),Teams.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().finish();
                startActivity(i);
            }
        });



        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    //user is signed in


                    name.setText(CapsFirst(user.getDisplayName()));


                }
            };
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



    public void onStart() {
        super.onStart();
    }


    @Override
    public void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

}
