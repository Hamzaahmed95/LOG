package khi.fast.log;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Hamza Ahmed on 28-Sep-17.
 */

public class umar2 extends AppCompatActivity {

    TextView t1;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.umar2);
        t1 = (TextView)findViewById(R.id.nameOutput);
        t1.setText("Umar");


    }
}
