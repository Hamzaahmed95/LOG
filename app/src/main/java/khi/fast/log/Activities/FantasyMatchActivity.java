package khi.fast.log.Activities;

import android.support.v4.app.Fragment;

import khi.fast.log.Fragments.FantasyMatchScore;

/**
 * Created by Hamza Ahmed on 09-Oct-17.
 */

public class FantasyMatchActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new FantasyMatchScore();
    }
}
