package khi.fast.log.pre_login;

/**
 * Created by Hamza Ahmed on 25-Sep-17.
 */

import android.support.v4.app.Fragment;

import khi.fast.log.activities.SingleFragmentActivity;
import khi.fast.log.fragments.QuestionFragment;

/**
 * Created by Hamza Ahmed on 19-Jul-17.
 */

public class QuestionActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment(){
        return new QuestionFragment();
    }
}