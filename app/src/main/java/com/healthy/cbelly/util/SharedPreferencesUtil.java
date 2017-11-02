package com.healthy.cbelly.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.healthy.cbelly.account.JoinInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chan1park on 2017. 4. 28..
 *
 * SharedPreferencesUtil
 */

public class SharedPreferencesUtil {

    private static final String TAG = SharedPreferencesUtil.class.getName();

    private static final String SET_MAC_ADDRESS_PREF = "SET_MAC_ADDRESS_PREF";

    /**
     * SharedPreferences 에 String 저장
     * @param key
     * @param value
     */
    public static synchronized void putString(Context context, String key, String value) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * SharedPreferences 에서 String 추출
     * @param context
     * @param key
     * @return
     */
    public static synchronized String getString(Context context, String key, String def) {
        String value = PreferenceManager.getDefaultSharedPreferences(context).getString(key, def);
        return value;
    }

    /**
     * SharedPreferences 에 StringSet 저장
     * @param key
     * @param value
     */
    public static synchronized void putStringSet(Context context, String key, Set<String> value) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet(key, value);
        editor.commit();
    }

    /**
     * SharedPreferences 에서 StringSet 추출
     * @param context
     * @param key
     * @return
     */
    public static synchronized Set<String> getStringSet(Context context, String key, Set<String> def) {
        Set<String> value = PreferenceManager.getDefaultSharedPreferences(context).getStringSet(key, def);
        return value;
    }


    /**
     * SharedPreferences 에 boolean 저장
     * @param context
     * @param key
     * @param value
     */
    public static synchronized void putBoolean(Context context, String key, boolean value) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * ShredPrefereces 에서 boolean 추출
     * @param context
     * @param key
     * @return
     */
    public static synchronized boolean getBoolean(Context context, String key) {
        boolean value = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, false);
        return value;
    }


    /**
     * SharedPreferences 에서 해당 키의 값을 삭제
     * @param context
     * @param key
     */
    public static synchronized void removePref(Context context, String key) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.commit();
    }

}
