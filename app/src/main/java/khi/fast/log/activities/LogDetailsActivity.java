package khi.fast.log.activities;

/**
 * Created by Hamza Ahmed on 26-Sep-17.
 */


import android.support.v4.app.Fragment;

import khi.fast.log.fragments.LogDetailsFragment;

/**
 * Created by Hamza Ahmed on 19-Jul-17.
 */

public class LogDetailsActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment(){
        return new LogDetailsFragment();
    }
}
