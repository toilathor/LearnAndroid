package com.lqt.duynguyenhairsalon.SharedPreferences;

import android.content.Context;

/*
 *
 * Class này quản lí tất cả các Shared Preferences trong Project
 *
 * */
public class DataLocalManager {
    private static final String PREF_FISRT_INSTALL = "PREF_FISRT_INSTALL";
    private static final String PREF_USER_NAME = "PREF_USER_NAME";
    private static final String PREF_IS_LOGGED = "PREF_IS_LOGGED";
    private static final String PREF_IS_ADMIN = "PREF_IS_ADMIN";


    /*
     *Sử dụng Singleton Pattern để tồn tại duy nhất 1 instance
     * sử dụng cơ chế nhiều mày in nhưng chỉ có duy nhất một
     * bộ phận quản lí máy in đó
     * https://viblo.asia/p/hoc-singleton-pattern-trong-5-phut-4P856goOKY3
     *
     * */
    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;


    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }

    //Lazy initialization
    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setFisrtInstall(boolean isFisrt) {
        DataLocalManager.getInstance().mySharedPreferences.putBooleanValue(PREF_FISRT_INSTALL, isFisrt);
    }

    public static boolean getFisrtInstall() {
        return DataLocalManager.getInstance().mySharedPreferences.getBooleanValue(PREF_FISRT_INSTALL);
    }

    public static void setPrefUserName(String userName) {
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PREF_USER_NAME, userName);
    }

    public static String getPrefUserName() {
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(PREF_USER_NAME);
    }

    public static void setPrefIsLogged(boolean isLogged) {
        DataLocalManager.getInstance().mySharedPreferences.putBooleanValue(PREF_IS_LOGGED, isLogged);
    }

    public static boolean getPrefIsLogged() {
        return DataLocalManager.getInstance().mySharedPreferences.getBooleanValue(PREF_IS_LOGGED);
    }

    public static void setPrefIsAdmin(boolean isLAdmin) {
        DataLocalManager.getInstance().mySharedPreferences.putBooleanValue(PREF_IS_ADMIN, isLAdmin);
    }

    public static boolean getPrefIsAdmin() {
        return DataLocalManager.getInstance().mySharedPreferences.getBooleanValue(PREF_IS_ADMIN);
    }
}
