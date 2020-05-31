package khi.fast.log.fragments;

/**
 * Created by Hamza Ahmed on 25-Sep-17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import khi.fast.log.activities.LogOverviewActivity;
import khi.fast.log.model.House;
import khi.fast.log.R;
import khi.fast.log.utils.Constants;

import static khi.fast.log.utils.Constants.QUESTION_ACTIVITY_BUTTON;
import static khi.fast.log.utils.Constants.QUESTION_ACTIVITY_FAV_PLAYER_TEXT;

public class QuestionFragment extends Fragment {


    public static final int RC_SIGN_IN = 1;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference House;
    private String usernameArray[];
    private String house;
    private String favouriteTeam;
    private String favouriteBatsman;
    private String mUsername;

    Button SendButtonQuestion;
    EditText FavPlayer;
    TextView Username;
    Spinner BestTeam;
    String[] items;
    ArrayAdapter<String> BestTeamAdapter;
    Query GetUserName;
    View view;

    int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.question_activity, container, false);
        initialization(view);
        settingValue();
        handleClickListener();

        return view;
    }

    private void initialization(View view) {
        FavPlayer = (EditText) view.findViewById(R.id.FavPlayer);
        Username = (TextView) view.findViewById(R.id.Username);
        BestTeam = (Spinner) view.findViewById(R.id.favTeam);
        SendButtonQuestion = (Button) view.findViewById(R.id.sendButtonQuesion);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        usernameArray = new String[250];
    }

    private void settingValue() {
        i = 0;
        House = mFirebaseDatabase.getReference().child("house");
        GetUserName = mFirebaseDatabase.getReference().child("house").orderByChild("username");
        items = Constants.QUESTION_ACTIVITY_FAV_TEAM;
        BestTeamAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_style, items);
        BestTeam.setAdapter(BestTeamAdapter);
        BestTeam.setPrompt("select");
        FavPlayer.setHint(QUESTION_ACTIVITY_FAV_PLAYER_TEXT);
        SendButtonQuestion.setText(QUESTION_ACTIVITY_BUTTON);
    }

    private void handleClickListener() {

//        GetUserName.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//
//                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
//                        if(issue.child("username").getValue().toString()!=null){
//                            usernameArray[i] = issue.child("username").getValue().toString();
//                            i++;
//                        }
//
//
//                    }
//                    for (int j = 0; j < i; j++) {
//                        System.out.println(j + "" + usernameArray[j]);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        BestTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                switch (position) {

                    case 0:
                        // set editbox visible
                        Log.d("case : ", " " + items[position]);
                        favouriteTeam = items[position];
                        house = "green";

                        break;
                    case 1:
                        // set editbox invivible
                        Log.d("case : ", " " + position);
                        favouriteTeam = items[position];
                        house = "lightBlue";

                        break;
                    case 2:
                        // set editbox invivible
                        Log.d("case : ", " " + position);
                        favouriteTeam = items[position];
                        house = "purple";
                        break;
                    case 3:
                        Log.d("case : ", " " + position);
                        favouriteTeam = items[position];
                        house = "red";
                        break;
                    case 4:
                        house = "peach";
                        favouriteTeam = items[position];

                        break;
                    case 5:
                        house = "yellow";
                        favouriteTeam = items[position];
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

                FavPlayer.setVisibility(View.VISIBLE);

            }
        });


        SendButtonQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favouriteBatsman = FavPlayer.getText().toString();
                System.out.println("QuestionFragment: " + favouriteBatsman + " " + favouriteTeam);
                if (!favouriteBatsman.equals("") && !favouriteTeam.equals("FAVOURITE TEAM")) {

                    House house1 = new House(mUsername, favouriteBatsman, favouriteTeam, house);
                    House.push().setValue(house1);

                    Intent i = new Intent(getActivity(), LogOverviewActivity.class);
                    Bundle b = new Bundle();
                    b.putStringArray("users", usernameArray);
                    i.putExtra("batch", house);
                    i.putExtra("username", mUsername);
                    i.putExtras(b);
                    getActivity().finish();
                    startActivity(i);
                } else {
                    Toast.makeText(getContext(), "Enter the Required Fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
        House.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("here ->" + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

    }

}
