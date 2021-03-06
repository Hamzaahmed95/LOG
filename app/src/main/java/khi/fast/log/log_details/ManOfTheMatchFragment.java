package khi.fast.log.log_details;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import khi.fast.log.R;
import khi.fast.log.log_details.LogDetailsActivity;

import khi.fast.log.adapter.MOMAdapter;
import khi.fast.log.model.MOMCLASS;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class ManOfTheMatchFragment extends Fragment {

    private static final String TAG = "MOM";

    public static final String ANONYMOUS = "anonymous";

    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    public static final int RC_SIGN_IN =1;

    private ListView mmessageListViewMOM;

    private ImageView closeButton;

    private MOMAdapter mMOMAdapter;

    private ProgressBar mProgressBar;

    private ImageButton mphotoPickerButtonMOM;

    private EditText mmessageEditTextMOM;

    private android.widget.Button mSendButton;
    public String NAME;

    private static final int RC_PHOTO_PICKER =  2;

    private String mUsername;
    private ImageView Button;
    private List<String> notes;
    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mMessageDatabaseReference;

    private ChildEventListener mChildEventListener;

    private FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListner;

    private FirebaseStorage firebaseStorage;

    private StorageReference mChatPhotoStorageReference;
    private String name;
    private String runs;
    private RelativeLayout mom;
    ProgressBar mprogressBar;

    private TextView text;
    public ManOfTheMatchFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mom, container, false);

        NAME=ANONYMOUS;

        notes = new ArrayList<String>();
        mUsername = ANONYMOUS;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        closeButton = (ImageView) view.findViewById(R.id.backButtonMOM);

        mom = (RelativeLayout)view.findViewById(R.id.mom);

        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("mom");
        mChatPhotoStorageReference =firebaseStorage.getReference().child("mom_photos");
      //  Log.d("oncreate ",mMessageDatabaseReference.getDatabase().toString());



        // Initialize references to views
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mmessageListViewMOM = (ListView) view.findViewById(R.id.messageListViewMOM);
        mphotoPickerButtonMOM = (ImageButton) view.findViewById(R.id.photoPickerButtonMOM);
        mmessageEditTextMOM = (EditText) view.findViewById(R.id.messageEditTextMOM);
        mSendButton = (android.widget.Button) view.findViewById(R.id.sendButtonMOM);

        // Initialize message ListView and its adapter

        // Initialize progress bar


        // ImagePickerButton shows an image picker to upload a image for a message
        if(mphotoPickerButtonMOM!=null)
            mphotoPickerButtonMOM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER);

                }
            });

        // Enable Send button when there's text to send
        if(mmessageEditTextMOM!=null) {
            mmessageEditTextMOM.addTextChangedListener(new TextWatcher() {
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
            mmessageEditTextMOM.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        }
        // Send button sends a message and clears the EditText
        if(mSendButton!=null)
            mSendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String score = mmessageEditTextMOM.getText().toString();
                    String [] arr = score.split("-");
                    name=arr[0];
                    runs=arr[1];

                    // TODO: Send messages on click
                    MOMCLASS MOMCLASS = new MOMCLASS(name,runs,null);

                    // Clear input box
                    mMessageDatabaseReference.push().setValue(MOMCLASS);
                    mmessageEditTextMOM.setText("");


                }
            });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LogDetailsActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });
        Bundle extra =getActivity().getIntent().getExtras();
        if(extra!=null) {
            String url2 = extra.getString("username");
            if(url2!=null){
                Log.d("hamza: ",url2);
                if(!url2.equals("K142805 Hamza Ahmed")){
                    //  Log.d(TAG, ""+url2);
                    mSendButton.setVisibility(View.GONE);
                    mphotoPickerButtonMOM.setVisibility(View.GONE);
                    mmessageEditTextMOM.setVisibility(View.GONE);
                }
            }
//
        }


        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    final List<MOMCLASS> momclasses = new ArrayList<>();
                    mMOMAdapter = new MOMAdapter(getActivity(), R.layout.item_message, momclasses);
                    if(mmessageListViewMOM!=null)
                        mmessageListViewMOM.setAdapter(mMOMAdapter);



                }else{
                    //user is signed out
                    onSignedOutInitialize();

                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setTheme(R.style.AppTheme)
                                    .setLogo(R.drawable.wb5)
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER
                                    ).build(),
                            RC_SIGN_IN);




                }
            };
        };


        return view;
    }

    @Override
    public void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
        detachDatabaseReadListener();
        mMOMAdapter.clear();
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==RC_SIGN_IN){
            if(resultCode ==RESULT_OK){
                Toast.makeText(getContext(),"Signed in!",Toast.LENGTH_SHORT).show();
            }
            else if(resultCode == RESULT_CANCELED){
                Toast.makeText(getContext(),"Sign in cancelled",Toast.LENGTH_SHORT).show();

            }





            //upload file to firebase storage
        }
        else if(requestCode == RC_PHOTO_PICKER && resultCode==RESULT_OK){
            Uri selectedImageUri= data.getData();
            StorageReference photoRef =
                    mChatPhotoStorageReference.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri).addOnSuccessListener
                    (getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadURL =taskSnapshot.getDownloadUrl();
                            MOMCLASS momclass = new MOMCLASS(null,null,downloadURL.toString());
                            mMessageDatabaseReference.push().setValue(momclass);
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
        mMOMAdapter.clear();

        detachDatabaseReadListener();
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    MOMCLASS momclass = dataSnapshot.getValue(MOMCLASS.class);
                    if(momclass!=null)
                        mMOMAdapter.add(momclass);
                    System.out.println(dataSnapshot.child("picture").getValue());
                    mProgressBar.setVisibility(View.GONE);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    MOMCLASS f =dataSnapshot.getValue(MOMCLASS.class);

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

                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                        MOMCLASS mom =noteDataSnapshot.getValue(MOMCLASS.class);
                        String pictures[] = new String[100];
                        //  Log.d("mom ",""+mom.getPICTURE());

                        notes.add(mom.getPICTURE());
                    }
                    for (String s:notes){
                     //   Log.d("hamza1234",s);

                    }





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
    }
}


