package khi.fast.log.FlogPlayers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import khi.fast.log.Activities.PlatinumPlayers;
import khi.fast.log.Adapter.PlayerListAdapter;
import khi.fast.log.POJO.FriendlyMessage;
import khi.fast.log.R;

public class OneFragment extends Fragment {

    public OneFragment() {
        // Required empty public constructor
    }
    private PlayerListAdapter mPlayerListAdapter;
    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mMessageDatabaseReference;
    private ListView mMessageListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("platinumPlayers");
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        mMessageListView = (ListView) view.findViewById(R.id.messageListView);
        final List<FriendlyMessage> friendlyMessages = new ArrayList<>();
        mPlayerListAdapter = new PlayerListAdapter(getActivity(), R.layout.item_players, friendlyMessages, "hamza");

        if (mMessageListView != null)
            mMessageListView.setAdapter(mPlayerListAdapter);
        return view;
    }

}
