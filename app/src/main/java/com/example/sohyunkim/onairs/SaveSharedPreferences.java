package com.example.sohyunkim.onairs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreferences {
    static final String userID = "userID";
    static final String name = "name";

    static SharedPreferences getSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    //save user name state
    public static void saveNameState(Context context, String name){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString("name", name);
        editor.commit();
    }
    //get user name state
    public static String getNameState(Context context){
        return getSharedPreferences(context).getString(name,"");
    }
    //save userID state
    public static void saveUserIDState(Context context, String userID){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString("userID", userID);
        editor.commit();
    }
    //get userID state
    public static String getuserIDState(Context context){
        return getSharedPreferences(context).getString(userID,"");
    }
    //remove state
    public static void removeState(Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.commit();
    }
}
