package khi.fast.log.log_details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import khi.fast.log.R;

public class ManOfTheMatchFragment extends Fragment {



    private TextView text;
    public ManOfTheMatchFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.text, container, false);
        text =(TextView) view.findViewById(R.id.text);
        text.setText("ManOfTheMatchFragment");

        return view;
    }
}

