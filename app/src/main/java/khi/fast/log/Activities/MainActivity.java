package khi.fast.log.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import khi.fast.log.R;
import khi.fast.log.Utils.Constants;

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
        paragraph4.setText(Constants.MAIN_ACTIVITY_PARAGRAPH4);
        getStarted.setText(Constants.MAIN_ACTIVITY_BUTTON_TEXT);
    }
    private void handleClickListener(){
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, QuestionActivity.class);
                startActivity(i);
                finish();

            }
        });
    }
}