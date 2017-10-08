package khi.fast.log;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Hamza Ahmed on 07-Oct-17.
 */

public class FlogMainActivity extends AppCompatActivity {

    private LinearLayout pickTeam;
    private Dialog dialog;
    private ImageView backbutton5;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flog_main_activity);
        pickTeam=(LinearLayout)findViewById(R.id.pickTeam);
        backbutton5=(ImageView)findViewById(R.id.backButton);
        backbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FlogMainActivity.this,Check123.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(i);
            }
        });
        pickTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }
    private void showDialog() {
        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.text1);

        // set the custom dialog components - text, image and button

        // Close Button

        // Buy Button

        TextView t1 =(TextView)dialog.findViewById(R.id.dialogText);
        t1.setText("You have 100 coins to buy 8 players \n 1 Goal Keeper \n 4 Defenders \n 3 Strikers");

        dialog.setCanceledOnTouchOutside (false);
        Button Close = (Button) dialog.findViewById(R.id.close1);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                Intent i = new Intent(FlogMainActivity.this,PlatinumPlayers.class);
                startActivity(i);


            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
