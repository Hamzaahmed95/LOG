package khi.fast.log.Scoring;

/**
 * Created by Hamza Ahmed on 26-Sep-17.
 */


import android.support.v4.app.Fragment;

import khi.fast.log.Activities.SingleFragmentActivity;

/**
 * Created by Hamza Ahmed on 19-Jul-17.
 */

public class CricketScoreCardActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment(){
        return new CricketScoreCardFragment();
    }
}