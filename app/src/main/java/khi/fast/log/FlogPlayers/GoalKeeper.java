package khi.fast.log.FlogPlayers;



/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import khi.fast.log.Activities.FlogMainActivity;
import khi.fast.log.Activities.SelectedTeams;
import khi.fast.log.Adapter.PlayerListAdapter;
import khi.fast.log.POJO.FriendlyMessage;
import khi.fast.log.POJO.Image;
import khi.fast.log.POJO.UsersFantacyTeam;
import khi.fast.log.R;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class GoalKeeper extends Fragment {

    private static final String TAG = "ProfileActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_SIGN_IN =1;
    private ListView mMessageListView;
    private TextView name;
    private PlayerListAdapter mPlayerListAdapter;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;
    public String NAME;
    private static final int RC_PHOTO_PICKER=2;
    private static final int RC_PHOTO_PICKERStories=3;
    private String mUsername;
    private ImageView Button;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private DatabaseReference mMessageDatabaseReference2;
    private DatabaseReference mTeamDatabaseReference;
    private DatabaseReference mStoriesDatabaseReference;
    private ChildEventListener mChildEventListener;
    private ChildEventListener mChildEventListener1;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private Button showTeam;
    private EditText mPriceEditText;
    private FirebaseStorage firebaseStorage;
    FirebaseUser user;
    private StorageReference mChatPhotoStorageReference;
    private StorageReference mStoriesStorageReference;
    private String side2;
    private LinearLayout l1;
    private Dialog dialog;
    private ArrayList<Image> images;
    MediaPlayer mp;

    private String goalkeeper1;
    private String defender1;
    private String defender2;
    private String attacker1;
    private String attacker2;
    private TextView goalkeeper;
    private TextView defender;
    private TextView striker;
    private ImageView backButton5;


    private String house;

    public GoalKeeper() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.flog_goalkeepers, container, false);
        mPriceEditText = (EditText) view.findViewById(R.id.priceEditText);


        backButton5=(ImageView) view.findViewById(R.id.backButton);
        backButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), FlogMainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("name1",NAME);
                startActivity(i);
            }
        });
        goalkeeper=(TextView) view.findViewById(R.id.striker);
        goalkeeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Striker.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        defender=(TextView) view.findViewById(R.id.defender);
        defender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Defender.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });


        NAME=ANONYMOUS;
        l1=(LinearLayout)view.findViewById(R.id.linearLayout);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mMessageListView = (ListView) view.findViewById(R.id.messageListView);
        mPhotoPickerButton = (ImageButton) view.findViewById(R.id.photoPickerButton);
        mMessageEditText = (EditText) view.findViewById(R.id.messageEditText);
        mSendButton = (Button) view.findViewById(R.id.sendButton);
        mUsername = ANONYMOUS;
        showTeam=(Button) view.findViewById(R.id.UserTeam);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        Bundle extra =getActivity().getIntent().getExtras();
        if(extra!=null) {
            house = extra.getString("username");

            System.out.println("name:==> " + house);
        }
        final String platinumPlayers=house+"platinumPlayers";
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("platinumPlayers");
        mMessageDatabaseReference2 =mFirebaseDatabase.getReference().child(platinumPlayers);
        mTeamDatabaseReference =mFirebaseDatabase.getReference().child("IndivisualTeams");
        mStoriesDatabaseReference =mFirebaseDatabase.getReference().child("stories");
        mChatPhotoStorageReference =firebaseStorage.getReference().child("platinumPhotos");
        mStoriesStorageReference =firebaseStorage.getReference().child("stories_pictures");

        mMessageDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                mMessageDatabaseReference2.setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null)
                        {
                            System.out.println("Copy failed");
                        }
                        else
                        {
                            System.out.println("Success");
                        }
                    }



                });
            }

            @Override
            public void onCancelled(DatabaseError firebaseError)
            {
                System.out.println("Copy failed");
            }
        });

        // Initialize references to views
        Query mHouseDatabaseReference3 =mFirebaseDatabase.getReference().child("silverPlayers").orderByChild("check");

        mHouseDatabaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count =0;
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {

                        System.out.println("issue"+issue.child("check").getValue());
                        if(issue.child("check").getValue().equals(true)) {
                            System.out.println("selected players: " + issue.child("text").getValue());
                            count++;
                            if(count==1){
                                attacker1= issue.child("text").getValue().toString();
                            }
                            else if(count==2){

                                attacker2= issue.child("text").getValue().toString();
                            }

                        }

                    }
                    if(count==0) {
                        attacker1 = "not selected";
                        attacker2 = "not selected";
                    }
                    else if(count==1){
                        attacker1 = "atleast 2 attackers should be selected";
                        attacker2 = "atleast 2 attackers should be selected";
                    }
                    else if(count>2){
                        attacker1="more than 2";
                        attacker2="more than 2";


                    }
                }




            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query mHouseDatabaseReference03 =mFirebaseDatabase.getReference().child("IndivisualTeams").orderByChild("userId");

        mHouseDatabaseReference03.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count = 0;
                    String test = "";
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {

                        System.out.println("issue" + issue.child("userId").getValue());
                        //   if(issue.child("check").getValue().equals(true)) {

                        if (issue.child("userId").getValue().equals(NAME)) {
                            test = issue.child("defender1").getValue() + "" + issue.child("defender2").getValue() + " " + issue.child("striker1").getValue() + " "
                                    + issue.child("striker2").getValue() + " " + issue.child("goli").getValue();
                        }
                        // System.out.println("selected players: " + issue.child("text").getValue());
                        //count++;
                            /*if(count==1){
                                attacker1= issue.child("text").getValue().toString();
                            }
                            else if(count==2){

                                attacker2= issue.child("text").getValue().toString();
                            }*/


                    }
                    System.out.println("Team is :" + test);


                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query mHouseDatabaseReferencegold =mFirebaseDatabase.getReference().child("goldPlayers").orderByChild("check");

        mHouseDatabaseReferencegold.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count =0;

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        System.out.println("issue"+issue.child("check").getValue());
                        if(issue.child("check").getValue().equals(true)) {
                            System.out.println("selected players: " + issue.child("text").getValue());
                            count++;
                            if(count==1){
                                defender1= issue.child("text").getValue().toString();
                            }
                            else if(count==2){

                                defender2= issue.child("text").getValue().toString();
                            }

                        }
                    }
                    if(count==0) {
                        defender1 = "not selected";
                        defender2 = "not selected";
                    }
                    else if(count==1){
                        defender1 = "atleast 2 defenders should be selected";
                        defender2 = "atleast 2 defenders should be selected";
                    }
                    else if(count>2){
                        defender1="more than 2";
                        defender2="more than 2";


                    }
                }




            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query mHouseDatabaseReferenceplatinum =mFirebaseDatabase.getReference().child("platinumPlayers").orderByChild("check");

        mHouseDatabaseReferenceplatinum.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count=0;
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        System.out.println("issue"+issue.child("check").getValue());
                        if(issue.child("check").getValue().equals(true)) {
                            count++;
                            System.out.println("selected players: " + issue.child("text").getValue());
                            if(count==1)
                                goalkeeper1= issue.child("text").getValue().toString();
                            else
                                goalkeeper1= "more than 1";
                        }
                        else{
                            goalkeeper1="not selected";
                        }

                    }
                }




            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        Query mHouseDatabaseReference2 =mFirebaseDatabase.getReference().child("stories");

        mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Image image1 = new Image();
                        image1.setImage_ID(issue.child("image_ID").getValue().toString());
                        images.add(0,image1);
                    }
                }




            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        if (mPhotoPickerButton != null)
            mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                    startActivityForResult(intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);

                }
            });



        if (mMessageEditText != null) {
            mMessageEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().trim().length() > 0) {
                        mSendButton.setEnabled(true);
                    } else {
                        mSendButton.setEnabled(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }

            });
            mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        }
        if (mPriceEditText != null) {
            mPriceEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().trim().length() > 0) {
                        mSendButton.setEnabled(true);
                    } else {
                        mSendButton.setEnabled(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }

            });
            mPriceEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        }
        // Send button sends a message and clears the EditText
        if (mSendButton != null)
            mSendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String unique=mMessageEditText.getText().toString(); //Hamza Ahmed
                    String concat1=unique.substring(unique.length() - 1).concat(unique); // dHamza Ahmed
                    String string = concat1.replaceAll("\\s+"," "); //dHamzaAhmed
                    String size = String.valueOf(concat1.length()); //dHamzaAhmed12
                    String key=string.concat(size);
                    String price=mPriceEditText.getText().toString();
                    String finalKey=price.concat(key);
                    System.out.println("key: "+key);
                    FriendlyMessage friendlyMessage = new FriendlyMessage(mMessageEditText.getText().toString(), mUsername, null,mPriceEditText.getText().toString(),false, finalKey);
                    mMessageDatabaseReference.push().setValue(friendlyMessage);
                    mMessageEditText.setText("");
                }
            });

        if (showTeam != null)
            showTeam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(goalkeeper1.equals("more than 1"))
                        Toast.makeText(getActivity(),"Only 1 Goal Keeper is allowed! ",Toast.LENGTH_SHORT).show();
                    else if(goalkeeper1.equals("not selected"))
                        Toast.makeText(getActivity(),"No Goal Keeper is selected ",Toast.LENGTH_SHORT).show();
                    else if (defender1.equals("more than 2"))
                        Toast.makeText(getActivity(),"Only 2 Defenders are allowed! ",Toast.LENGTH_SHORT).show();
                    else if (defender1.equals("not selected"))
                        Toast.makeText(getActivity(),"No Defender is selected",Toast.LENGTH_SHORT).show();
                    else if(defender1.equals("atleast 2 defenders should be selected"))
                        Toast.makeText(getActivity(),"atleast 2 Defenders should be selected",Toast.LENGTH_SHORT).show();
                    else if (attacker1.equals("more than 2"))
                        Toast.makeText(getActivity(),"Only 2 Attackers are allowed! ",Toast.LENGTH_SHORT).show();
                    else if (attacker1.equals("not selected"))
                        Toast.makeText(getActivity(),"No Attackers is selected",Toast.LENGTH_SHORT).show();
                    else if(attacker1.equals("atleast 2 attackers should be selected"))
                        Toast.makeText(getActivity(),"atleast 2 Attackers should be selected",Toast.LENGTH_SHORT).show();
                    else {


                        System.out.println("user" + NAME + "goalkeeper: " + goalkeeper1 + " " + defender1 + " " + defender2 + " " + attacker1 + " " + attacker2);
                        UsersFantacyTeam usersFantacyTeam = new UsersFantacyTeam(NAME, goalkeeper1, defender1, defender2, attacker1, attacker2);



                        String text = NAME+" You set the team as: \n\n"+"GoalKeeper: "+goalkeeper1 + " \n Defender1: " + defender1 + " \n Defender2: " + defender2 + " \nStriker1: " + attacker1 + " \n Striker2: " + attacker2;
                        showDialog(text,usersFantacyTeam);
                    }
                }
            });

        if (mPhotoPickerButton != null)
            mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                    startActivityForResult(intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);

                }
            });


        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    NAME = user.getDisplayName();

                    if (!NAME.equals("K142805 Hamza Ahmed")) {

                        l1.setVisibility(View.GONE);

                    }

//                    name.setText(user.getDisplayName());
                    final List<FriendlyMessage> friendlyMessages = new ArrayList<>();
                    mPlayerListAdapter = new PlayerListAdapter(getActivity(), R.layout.item_players, friendlyMessages, NAME,"platinumPlayers");

                    if (mMessageListView != null)
                        mMessageListView.setAdapter(mPlayerListAdapter);

                    images = new ArrayList<>();

                    ;


                } else {
                    onSignedOutInitialize();

                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setTheme(R.style.FirebaseLoginTheme)
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

        return view;
    }




  /*  public void moveFirebaseRecord(DatabaseReference fromPath, final DatabaseReference toPath)
    {
        fromPath.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                toPath.setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener()
                {
                    @Override
                    public void onComplete(FirebaseError firebaseError, DatabaseReference firebase)
                    {
                        if (firebaseError != null)
                        {
                            System.out.println("Copy failed");
                        }
                        else
                        {
                            System.out.println("Success");
                        }
                    }
                });
            }

            @Override
            public void onCancelled(FirebaseError firebaseError)
            {
                System.out.println("Copy failed");
            }
        });
    }*/

    public ArrayList<Image> getmMatch(){

        return images;
    }


    @Override
    public void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
        mPlayerListAdapter.clear();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
            } else if (resultCode == RESULT_CANCELED) {
            }
        }
        else if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            StorageReference photoRef =
                    mChatPhotoStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL = taskSnapshot.getDownloadUrl();
                            FriendlyMessage friendlyMessage = new FriendlyMessage(null, mUsername, downloadURL.toString(),null,false, null);
                            mMessageDatabaseReference.push().setValue(friendlyMessage);



                        }
                    });
        }
        else if (requestCode == RC_PHOTO_PICKERStories && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            StorageReference photoRef =
                    mStoriesStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL = taskSnapshot.getDownloadUrl();
                            Image image = new Image(null, downloadURL.toString());
                            mStoriesDatabaseReference.push().setValue(image);
                        }
                    });
        }
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
        mPlayerListAdapter.clear();

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);


                    mPlayerListAdapter.add(friendlyMessage);
                    mProgressBar.setVisibility(View.GONE);


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
        if(mChildEventListener1==null) {
            mChildEventListener1 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    UsersFantacyTeam usersFantacyTeam = dataSnapshot.getValue(UsersFantacyTeam.class);
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
            mTeamDatabaseReference.addChildEventListener(mChildEventListener1);
            mTeamDatabaseReference.addValueEventListener(new ValueEventListener() {
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
        if(mChildEventListener1!=null)
            mTeamDatabaseReference.removeEventListener(mChildEventListener1);
        mChildEventListener1=null;
    }
    private void showDialog(String name,final UsersFantacyTeam usersFantacyTeam) {
        // custom dialog
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.pop_up_teams);

        // set the custom dialog components - text, image and button

        // Close Button

        // Buy Button

        TextView t1 =(TextView)dialog.findViewById(R.id.dialogText);
        t1.setText(name);

        dialog.setCanceledOnTouchOutside (false);
        Button Close = (Button) dialog.findViewById(R.id.close1);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), SelectedTeams.class);
                i.putExtra("NAME",NAME);
                startActivity(i);
                mTeamDatabaseReference.push().setValue(usersFantacyTeam);
                dialog.dismiss();

            }
        });
        Button Close1 = (Button) dialog.findViewById(R.id.close2);
        Close1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                //Intent i = new Intent(Sil,GoldPlayers.class);
                //getContext().startActivity(i);


            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


}