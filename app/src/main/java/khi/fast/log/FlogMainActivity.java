package khi.fast.log;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Hamza Ahmed on 07-Oct-17.
 */

public class FlogMainActivity extends AppCompatActivity {

    private LinearLayout pickTeam;
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
                Intent i = new Intent(FlogMainActivity.this,PlatinumPlayers.class);
                startActivity(i);
            }
        });

    }
}