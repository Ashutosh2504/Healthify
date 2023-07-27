package com.example.healthifyapp.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    final static String FileName="Shared_Prefrences";

    final static String FileName1="logid";
    final static String FileName2="Subscription";

    public static String readSharedSetting(Context ctx, String settingName, String defualtValue) {
        SharedPreferences sharedPref =ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        return sharedPref.getString(settingName,defualtValue);
    }
    public static int readSharedSetting(Context ctx, String settingName, int defualtValue) {
        SharedPreferences sharedPref =ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        return sharedPref.getInt(settingName,defualtValue);
    }
    public static void saveSharedSetting(Context ctx, String settingName, String settingValue) {
        SharedPreferences sharedPref =ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putString(settingName,settingValue);
        editor.apply();
    }
    public static void saveSharedSetting(Context ctx, String settingName, float settingValue) {
        SharedPreferences sharedPref =ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putFloat(settingName,settingValue);
        editor.apply();
    }
    public static void saveSharedSetting(Context ctx, String settingName, int settingValue) {
        SharedPreferences sharedPref =ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putInt(settingName,settingValue);
        editor.apply();
    }


}
