package khi.fast.log;

/**
 * Created by Hamza Ahmed on 26-Sep-17.
 */

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Hamza Ahmed on 16-Jul-17.
 */

public class FutsalFragment extends Fragment {
    AnimatorSet set;

    private TextView name;
    private LinearLayout polls;
    private LinearLayout Score;
    private LinearLayout OPCAPS;
    private LinearLayout PointsTable;

    private LinearLayout Teams;

    private String Name;
    String[] array;
    public static final int RC_SIGN_IN = 1;
    public static final String ANONYMOUS = "anonymous";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;

    Dialog dialog;
    private LinearLayout game;
    private ImageView backButton5;
    int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.cricket, container, false);

        game = (LinearLayout) view.findViewById(R.id.game);
     //   game.setBackgroundResource(R.drawable.bg_gradient14);
        polls = (LinearLayout) view.findViewById(R.id.layout1);
        Score = (LinearLayout) view.findViewById(R.id.layout2);
        OPCAPS = (LinearLayout) view.findViewById(R.id.layout3);
        PointsTable = (LinearLayout) view.findViewById(R.id.layout4);
        Teams = (LinearLayout) view.findViewById(R.id.layout6);

        backButton5 = (ImageView) view.findViewById(R.id.backButton5);
        backButton5 = (ImageView) view.findViewById(R.id.backButton5);
        backButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Check123.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().finish();
                startActivity(i);
            }
        });
        mFirebaseAuth = FirebaseAuth.getInstance();

        name = (TextView) view.findViewById(R.id.optionUsername);


        polls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), PollsFutsalActivity.class);
                i.putExtra("username", name.getText());
                System.out.println("name: " + name.getText());
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().finish();
                startActivity(i);
            }
        });
        Score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), FutsalScoreActivity.class);
                i.putExtra("username", name.getText());
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().finish();
                startActivity(i);
            }
        });
        OPCAPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MOMFutsal.class);
                i.putExtra("username", name.getText());
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().finish();
                startActivity(i);
            }
        });

        PointsTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), PTFutsalActivity.class);
                i.putExtra("username", name.getText());
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().finish();
                startActivity(i);
            }
        });

        Teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), TeamFutsal.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().finish();
                startActivity(i);
            }
        });

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    //user is signed in

                    Name = user.getDisplayName();
                    name.setText(CapsFirst(user.getDisplayName()));
                    Log.d("Name:", Name);
                    if (!Name.equals("K142805 Hamza Ahmed")) {


                    }
                    //name.setText(user.getDisplayName().toUpperCase());
                    Log.d("hamza here", "this");
                    Log.d("check", user.getDisplayName().substring(2, 3));

                } else {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setTheme(R.style.FirebaseLoginTheme)
                                    .setLogo(R.drawable.wb5)
                                    .setProviders(
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
        for (int i = 0; i < words.length; i++) {
            ret.append(Character.toUpperCase(words[i].charAt(0)));
            ret.append(words[i].substring(1));
            if (i < words.length - 1) {
                ret.append(' ');
            }
        }
        return ret.toString();
    }

    @Override
    public void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

}
