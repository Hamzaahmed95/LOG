package khi.fast.log.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import khi.fast.log.pre_login.SplashScreen;

import static android.content.Context.MODE_PRIVATE;

public class Utils {

    private static final String PREFS_NAME = "log_my_preferences";

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
    private static SharedPreferences getPreference() {
        return SplashScreen.APPLICATION_CONTEXT.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }

    public static void saveBoolean(String key, boolean val) {
        SharedPreferences prefs = getPreference();
        SharedPreferences.Editor e = prefs.edit();
        try {
            e.putBoolean(new String(key.getBytes("UTF-8"), Charset.defaultCharset()), val).apply();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
    }
    public static boolean getBooleanPref(String key) {
        return getPreference().getBoolean(key, false);
    }

}
