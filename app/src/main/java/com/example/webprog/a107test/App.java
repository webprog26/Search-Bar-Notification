package com.example.webprog.a107test;

import android.app.Application;

import com.example.webprog.a107test.interfaces.PreferenceEditorModule;
import com.example.webprog.a107test.interfaces.SearchNotificationModule;

/**
 * Created by webprog on 09.08.17.
 */

public class App extends Application {

    private static PreferenceEditorModule mPreferenceEditorModule;
    private static SearchNotificationModule mSearchNotificationModule;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mPreferenceEditorModule = new PreferencesEditorModuleImpl();
        this.mSearchNotificationModule = new SearchNotificationModuleImpl();
    }

    public static PreferenceEditorModule getPreferenceEditorModule(){
        return mPreferenceEditorModule;
    }

    public static SearchNotificationModule getSearchNotificationModule(){
        return mSearchNotificationModule;
    }
}
