package com.playdate.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {

    private static final String PREF_NAME = "VLOGHIT";

    private static PreferencesManager sInstance;
    private final SharedPreferences mPref;

    private PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
    }

    public static synchronized PreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(PreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void setStringValue(String KEY_VALUE, String value) {
        mPref.edit()
                .putString(KEY_VALUE, value)
                .commit();
    }

    public String getStringValue(String KEY_VALUE) {
        return mPref.getString(KEY_VALUE, "");
    }


    public void setBooleanValue(String KEY_VALUE, boolean value) {
        mPref.edit()
                .putBoolean(KEY_VALUE, value)
                .commit();
    }

    public boolean getBooleanValue(String KEY_VALUE) {
        return mPref.getBoolean(KEY_VALUE, false);
    }


    public void remove(String key) {
        mPref.edit()
                .remove(key)
                .commit();
    }

    public boolean clear() {
        return mPref.edit()
                .clear()
                .commit();
    }
}