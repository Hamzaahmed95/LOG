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
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.List;
import khi.fast.log.flog_players.GoalKeepersFragment;
import khi.fast.log.model.FriendlyMessage;
import khi.fast.log.R;

public class FlogPlayersListAdapter extends ArrayAdapter<FriendlyMessage> {

    String Name;
    private TextView messageTextView;
    private ImageView TeamLogo;
    private FirebaseDatabase mFirebaseDatabase;
    private Dialog dialog;
    private String dbName="";

    public FlogPlayersListAdapter(Context context, int resource, List<FriendlyMessage> objects, String name, String dbName) {
        super(context, resource, objects);
        Name = name;
        this.dbName = dbName;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.flog_players_list, parent, false);
        }

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        messageTextView = (TextView) convertView.findViewById(R.id.player_name);
        TeamLogo = (ImageView) convertView.findViewById(R.id.team_logo);



        FriendlyMessage message = getItem(position);
        if(message.getPrice().equals("Stags")){
            TeamLogo.setImageResource(R.drawable.stags);
        }
        else if(message.getPrice().equals("Falcons")){
            TeamLogo.setImageResource(R.drawable.falcons);
        }
        else if (message.getPrice().equals("Dragons")){
            TeamLogo.setImageResource(R.drawable.dragons);
        }
        else if (message.getPrice().equals("Hunters")){
            TeamLogo.setImageResource(R.drawable.hunters);
        }
        else {
            TeamLogo.setImageResource(R.drawable.dires);
        }

        messageTextView.setVisibility(View.VISIBLE);
        messageTextView.setText(CapsFirst(message.getText()));
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