package com.lqt.duynguyenhairsalon.SharedPreferences;

import android.content.Context;
/*
*
* Class này quản lí tất cả các Shared Preferences trong Project
*
* */
public class DataLocalManager {
    private static final String PREF_FISRT_INSTALL = "PREF_FISRT_INSTALL";
    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;

    public static void init(Context context){
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }

    public static DataLocalManager getInstance(){
        if (instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setFisrtInstall(boolean isFisrt){
        DataLocalManager.getInstance().mySharedPreferences.putBooleanValue(PREF_FISRT_INSTALL,isFisrt);
    }

    public static boolean getFisrtInstall(){
        return DataLocalManager.getInstance().mySharedPreferences.getBoleanValue(PREF_FISRT_INSTALL);
    }
}
