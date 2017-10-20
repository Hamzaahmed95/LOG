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
import android.support.v4.app.FragmentManager;
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

public class CricketFragment extends Fragment {
    AnimatorSet set;
    AnimatorSet set2;
    private TextView name;
    private LinearLayout polls;
    private LinearLayout Score;
    private LinearLayout OPCAPS;
    private LinearLayout PointsTable;
    private LinearLayout Teams;
    private ImageView signout;
    private String Name;
    String[] array;
    AnimatorSet set3;
    private FirebaseDatabase mFirebaseDatabase;
    private ImageView backButton5;
    private DatabaseReference mMessageDatabaseReference;
    private DatabaseReference mUsersDatabaseReference;
    public static final int RC_SIGN_IN =1;
    public static final String ANONYMOUS = "anonymous";
    private ChildEventListener mChildEventListener;
    private String mUsername;
    private String UserName;
    private FirebaseAuth mFirebaseAuth;
    private LinearLayout game;
    private FirebaseAuth.AuthStateListener mAuthStateListner;


    private FirebaseStorage firebaseStorage;

    private StorageReference mPollsPhotoStorageReference;

    private DatabaseReference mHouseDatabaseReference;

    AnimatorSet set4;
    AnimatorSet set5;
    AnimatorSet set6;
    LinearLayout layout01;
    AnimatorSet set7;
    AnimatorSet set8;
    AnimatorSet set9;
    Dialog dialog;
    int i;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getFragmentManager().getBackStackEntryCount() == 0) {
                    getActivity().finish();
                    Intent i = new Intent(getActivity(),Check123.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(getActivity(),Check123.class);
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
      //  signout =(ImageView)view.findViewById(R.id.logout);

        array = new String[1000];
        i=0;
        backButton5=(ImageView)view.findViewById(R.id.backButton5);
        backButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),Check123.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().finish();
                startActivity(i);
            }
        });
        Name =ANONYMOUS;
        //  mHouseDatabaseReference =mFirebaseDatabase.getReference().child("house");


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();

        name=(TextView)view.findViewById(R.id.optionUsername);
        firebaseStorage = FirebaseStorage.getInstance();
        mUsername = ANONYMOUS;
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("messages");
        mPollsPhotoStorageReference =firebaseStorage.getReference().child("Polls_photos");
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("users");
        Log.d("oncreate ",mMessageDatabaseReference.getDatabase().toString());

/*        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance().signOut(getActivity());
            }
        });
*/
        Query mHouseDatabaseReference2 =mFirebaseDatabase.getReference().child("house").orderByChild("username");

        mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"

                        System.out.println(issue.child("username").getValue().equals(mUsername));
                        array[i]=issue.child("username").getValue().toString();
                        i++;

                    }
                    for(int j=0;j<i;j++){
                        System.out.println(j+""+array[j]);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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


     /*   Bundle b=getActivity().getIntent().getExtras();
        String[] array=b.getStringArray("users");
        for(int j=0;j<array.length;j++){
            System.out.println(j+""+array[j]);
        }
*/
        Bundle extra =getActivity().getIntent().getExtras();
        if(extra!=null) {
            String house = extra.getString("batch");
            if(house.equals("green"))
                //Log.d("house",house);
                if(house.equals("green")){
                    layout01.setBackgroundColor(getResources().getColor(R.color.greenshades1));

                    Log.d("hzma green","house");
                }
                else if(house.equals("lightBlue")){

                    layout01.setBackgroundResource(R.color.lightBlue);
                    layout01.setBackgroundColor(getResources().getColor(R.color.lightBlue));

                    Log.d("lightBlue ",house);

                }
                else if(house.equals("purple")){
                    layout01.setBackgroundColor(getResources().getColor(R.color.purple));

                    Log.d("purple ",house);
                }
                else if(house.equals("red")){
                    layout01.setBackgroundColor(getResources().getColor(R.color.redshades1));
                    Log.d("red ",house);

                }
                else if(house.equals("peach")){
                    layout01.setBackgroundColor(getResources().getColor(R.color.peach));

                    Log.d("peach ",house);

                }
                else if(house.equals("yellow")){
                    layout01.setBackgroundColor(getResources().getColor(R.color.yellow));

                    Log.d("yellow ",house);

                }
                else{

                }

        }

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    Name=user.getDisplayName();
                    name.setText(CapsFirst(user.getDisplayName()));
                    Log.d("Name:",Name);
                    if(!Name.equals("K142805 Hamza Ahmed")){

                     
                    }
                    //name.setText(user.getDisplayName().toUpperCase());
                    Log.d("hamza here","this");
                    Log.d("check",user.getDisplayName().substring(2,3));

                }else{
                    //user is signed out
                    onSignedOutInitialize();

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
    private boolean isFirstTime()
    {
        SharedPreferences preferences = getActivity().getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }


    @Override
    public void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
    }

    public void onStart() {
        super.onStart();

       /* if(isFirstTime()) {

            set = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(), R.animator.flip);
            set2 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(), R.animator.flip1);
            set.setTarget(Polls);
            set2.setTarget(getScore);
            set.start();
            set2.start();
            set3 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(), R.animator.flip2);
            set4 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(), R.animator.flip4);
            set3.setTarget(getMatches);
            set4.setTarget(getTeams);
            set3.start();
            set4.start();
            set5 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(), R.animator.flip3);
            set6 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(), R.animator.flip5);
            set5.setTarget(Matches);
            set6.setTarget(getScore1);
            set5.start();
            set6.start();
            set7 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(), R.animator.flip6);
            set8 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(), R.animator.flip7);
            set7.setTarget(getMatches1);
            set8.setTarget(getTeams1);
            set7.start();
            set8.start();
            set9 = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(), R.animator.flip8);
            set9.setTarget(MOM);
            set9.start();
        }
*/
    }



    @Override
    public void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }
    private void  onSignedInInitialize(String username){
        mUsername = username;
        attachDatabaseReadListener();

    }
    private void  onSignedOutInitialize(){
        mUsername = ANONYMOUS;

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);
                    String splited = friendlyMessage.getName();


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    FriendlyMessage f =dataSnapshot.getValue(FriendlyMessage.class);
                    Log.d("ooo = ",f.getName());
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
        }
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null)
            mMessageDatabaseReference.removeEventListener(mChildEventListener);
        mChildEventListener=null;
    }

}
