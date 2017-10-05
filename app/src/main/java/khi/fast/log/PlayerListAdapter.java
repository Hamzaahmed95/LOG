package khi.fast.log;

/**
 * Created by Hamza Ahmed on 05-Oct-17.
 */


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class PlayerListAdapter extends ArrayAdapter<FriendlyMessage> {

    String Name;
    FirebaseUser user;
    private  TextView messageTextView;
    private CheckBox checkBox1;

    private int count=0;
    public PlayerListAdapter(Context context, int resource, List<FriendlyMessage> objects, String name) {
        super(context, resource, objects);
        Name = name;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_players, parent, false);
        }

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        checkBox1 = (CheckBox)convertView.findViewById(R.id.checkBox1);
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // System.out.println(mMessageDatabaseReference.child(getItem(position).getId()).getRoot().removeValue());
                //  mMessageDatabaseReference.getRef().setValue(null);
                //    System.out.println(mHouseDatabaseReference.child(("id").getV));
                if(count<1)
                count++;
                else{
                    checkBox1.setEnabled(false);
                    Toast.makeText(getContext(),"You are only allowed 1 ",Toast.LENGTH_SHORT).show();
                }
                System.out.println(getItem(position).getText());

            }
        });


        FriendlyMessage message = getItem(position);
        boolean isPhoto = message.getPhotoUrl()!= null;

        if (isPhoto) {
            messageTextView.setVisibility(View.GONE);
            photoImageView.setVisibility(View.VISIBLE);
            Glide.with(photoImageView.getContext())
                    .load(message.getPhotoUrl())

                    .into(photoImageView);
        } else {
            messageTextView.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.GONE);
            messageTextView.setText(CapsFirst(message.getText()));
        }


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

}