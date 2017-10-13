package khi.fast.log;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Hamza Ahmed on 12-Oct-17.
 */

public class ResultFantasyActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN =1 ;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;
    private TextView t6;
    String name="";
    String name1;
    int count=0;
    private ImageView backbutton5;
    private FirebaseDatabase mFirebaseDatabase;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fantasyrank);
        t1=(TextView)findViewById(R.id.rank1);
        t2=(TextView)findViewById(R.id.rank2);
        t3=(TextView)findViewById(R.id.rank3);
        t4=(TextView)findViewById(R.id.point1);
        t5=(TextView)findViewById(R.id.point2);
        t6=(TextView)findViewById(R.id.point3);
        backbutton5 = (ImageView) findViewById(R.id.backButton);
        backbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ResultFantasyActivity.this, FlogMainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("name1",name1);
                startActivity(i);
            }
        });

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        Query mHouseDatabaseReference233 = mFirebaseDatabase.getReference().child("IndivisualPoints").orderByChild("points").limitToLast(3);


        mHouseDatabaseReference233.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList a1 = new ArrayList();
                    int count=0;
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        count++;
                        System.out.println("sadia "+issue.child("points").getValue().toString());
                        System.out.println("sadia2 "+issue.child("name").getValue().toString());
                        if(count==1){
                            t3.setText(issue.child("name").getValue().toString());
                            t6.setText(issue.child("points").getValue().toString());

                        }
                        else if(count==2){
                            t2.setText(issue.child("name").getValue().toString());
                            t5.setText(issue.child("points").getValue().toString());
                        }
                        else{
                            t1.setText(issue.child("name").getValue().toString());
                            t4.setText(issue.child("points").getValue().toString());

                        }
                     //   System.out.println("sheryy pagal"+issue.child("name").getValue());

                    }
                  /*  Collections.reverse(a1);

                    StringBuffer text = new StringBuffer();

                    for(int i=0;i<a1.size();i++){
                        Ranks=Ranks.concat(a1.get(i).toString()+"-");

                    }



                    //  markdisplayTextArea.setText(text.toString());
                    System.out.println("After Reverse Order, ArrayList Contains : " + Ranks);
                    int count=0;
                    for (int i=0;i<Ranks.length();i++){

                        if(Ranks.charAt(i)=='-'){
                            Ranks=Ranks.replace(Ranks.charAt(i),'\n');
                        }
                    }
                    System.out.println("After Reverse : " + Ranks);


*/
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query mHouseDatabaseReference = mFirebaseDatabase.getReference().child("IndivisualPoints").orderByChild("points");


        mHouseDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList a1 = new ArrayList();


                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        count++;
                       // System.out.println("hamza " + issue.child("points").getValue().toString());
                        System.out.println("count01: " + count);

                    }
                    System.out.println("total count: " + count);

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {

                      //  System.out.println("hamza " + issue.child("points").getValue().toString());
                        System.out.println("count02: " + count);
                        issue.getRef().child("rank").setValue(count);
                        count--;

                    }
                    System.out.println("total count: " + count);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    //user is signed in
                   // onSignedInInitialize(user.getDisplayName());
                    name1=user.getDisplayName();

                }else{
                    //user is signed out
                    //onSignedOutInitialize();

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
            };
        };


    }
    @Override
    public void onPause(){
        super.onPause();
        if(mAuthStateListner!=null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
       // detachDatabaseReadListener();
    }
    @Override
    public void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }


}
