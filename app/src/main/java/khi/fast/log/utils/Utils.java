package khi.fast.log.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

    public static final void startingActivity(Context context, final Class<? extends Activity> ActivityToOpen,boolean isClearTop){

        if(isClearTop){
            Intent i = new Intent(context, ActivityToOpen);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(i);
        }
        else{
            Intent i = new Intent(context, ActivityToOpen);
            context.startActivity(i);
        }
    }
   public static final String CapsFirst(String str) {
        String[] words = str.split(" ");
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < words.length; i++) {
            ret.append(Character.toUpperCase(words[i].charAt(0)));
            ret.append(words[i].substring(1));
            if(i < words.length - 1) {
                ret.append(' ');
            }
        }
        return ret.toString();
    }
}
