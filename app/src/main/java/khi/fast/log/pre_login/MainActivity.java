package khi.fast.log.pre_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import khi.fast.log.R;
import khi.fast.log.utils.Constants;
import khi.fast.log.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private Button getStarted;
    private TextView heading;
    private TextView sub_heading0;
    private TextView sub_heading1;
    private TextView sub_heading2;
    private TextView paragraph0;
    private TextView paragraph1;
    private TextView paragraph2;
    private TextView paragraph3;
    private TextView paragraph4;
    private TextView paragraph5;
    private TextView paragraph6;
    private TextView paragraph7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializaiton();
        settingValue();
        handleClickListener();
    }
    private void initializaiton(){
        getStarted = (Button)findViewById(R.id.getStarted);
        heading = (TextView)findViewById(R.id.heading);
        sub_heading0 = (TextView)findViewById(R.id.sub_heading0);
        sub_heading1 = (TextView)findViewById(R.id.sub_heading1);
        sub_heading2 = (TextView)findViewById(R.id.sub_heading2);
        paragraph0 = (TextView)findViewById(R.id.paragraph0);
        paragraph1 = (TextView)findViewById(R.id.paragraph1);
        paragraph2 = (TextView)findViewById(R.id.paragraph2);
        paragraph3 = (TextView)findViewById(R.id.paragraph3);
        paragraph4 = (TextView)findViewById(R.id.paragraph4);
        paragraph5 = (TextView)findViewById(R.id.paragraph5);
        paragraph6 = (TextView)findViewById(R.id.paragraph6);
        paragraph7 = (TextView)findViewById(R.id.paragraph7);
    }
    private void settingValue(){
        heading.setText(Constants.MAIN_ACTIVITY_HEADING);
        sub_heading0.setText(Constants.MAIN_ACTIVITY_SUB_HEADING0);
        sub_heading1.setText(Constants.MAIN_ACTIVITY_SUB_HEADING1);
        sub_heading2.setText(Constants.MAIN_ACTIVITY_SUB_HEADING2);
        paragraph0.setText(Constants.MAIN_ACTIVITY_PARAGRAPH0);
        paragraph1.setText(Constants.MAIN_ACTIVITY_PARAGRAPH1);
        paragraph2.setText(Constants.MAIN_ACTIVITY_PARAGRAPH2);
        paragraph3.setText(Constants.MAIN_ACTIVITY_PARAGRAPH3);
        paragraph4.setText(Constants.MAIN_ACTIVITY_PARAGRAPH0);
        paragraph5.setText(Constants.MAIN_ACTIVITY_PARAGRAPH1);
        paragraph6.setText(Constants.MAIN_ACTIVITY_PARAGRAPH4);
        paragraph7.setText(Constants.MAIN_ACTIVITY_PARAGRAPH5);
        getStarted.setText(Constants.MAIN_ACTIVITY_BUTTON_TEXT);
    }
    private void handleClickListener(){
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startingActivity(MainActivity.this, LoginActivity.class,"","",true);
            }
        });
    }
}