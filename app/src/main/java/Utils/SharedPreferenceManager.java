package Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by gilbert-mac on 2017. 10. 1..
 */

public class SharedPreferenceManager {
    private final String PREF_NAME = "com.fourwheels.fourwheels";
    public final static String MY_LOCATION_LONGITUDE = "MY_LOCATION_LONGITUDE";
    public final static String MY_LOCATION_LATITUDE = "MY_LOCATION_LATITUDE";

    public final static String MY_RESERVATION_COUNT = "MY_RESERVATION_COUNT";
    public final static String MY_DO = "MY_DO";
    public final static String MY_GU = "MY_GU";
    public final static String MY_DONG = "MY_DONG";

    public final static String MY_MAPCOUNT = "MY_MAPCOUNT";
    public final static String MY_MAP_INTERVAL = "MY_MAP_INTERVAL";

    public final static String MY_REVIEW_TEXT = "MY_REVIEW_TEXT";

    static Context mContext;

    public SharedPreferenceManager(Context c) {
        mContext = c;
    }

    public void put(String key, String value) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void put(String key, boolean value) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void put(String key, int value) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void put(String key, float value) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public String getValue(String key, String dftValue) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        try {
            return pref.getString(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }
    }

    public int getValue(String key, int dftValue) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        try {
            return pref.getInt(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }
    }

    public boolean getValue(String key, boolean dftValue) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        try {
            return pref.getBoolean(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }
    }

    public float getValue(String key, float dftValue) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        try {
            return pref.getFloat(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }
    }
}
