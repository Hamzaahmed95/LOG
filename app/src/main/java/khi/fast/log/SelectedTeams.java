package khi.fast.log;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

/**
 * Created by Hamza Ahmed on 09-Oct-17.
 */

public class SelectedTeams extends AppCompatActivity {



    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private DatabaseReference mMessageDatabaseReference;
    private String house;
    private TextView GoalKeeper;
    private TextView Defender1;
    private TextView Defender2;
    private TextView Striker1;
    private TextView Striker2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectedteam);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        Bundle extra =this.getIntent().getExtras();

        GoalKeeper=(TextView)findViewById(R.id.goalkeeper1);
        Defender1=(TextView)findViewById(R.id.defender1);
        Defender2=(TextView)findViewById(R.id.defender2);

        Striker1 =(TextView)findViewById(R.id.striker1);
        Striker2 =(TextView)findViewById(R.id.striker2);


        if(extra!=null) {
            house = extra.getString("NAME");

            System.out.println("name:==> " + house);
        }

        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("IndivisualTeams");

        Query mHouseDatabaseReferenceteam =mFirebaseDatabase.getReference().child("IndivisualTeams").orderByChild("userId");

        mHouseDatabaseReferenceteam.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count = 0;
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        //System.out.println("issue"+issue.child("check").getValue());
                        if (issue.child("userId").getValue().equals(house)) {

                            System.out.println("Defender1 " + issue.child("defender1").getValue());
                            System.out.println("Defender2 " + issue.child("defender2").getValue());
                            System.out.println("Striker1 " + issue.child("striker1").getValue());
                            System.out.println("Striker2 " + issue.child("striker2").getValue());
                            System.out.println("Goal Keeper " + issue.child("goalkeeper").getValue());
                            GoalKeeper.setText(issue.child("goalkeeper").getValue().toString());
                            Defender1.setText(issue.child("defender1").getValue().toString());
                            Defender2.setText(issue.child("defender2").getValue().toString());
                            Striker1.setText(issue.child("striker1").getValue().toString());
                            Striker2.setText(issue.child("striker2").getValue().toString());

                        }
                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
}
