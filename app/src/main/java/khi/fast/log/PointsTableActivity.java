package khi.fast.log;

/**
 * Created by Hamza Ahmed on 27-Sep-17.
 */

import android.support.v4.app.Fragment;

/**
 * Created by Hamza Ahmed on 18-Jul-17.
 */

public class PointsTableActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new PointsTableFragment();
    }

}
