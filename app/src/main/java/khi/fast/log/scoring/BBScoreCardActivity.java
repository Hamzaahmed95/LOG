package khi.fast.log.scoring;

/**
 * Created by Hamza Ahmed on 01-Oct-17.
 */

import android.support.v4.app.Fragment;

import khi.fast.log.activities.SingleFragmentActivity;

/**
 * Created by Hamza Ahmed on 19-Jul-17.
 */

public class BBScoreCardActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment(){
        return new BBScoreCardFragment();
    }
}
