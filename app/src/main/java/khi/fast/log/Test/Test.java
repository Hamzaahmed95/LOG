package khi.fast.log.Test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import khi.fast.log.R;
import khi.fast.log.adapter.TestAdapter;
import khi.fast.log.model.Player;

public class Test extends Fragment {

    RecyclerView.LayoutManager layoutManager;
    private TestAdapter mAdapter;
    Map<String, String> Players;
    List<Player> players=new ArrayList<>();
    LinearLayout LinearLayout;
    RecyclerView [] myRecyclerView;
    TextView[] myTextViews;

    private FirebaseDatabase mFirebaseDatabase;
    final int N = 5;
    int i=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test, container, false);
        LinearLayout = (LinearLayout) view.findViewById(R.id.main_layout);
        myTextViews = new TextView[N];
        myRecyclerView = new RecyclerView[N];

        for (int i = 0; i < N; i++) {
            final RecyclerView rowRecyclerView = new RecyclerView(getContext());
            final TextView rowTextView = new TextView(getContext());
            rowTextView.setTextSize(25);
            rowTextView.setPadding(35,35,15,15);
            rowTextView.setTextColor(getResources().getColor(R.color.common_text_color));

            LinearLayout.addView(rowTextView);
            LinearLayout.addView(rowRecyclerView);
            rowRecyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
            rowRecyclerView.setLayoutManager(layoutManager);

            myRecyclerView[i] = rowRecyclerView;
            myTextViews[i]=rowTextView;
        }
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        Players = new HashMap<String, String>();

        QueryingData();
        return view;
    }

    public void QueryingData() {
        Query mHouseDatabaseReference2 = mFirebaseDatabase.getReference().child("Teams").orderByChild("players");


        mHouseDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    i=0;

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        players=new ArrayList<>();
                        System.out.println("");
                        String logo = issue.child("image").getValue().toString();
                        String team_name = issue.child("team_name").getValue().toString();
                        for (DataSnapshot issue2 : issue.child("players").getChildren()) {
                            Player player = new Player(issue2.child("name").getValue().toString(), issue2.child("position").getValue().toString());
                            players.add(player);
                        }

                        for(int j =0;j<11;j++){
                            System.out.println("Players: => "+players.get(j).getName());
                        }
                        System.out.println("Players: ---------------------------------------------");


                        mAdapter = new TestAdapter(players);
                        myTextViews[i].setText(team_name);
                        myRecyclerView[i].setAdapter(mAdapter);
                        i++;

                    }


                }


               // recyclerView.setAdapter(mAdapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
    }
}
