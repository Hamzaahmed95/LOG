package khi.fast.log;

/**
 * Created by Hamza Ahmed on 03-Oct-17.
 */

import android.support.v4.app.Fragment;

/**
 * Created by Hamza Ahmed on 18-Jul-17.
 */

public class BadmintonPTActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new BadmintonPTFragment();
    }

}