package khi.fast.log.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;


import static android.content.Context.MODE_PRIVATE;

public class Utils {

    public static final boolean isFirstTime(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences("CheckingFirstTime",MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }

    public static final void startingActivity(Context context, final Class<? extends Activity> ActivityToOpen, String extraKey,String extraMsg,boolean isFinish){

        if(extraKey==""){
            Intent i = new Intent(context, ActivityToOpen);
            context.startActivity(i);
        }
        else{
            Intent i = new Intent(context, ActivityToOpen);
            i.putExtra(extraKey,extraMsg);
            context.startActivity(i);
        }
        if(isFinish){
            ((Activity)context).finish();
        }
    }
}
