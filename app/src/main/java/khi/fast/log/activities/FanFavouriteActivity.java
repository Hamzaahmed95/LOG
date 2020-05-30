package khi.fast.log.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import khi.fast.log.R;

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
    private LinearLayout l1;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String count00 ="";
    public static final String count01 ="";
    public static final String count02 ="";
    public static final String count03 ="";
    public static final String count04 ="";
    public static final String count05 ="";
    public static final String count06 ="";
    private static final String hamza="";
    private ProgressBar mProgressBar;

    private SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barchart);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        v1= findViewById(R.id.v1);
        v2= findViewById(R.id.v2);
        v3= findViewById(R.id.v3);
        v4= findViewById(R.id.v4);
        v5= findViewById(R.id.v5);
        v6= findViewById(R.id.v6);
        v7= findViewById(R.id.v7);
        l1=(LinearLayout)findViewById(R.id.layoutMiss);

        l1.setVisibility(View.GONE);
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
                Intent i = new Intent(FanFavouriteActivity.this, LogOverviewActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(i);
            }
        });
        System.out.println("in main count0 ="+count);


        if(isNetworkAvailable()) {
            Query mHouseDatabaseReference23 = mFirebaseDatabase.getReference().child("house");
            System.out.println("Hamza Ahmed1 : we here 0 "+mHouseDatabaseReference23);


            mHouseDatabaseReference23.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        System.out.println("Hamza Ahmed1 : we here 1 "+dataSnapshot);
                        // dataSnapshot is the "issue" node with all children with id 0
                        for (DataSnapshot issue : dataSnapshot.getChildren()) {

                            mProgressBar.setVisibility(View.GONE);
                            l1.setVisibility(View.VISIBLE);

                            if (issue.child("favTeam").getValue().equals("Stags")) {
                                count = count + 4;
                            } else if (issue.child("favTeam").getValue().equals("Dragons")) {
                                count1 = count1 + 4;
                                ;
                            } else if (issue.child("favTeam").getValue().equals("Dires")) {
                                count2 = count2 + 4;
                                ;
                            } else if (issue.child("favTeam").getValue().equals("Falcons")) {
                                count3 = count3 + 4;
                                ;
                            } else if (issue.child("favTeam").getValue().equals("Pythons")) {
                                count4 = count4 + 4;
                                ;
                            } else if (issue.child("favTeam").getValue().equals("Hunters")) {
                                count5 = count5 + 4;
                                ;
                            } else if (issue.child("favTeam").getValue().equals("Jaguars")) {
                                count6 = count6 + 4;
                            }
                        }
                        System.out.println("count = " + count);
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

                        t1.setText(String.valueOf(count / 4));
                        t2.setText(String.valueOf(count1 / 4));
                        t3.setText(String.valueOf(count2 / 4));
                        t4.setText(String.valueOf(count3 / 4));
                        t5.setText(String.valueOf(count4 / 4));
                        t6.setText(String.valueOf(count5 / 4));
                        t7.setText(String.valueOf(count6 / 4));

                        String p1=String.valueOf(count);
                        String p2=String.valueOf(count1);
                        String p3=String.valueOf(count2);
                        String p4=String.valueOf(count3);
                        String p5=String.valueOf(count4);
                        String p6=String.valueOf(count5);
                        String p7=String.valueOf(count6);
                        sharedPref = getPreferences(MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("poll1", p1);
                        editor.putString("poll2", p2);
                        editor.putString("poll3", p3);
                        editor.putString("poll4", p4);
                        editor.putString("poll5", p5);
                        editor.putString("poll6", p6);
                        editor.putString("poll7", p7);
                        editor.apply();



                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("Hamza Ahmed1 : we here 4 "+databaseError);
                }
            });
        }
        else{
            System.out.println("Hamza Ahmed1 : we here 2");
            mProgressBar.setVisibility(View.GONE);
            l1.setVisibility(View.VISIBLE);
            sharedPref = getPreferences(MODE_PRIVATE);
            String p1 = sharedPref.getString("poll1", "");
            String p2 = sharedPref.getString("poll2", "");
            String p3 = sharedPref.getString("poll3", "");
            String p4 = sharedPref.getString("poll4", "");
            String p5 = sharedPref.getString("poll5", "");
            String p6 = sharedPref.getString("poll6", "");
            String p7 = sharedPref.getString("poll7", "");
            ViewGroup.LayoutParams params = v1.getLayoutParams();
            params.height = Integer.parseInt(p1);
            v1.setLayoutParams(params);
            ViewGroup.LayoutParams params1 = v2.getLayoutParams();
            params1.height =  Integer.parseInt(p2);
            v2.setLayoutParams(params1);
            ViewGroup.LayoutParams params2 = v3.getLayoutParams();
            params2.height =  Integer.parseInt(p3);
            v3.setLayoutParams(params2);
            ViewGroup.LayoutParams params3 = v4.getLayoutParams();
            System.out.println("hamza here: "+Integer.parseInt(p4));
            params3.height =  Integer.parseInt(p4);
            v4.setLayoutParams(params3);
            ViewGroup.LayoutParams params4 = v5.getLayoutParams();
            params4.height =  Integer.parseInt(p5);
            v5.setLayoutParams(params4);
            ViewGroup.LayoutParams params5 = v6.getLayoutParams();
            params5.height =  Integer.parseInt(p6);
            v6.setLayoutParams(params5);
            ViewGroup.LayoutParams params6 = v7.getLayoutParams();
            params6.height =  Integer.parseInt(p7);
            v7.setLayoutParams(params6);

            System.out.println("poll1-> "+p4);
            t1.setText(String.valueOf(Integer.parseInt(p1)/4));
            t2.setText(String.valueOf(Integer.parseInt(p2)/4));
            t3.setText(String.valueOf(Integer.parseInt(p3)/4));
            t4.setText(String.valueOf(Integer.parseInt(p4)/4));
            t5.setText(String.valueOf(Integer.parseInt(p5)/4));
            t6.setText(String.valueOf(Integer.parseInt(p6)/4));
            t7.setText(String.valueOf(Integer.parseInt(p7)/4));
        }

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
