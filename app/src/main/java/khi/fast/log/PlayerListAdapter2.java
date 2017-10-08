package khi.fast.log;

/**
 * Created by Hamza Ahmed on 05-Oct-17.
 */


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static com.facebook.GraphRequest.TAG;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class PlayerListAdapter2 extends ArrayAdapter<FriendlyMessage> {

    String Name;
    FirebaseUser user;
    private  TextView messageTextView;
    private  TextView priceTextView;
    private CheckBox checkBox1;
    private int pos;
    private FirebaseDatabase mFirebaseDatabase;
    private Dialog dialog;
    private DatabaseReference mMessageDatabaseReference;
    private int count=0;
    private List<FriendlyMessage> topicsList;
    public PlayerListAdapter2(Context context, int resource, List<FriendlyMessage> objects, String name) {
        super(context, resource, objects);
        topicsList=objects;
        Name = name;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_players, parent, false);
        }

        //  ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        priceTextView = (TextView) convertView.findViewById(R.id.price);
        checkBox1 = (CheckBox)convertView.findViewById(R.id.checkBox1);





        mMessageDatabaseReference =mFirebaseDatabase.getReference().child("silverPlayers");
        //    System.out.println("called"+count+" position "+getItemId(position));

        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println(getCount());
                System.out.println(getItem(position).getId());
                final CheckBox checkBox = (CheckBox) view;
                // System.out.println("checkbox check"+);


                if (checkBox.isChecked())
                {
                    mFirebaseDatabase.getReference().child("silverPlayers").orderByChild("id").equalTo(getItem(position).getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // dataSnapshot is the "issue" node with all children with id 0
                                for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                    // do something with the individual "issues"

                                    issue.getRef().child("check").setValue(true);
                                    checkBox1.setChecked(true);
                                    showDialog(issue.child("text").getValue().toString());
                                    //i++;

                                }

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else{
                    mFirebaseDatabase.getReference().child("silverPlayers").orderByChild("id").equalTo(getItem(position).getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // dataSnapshot is the "issue" node with all children with id 0
                                for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                    // do something with the individual "issues"


                                    issue.getRef().child("check").setValue(false);

                                    //i++;

                                }

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

                // System.out.println(mMessageDatabaseReference.child(getItem(position).getId()).getRoot().removeValue());
                //  mMessageDatabaseReference.getRef().setValue(null);
                //    System.out.println(mHouseDatabaseReference.child(("id").getV));
               /* if(count<1)
                count++;
                else{
                    checkBox1.setEnabled(false);
                    Toast.makeText(getContext(),"You are only allowed 1 ",Toast.LENGTH_SHORT).show();
                }*/
           /*     for(int i=0;i<getCount();i++){
                    System.out.println();
                }
                System.out.println("position= "+position);
                CheckBox checkBox = (CheckBox)view;
                if(count>0){
                    checkBox1.setChecked(false);
                    checkBox.setChecked(false);

                }


                if(checkBox.isChecked()){
                    FriendlyMessage friendlyMessage1 = getItem(position);
                    friendlyMessage1.setCheck(true);
                    System.out.println(getItem(position).getText());
                    System.out.println(getItem(position).getPrice());
                    System.out.println(getItem(position).getName());
                    System.out.println("count = " +count);
                    pos=position;
                    System.out.print("-> "+pos);
                    count++;
                }
                else{
                    System.out.print("pos= "+pos+" postion= "+position);
                    if(position==pos){
                        count--;
                    }
                    else{
                        Toast.makeText(getContext(),"Only 1 Goal keeper is allowed!",Toast.LENGTH_SHORT).show();
                    }
                }

*/
            }
        });

     /*   mMessageDatabaseReference.orderByChild("check").equalTo("false").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot fruitSnapshot: dataSnapshot.getChildren()) {

                    System.out.println(fruitSnapshot.child("check").getValue());
                    //fruitSnapshot.getRef().child("colour").setValue("Red");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

        */
        FriendlyMessage message = getItem(position);
        if(message.isCheck())
            checkBox1.setChecked(true);
        else
            checkBox1.setChecked(false);
        System.out.println("check or not-> "+position+" "+message.isCheck());
        messageTextView.setVisibility(View.VISIBLE);
        messageTextView.setText(message.getText());
        priceTextView.setVisibility(View.VISIBLE);
        priceTextView.setText(String.valueOf(message.getPrice()));
        //if(message.isCheck())
        //checkBox1.setChecked(!checkBox1.isChecked());
        // ;



        return convertView;
    }
    /*String CapsFirst(String str) {
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
    }*/
    private void showDialog(String name) {
        // custom dialog
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.text1);

        // set the custom dialog components - text, image and button

        // Close Button

        // Buy Button

        TextView t1 =(TextView)dialog.findViewById(R.id.dialogText);
        t1.setText(name+" is your goal keeper");

        dialog.setCanceledOnTouchOutside (false);
        Button Close = (Button) dialog.findViewById(R.id.close1);
        Close.setText("Select ");
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                Intent i = new Intent(getContext(),SilverPlayers.class);
                getContext().startActivity(i);


            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

}