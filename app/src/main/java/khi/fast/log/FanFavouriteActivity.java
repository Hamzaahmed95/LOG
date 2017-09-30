package khi.fast.log;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
    int count=0;

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

                        if (issue.child("favTeam").getValue().equals("Nawait Sultan")) {
                            count++;

                        } else {

                        }
                    }
                    System.out.println("count = "+count);
                    ViewGroup.LayoutParams params = v1.getLayoutParams();
                    params.height = count;
                    v1.setLayoutParams(params);
                   // v1.setLayoutParams(new LinearLayout.LayoutParams(200, 120));


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
