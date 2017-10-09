package khi.fast.log;

import android.app.Dialog;
import android.content.Intent;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamza Ahmed on 07-Oct-17.
 */

public class FlogMainActivity extends AppCompatActivity {

    private LinearLayout pickTeam;
    private Dialog dialog;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private ImageView backbutton5;
    private String NAME;
    private LinearLayout l1;

    private ChildEventListener mChildEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flog_main_activity);
        l1=(LinearLayout)findViewById(R.id.selectedplayer);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FlogMainActivity.this,SelectedTeams.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
        mFirebaseAuth = FirebaseAuth.getInstance();
        pickTeam=(LinearLayout)findViewById(R.id.pickTeam);
        backbutton5=(ImageView)findViewById(R.id.backButton);
        backbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FlogMainActivity.this,Check123.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(i);
            }
        });

        pickTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(NAME);
            }
        });
        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //user is signed in

                    System.out.println("=>hamza here "+NAME);
                   onSignedInInitialize(user.getDisplayName());
                    NAME = user.getDisplayName();
                 //   onSignedInInitialize(NAME);





                }
            }

        };



    }
    @Override
    protected void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }
    @Override
    protected void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
    }
    private void  onSignedInInitialize(String username){
        NAME = username;
        attachDatabaseReadListener();

    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);


                    //mPlayerListAdapter2.add(friendlyMessage);
                    //mProgressBar.setVisibility(View.GONE);


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
                  }



    }


    private void showDialog(final String name) {
        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.text1);

        // set the custom dialog components - text, image and button

        // Close Button

        // Buy Button

        TextView t1 =(TextView)dialog.findViewById(R.id.dialogText);
        t1.setText("You have 100 coins to buy 8 players \n 1 Goal Keeper \n 4 Defenders \n 3 Strikers");

        dialog.setCanceledOnTouchOutside (false);
        System.out.println("name:==> " + NAME);
        Button Close = (Button) dialog.findViewById(R.id.close1);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                Intent i = new Intent(FlogMainActivity.this,PlatinumPlayers.class);



                i.putExtra("username",NAME);
                startActivity(i);


            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
