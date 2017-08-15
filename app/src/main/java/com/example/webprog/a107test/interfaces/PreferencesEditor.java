package com.example.webprog.a107test.interfaces;

/**
 * Manages storing and reading values via {@link android.content.SharedPreferences}
 */

public interface PreferencesEditor {

    void setBooleanValue(final String key, final boolean value);
    boolean getBooleanValue(final String key);
    void setIntValue(final String key, final int value);
    int getIntValue(final String key);
}
