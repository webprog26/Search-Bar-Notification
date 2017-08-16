package com.example.webprog.a107test;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.webprog.a107test.interfaces.PreferencesEditor;
import com.example.webprog.a107test.interfaces.Presenter;

/**
 * Created by webprog on 09.08.17.
 */

public class PreferencesEditorImpl implements PreferencesEditor {

    private final SharedPreferences mSearchBarSharedPreferences;

    public PreferencesEditorImpl(Context context) {
        this.mSearchBarSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void setBooleanValue(String key, boolean value) {
        SharedPreferences sharedPreferences = getSearchBarSharedPreferences();

        if(sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(key, value).apply();
        }
    }

    @Override
    public boolean getBooleanValue(String key) {
        SharedPreferences sharedPreferences = getSearchBarSharedPreferences();
        return (sharedPreferences != null && sharedPreferences.getBoolean(key, false));
    }

    @Override
    public void setIntValue(final String key, int value) {
        SharedPreferences sharedPreferences = getSearchBarSharedPreferences();

        if(sharedPreferences != null) {
            sharedPreferences.edit().putInt(key, value).apply();
        }
    }

    @Override
    public int getIntValue(String key) {
        SharedPreferences sharedPreferences = getSearchBarSharedPreferences();

        if(sharedPreferences != null) {
            return sharedPreferences.getInt(key, Presenter.ZERO_LAUNCHES_COUNT);
        }
        return Presenter.ILLEGAL_LAUNCHES_COUNT;
    }

    private SharedPreferences getSearchBarSharedPreferences() {
        return mSearchBarSharedPreferences;
    }
}
