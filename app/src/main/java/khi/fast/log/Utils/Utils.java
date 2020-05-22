package khi.fast.log.Utils;

import android.content.Context;
import android.content.SharedPreferences;


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
}
