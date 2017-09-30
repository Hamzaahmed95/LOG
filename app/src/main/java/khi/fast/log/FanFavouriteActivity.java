package khi.fast.log;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import static android.R.attr.width;
import static khi.fast.log.R.attr.height;

/**
 * Created by Hamza Ahmed on 30-Sep-17.
 */

public class FanFavouriteActivity extends AppCompatActivity {

    View v1;
    View v2;
    View v3;
    View v4;
    View v5;
    View v6;
    View v7;


    private FirebaseDatabase mFirebaseDatabase;

    private FirebaseStorage firebaseStorage;

    private FirebaseAuth mFirebaseAuth;

    private ImageView backButton5;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;
    private TextView t6;
    private TextView t7;
    int count=0;
    int count1=0;
    int count2=0;
    int count3=0;
    int count4=0;
    int count5=0;
    int count6=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barchart);
        v1= findViewById(R.id.v1);
        v2= findViewById(R.id.v2);
        v3= findViewById(R.id.v3);
        v4= findViewById(R.id.v4);
        v5= findViewById(R.id.v5);
        v6= findViewById(R.id.v6);
        v7= findViewById(R.id.v7);
        t1 = (TextView)findViewById(R.id.poll1);
        t2 = (TextView)findViewById(R.id.poll2);
        t3 = (TextView)findViewById(R.id.poll3);
        t4 = (TextView)findViewById(R.id.poll4);
        t5 = (TextView)findViewById(R.id.poll5);
        t6 = (TextView)findViewById(R.id.poll6);
        t7 = (TextView)findViewById(R.id.poll7);
        firebaseStorage = FirebaseStorage.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        backButton5=(ImageView)findViewById(R.id.backButton5);
        backButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FanFavouriteActivity.this,Check123.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(i);
            }
        });

        Query mHouseDatabaseReference23 = mFirebaseDatabase.getReference().child("house");


        mHouseDatabaseReference23.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {

                        if (issue.child("favTeam").getValue().equals("Stags")) {
                            count=count+4;
                        }
                        else if(issue.child("favTeam").getValue().equals("Dragons")) {
                            count1=count1+4;;
                        }
                        else if(issue.child("favTeam").getValue().equals("Dires")) {
                            count2=count2+4;;
                        }
                        else if(issue.child("favTeam").getValue().equals("Falcons")) {
                            count3=count3+4;;
                        }
                        else if(issue.child("favTeam").getValue().equals("Pythons")) {
                            count4=count4+4;;
                        }
                        else if(issue.child("favTeam").getValue().equals("Hunters")) {
                            count5=count5+4;;
                        }
                        else if(issue.child("favTeam").getValue().equals("Jaguars")){
                            count6=count6+4;
                        }
                    }
                    System.out.println("count = "+count);
                    ViewGroup.LayoutParams params = v1.getLayoutParams();
                    params.height = count;
                    v1.setLayoutParams(params);
                    ViewGroup.LayoutParams params1 = v2.getLayoutParams();
                    params1.height = count1;
                    v2.setLayoutParams(params1);
                    ViewGroup.LayoutParams params2 = v3.getLayoutParams();
                    params2.height = count2;
                    v3.setLayoutParams(params2);
                    ViewGroup.LayoutParams params3 = v4.getLayoutParams();
                    params3.height = count3;
                    v4.setLayoutParams(params3);
                    ViewGroup.LayoutParams params4 = v5.getLayoutParams();
                    params4.height = count4;
                    v5.setLayoutParams(params4);
                    ViewGroup.LayoutParams params5 = v6.getLayoutParams();
                    params5.height = count5;
                    v6.setLayoutParams(params5);
                    ViewGroup.LayoutParams params6 = v7.getLayoutParams();
                    params6.height = count6;
                    v7.setLayoutParams(params6);
                   // v1.setLayoutParams(new LinearLayout.LayoutParams(200, 120));

                    t1.setText(String.valueOf(count/4));
                    t2.setText(String.valueOf(count1/4));
                    t3.setText(String.valueOf(count2/4));
                    t4.setText(String.valueOf(count3/4));
                    t5.setText(String.valueOf(count4/4));
                    t6.setText(String.valueOf(count5/4));
                    t7.setText(String.valueOf(count6/4));


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
