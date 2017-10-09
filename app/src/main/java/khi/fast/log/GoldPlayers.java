package khi.fast.log;



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
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
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
import java.util.UUID;


public class GoldPlayers extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_SIGN_IN =1;
    private ListView mMessageListView;
    private TextView name;
    private PlayerListAdapter1 mPlayerListAdapter1;
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
    private TextView striker;
    private ImageView backButton5;


    @Override
    protected void onCreate( final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.flog_defenders);
        mPriceEditText = (EditText) findViewById(R.id.priceEditText);


        backButton5=(ImageView)findViewById(R.id.backButton);
        backButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GoldPlayers.this,FlogMainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        goalkeeper=(TextView)findViewById(R.id.goalkeeper);
        goalkeeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GoldPlayers.this,PlatinumPlayers.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        striker=(TextView)findViewById(R.id.striker);
        striker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GoldPlayers.this,SilverPlayers.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });


        NAME=ANONYMOUS;
        l1=(LinearLayout)findViewById(R.id.linearLayout);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mMessageListView = (ListView) findViewById(R.id.messageListView);
        mPhotoPickerButton = (ImageButton) findViewById(R.id.photoPickerButton);
        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
        mSendButton = (Button) findViewById(R.id.sendButton);
        mUsername = ANONYMOUS;
        showTeam=(Button)findViewById(R.id.UserTeam);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        System.out.println("name:==> "+NAME);
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("goldPlayers");
        mTeamDatabaseReference =mFirebaseDatabase.getReference().child("IndivisualTeams");
        mStoriesDatabaseReference =mFirebaseDatabase.getReference().child("stories");
        mChatPhotoStorageReference =firebaseStorage.getReference().child("goldPhotos");
        mStoriesStorageReference =firebaseStorage.getReference().child("stories_pictures");



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
                    FriendlyMessage friendlyMessage = new FriendlyMessage(mMessageEditText.getText().toString(), mUsername, null,Integer.parseInt(mPriceEditText.getText().toString()),false, finalKey);
                    mMessageDatabaseReference.push().setValue(friendlyMessage);
                    mMessageEditText.setText("");
                }
            });

        if (showTeam != null)
            showTeam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(goalkeeper1.equals("more than 1"))
                        Toast.makeText(GoldPlayers.this,"Only 1 Goal Keeper is allowed! ",Toast.LENGTH_SHORT).show();
                    else if(goalkeeper1.equals("not selected"))
                        Toast.makeText(GoldPlayers.this,"No Goal Keeper is selected ",Toast.LENGTH_SHORT).show();
                    else if (defender1.equals("more than 2"))
                        Toast.makeText(GoldPlayers.this,"Only 2 Defenders are allowed! ",Toast.LENGTH_SHORT).show();
                    else if (defender1.equals("not selected"))
                        Toast.makeText(GoldPlayers.this,"No Defender is selected",Toast.LENGTH_SHORT).show();
                    else if(defender1.equals("atleast 2 defenders should be selected"))
                        Toast.makeText(GoldPlayers.this,"atleast 2 Defenders should be selected",Toast.LENGTH_SHORT).show();
                    else if (attacker1.equals("more than 2"))
                        Toast.makeText(GoldPlayers.this,"Only 2 Attackers are allowed! ",Toast.LENGTH_SHORT).show();
                    else if (attacker1.equals("not selected"))
                        Toast.makeText(GoldPlayers.this,"No Attackers is selected",Toast.LENGTH_SHORT).show();
                    else if(attacker1.equals("atleast 2 attackers should be selected"))
                        Toast.makeText(GoldPlayers.this,"atleast 2 Attackers should be selected",Toast.LENGTH_SHORT).show();
                    else {


                        System.out.println("user" + NAME + "goalkeeper: " + goalkeeper1 + " " + defender1 + " " + defender2 + " " + attacker1 + " " + attacker2);
                        UsersFantacyTeam usersFantacyTeam = new UsersFantacyTeam(NAME, goalkeeper1, defender1, defender2, attacker1, attacker2);


                        String text = NAME + " \n" + goalkeeper1 + " \n" + defender1 + " \n" + defender2 + " \n" + attacker1 + " \n" + attacker2;
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
                    mPlayerListAdapter1 = new PlayerListAdapter1(GoldPlayers.this, R.layout.item_players, friendlyMessages, NAME);

                    if (mMessageListView != null)
                        mMessageListView.setAdapter(mPlayerListAdapter1);

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

    }
    public ArrayList<Image> getmMatch(){

        return images;
    }


    @Override
    protected void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
        mPlayerListAdapter1.clear();
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
                    (this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL = taskSnapshot.getDownloadUrl();
                            FriendlyMessage friendlyMessage = new FriendlyMessage(null, mUsername, downloadURL.toString(),0,false, null);
                            mMessageDatabaseReference.push().setValue(friendlyMessage);



                        }
                    });
        }
        else if (requestCode == RC_PHOTO_PICKERStories && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            StorageReference photoRef =
                    mStoriesStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
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
    protected void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

    private void  onSignedInInitialize(String username){
        mUsername = username;
        attachDatabaseReadListener();

    }
    private void  onSignedOutInitialize(){
        mUsername = ANONYMOUS;
        mPlayerListAdapter1.clear();

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);


                    mPlayerListAdapter1.add(friendlyMessage);
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
        dialog = new Dialog(this);
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

                Intent i = new Intent(GoldPlayers.this,SelectedTeams.class);
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