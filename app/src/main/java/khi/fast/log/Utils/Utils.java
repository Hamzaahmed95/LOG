package khi.fast.log.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import khi.fast.log.Activities.SplashScreen;

import static android.content.Context.MODE_PRIVATE;

public class Utils {


    Context context;

    public Utils(Context context){
        this.context = context;

    }

    public boolean isFirstTime()
    {
        SharedPreferences preferences = context.getSharedPreferences("CheckingFirstTime",MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }
}
