package com.example.webprog.a107test;

import android.app.Application;

import com.example.webprog.a107test.interfaces.PreferencesEditor;
import com.example.webprog.a107test.interfaces.SearchNotification;

/**
 * Created by webprog on 09.08.17.
 */

public class App extends Application {

    private static PreferencesEditor mPreferencesEditor;
    private static SearchNotification mSearchNotification;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mPreferencesEditor = new PreferencesEditorImpl(this);
        this.mSearchNotification = new SearchNotificationImpl(this);
    }

    public static PreferencesEditor getPreferencesEditor(){
        return mPreferencesEditor;
    }

    public static SearchNotification getSearchNotification() {
        return mSearchNotification;
    }
}
