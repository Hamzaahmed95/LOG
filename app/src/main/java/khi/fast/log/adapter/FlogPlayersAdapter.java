package khi.fast.log.adapter;

/**
 * Created by Hamza Ahmed on 05-Oct-17.
 */
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.List;
import khi.fast.log.flog_players.GoalKeepersFragment;
import khi.fast.log.model.FriendlyMessage;
import khi.fast.log.R;

public class FlogPlayersAdapter extends ArrayAdapter<FriendlyMessage> {

    String Name;
    private  TextView messageTextView;
    private  TextView priceTextView;
    private CheckBox checkBox1;
    private FirebaseDatabase mFirebaseDatabase;
    private Dialog dialog;
    private String dbName="";
    private List<FriendlyMessage> topicsList;
    public FlogPlayersAdapter(Context context, int resource, List<FriendlyMessage> objects, String name, String dbName) {
        super(context, resource, objects);
        topicsList=objects;
        Name = name;
        this.dbName = dbName;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_players, parent, false);
        }

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        priceTextView = (TextView) convertView.findViewById(R.id.price);
        checkBox1 = (CheckBox)convertView.findViewById(R.id.checkBox1);
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println(getCount());
                System.out.println(getItem(position).getId());
                final CheckBox checkBox = (CheckBox) view;
                if (checkBox.isChecked())
                {
                    mFirebaseDatabase.getReference().child(dbName).orderByChild("id").equalTo(getItem(position).getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot issue : dataSnapshot.getChildren()) {

                                    issue.getRef().child("check").setValue(true);
                                    checkBox1.setChecked(true);
                                    showDialog(issue.child("text").getValue().toString(),true);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else{
                    mFirebaseDatabase.getReference().child("dbName").orderByChild("id").equalTo(getItem(position).getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {

                                for (DataSnapshot issue : dataSnapshot.getChildren()) {

                                    issue.getRef().child("check").setValue(false);
                                    showDialog(issue.child("text").getValue().toString(),false);
                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });

                }
            }
        });

        FriendlyMessage message = getItem(position);
        if(message.isCheck())
            checkBox1.setChecked(true);
        else
            checkBox1.setChecked(false);
        System.out.println("check or not-> "+position+" "+message.isCheck());
        messageTextView.setVisibility(View.VISIBLE);
        messageTextView.setText(CapsFirst(message.getText()));
        priceTextView.setVisibility(View.VISIBLE);
        priceTextView.setText(String.valueOf(message.getPrice()));
        return convertView;
    }
    String CapsFirst(String str) {
        String[] words = str.split(" ");
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < words.length; i++) {
            ret.append(Character.toUpperCase(words[i].charAt(0)));
            ret.append(words[i].substring(1));
            if(i < words.length - 1) {
                ret.append(' ');
            }
        }
        return ret.toString();
    }
    private void showDialog(String name,Boolean check) {

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.text1);

        Button Close = (Button) dialog.findViewById(R.id.close1);
        TextView t1 =(TextView)dialog.findViewById(R.id.dialogText);
        if(check) {
            t1.setText(name + " is selected as Goal Keeper");
            Close.setText("Select ");
        }
        else {
            Close.setText("Okay");
            t1.setText("Are you sure to remove " + name + "?");
        }

        dialog.setCanceledOnTouchOutside (false);
        Close.setText("Select ");
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                Intent i = new Intent(getContext(), GoalKeepersFragment.class);
                getContext().startActivity(i);
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

}