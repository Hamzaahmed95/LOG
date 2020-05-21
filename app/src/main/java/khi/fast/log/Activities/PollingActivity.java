package khi.fast.log.Activities;

/**
 * Created by Hamza Ahmed on 26-Sep-17.
 */


import android.support.v4.app.Fragment;

import khi.fast.log.Fragments.PollingFragment;

/**
 * Created by Hamza Ahmed on 19-Jul-17.
 */

public class PollingActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment(){
        return new PollingFragment();
    }
}