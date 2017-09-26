package khi.fast.log;

/**
 * Created by Hamza Ahmed on 26-Sep-17.
 */


import android.support.v4.app.Fragment;

/**
 * Created by Hamza Ahmed on 19-Jul-17.
 */

public class BasketballActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment(){
        return new BasketballFragment();
    }
}