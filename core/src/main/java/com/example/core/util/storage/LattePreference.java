package com.example.core.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.core.app.Latte;


/**
 * Created by 傅令杰 on 2017/4/22
 */

public final class LattePreference {

    /**
     * 提示:
     * Activity.getPreferences(int mode)生成 Activity名.xml 用于Activity内部存储
     * PreferenceManager.getDefaultSharedPreferences(Context)生成 包名_preferences.xml
     * Context.getSharedPreferences(String name,int mode)生成name.xml
     */
    private static final SharedPreferences PREFERENCES =
            PreferenceManager.getDefaultSharedPreferences(Latte.getApplicationContext());
    private static final String APP_PREFERENCES_KEY = "profile";

    private static SharedPreferences getAppPreference() {
        return PREFERENCES;
    }

    public static void setAppProfile(String val) {
        getAppPreference()
                .edit()
                .putString(APP_PREFERENCES_KEY, val)
                .apply();
    }

    public static String getAppProfile() {
        return getAppPreference().getString(APP_PREFERENCES_KEY, null);
    }

    public static JSONObject getAppProfileJson() {
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }

    public static void removeAppProfile() {
        getAppPreference()
                .edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    public static void clearAppPreferences() {
        getAppPreference()
                .edit()
                .clear()
                .apply();
    }

    public static void setAppFlag(String key, boolean flag) {
        getAppPreference()
                .edit()
                .putBoolean(key, flag)
                .apply();
    }

    public static void setUserId(String key,String userId){
        getAppPreference().edit().putString(key,userId).apply();
    }

    public static String  getUserId(String key){

        return getAppPreference().getString(key, "");
    }

    public static void setUserIcon(String key,String url){
        getAppPreference().edit().putString(key,url).apply();
    }

    public static void setUserPsd(String key,String psd){
        getAppPreference().edit().putString(key,psd).apply();
    }

    public static String getUserPsd(String key){
        return getAppPreference().getString(key,"");
    }

    public static String getUserIcon(String key){
        return getAppPreference().getString(key,"");
    }

    /**
     * 用户简介
     */
    public static void setUserSignature(String key,String value){
        getAppPreference().edit().putString(key,value).apply();
    }

    public static String getUserSignature(String key){
        return getAppPreference().getString(key,"");
    }

    public static void setUserType(String key,String value){
        getAppPreference().edit().putString(key,value).apply();
    }

    public static String getUserType(String key){
        return getAppPreference().getString(key,"");
    }


    public static boolean getAppFlag(String key) {
        return getAppPreference()
                .getBoolean(key, false);
    }

    public static void addCustomAppProfile(String key, String val) {
        getAppPreference()
                .edit()
                .putString(key, val)
                .apply();
    }

    public static String getCustomAppProfile(String key) {
        return getAppPreference().getString(key, "");
    }

}
