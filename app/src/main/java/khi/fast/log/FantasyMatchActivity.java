package khi.fast.log;

import android.support.v4.app.Fragment;

/**
 * Created by Hamza Ahmed on 09-Oct-17.
 */

public class FantasyMatchActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new FantasyMatchScore();
    }
}
