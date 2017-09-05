package com.jjkopen.demo.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jjkopen.demo.base.MyApp;

public class PreferenceUtils {

    public static int getInt(String Key) {
        return PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance()).getInt(Key, 0);
    }

    public static void setInt(String Key, int value) {
        SharedPreferences.Editor Editor = PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance()).edit();
        Editor.putInt(Key, value);
        Editor.apply();
    }

    public static Long getLong(String Key) {
        return PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance()).getLong(Key, 0);
    }

    public static void setLong(String Key, Long value) {
        SharedPreferences.Editor Editor = PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance()).edit();
        Editor.putLong(Key, value);
        Editor.apply();
    }

    public static String getString(String Key) {
        return PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance()).getString(Key, "");
    }

    public static void setString(String Key, String Values) {
        SharedPreferences.Editor Editor = PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance()).edit();
        Editor.putString(Key, Values);
        Editor.apply();
    }

    public static boolean getBoolean(String key) {
        return PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance()).getBoolean(key, false);
    }

    public static void setBoolean(String Key, Boolean value) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance()).edit();
        edit.putBoolean(Key, value);
        edit.apply();
    }

    public static void remove(String... keys) {
        if (keys != null) {
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance()).edit();
            for (String key : keys) {
                editor.remove(key);
            }
            editor.apply();
        }
    }

    public static void clear() {
        SharedPreferences.Editor Editor = PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance()).edit();
        Editor.clear();
        Editor.apply();
    }

}
