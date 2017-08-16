package com.example.webprog.a107test;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.webprog.a107test.interfaces.PreferenceEditorModule;
import com.example.webprog.a107test.interfaces.PreferencesEditor;

/**
 * Created by webprog on 16.08.17.
 */

public class PreferencesEditorModuleImpl implements PreferenceEditorModule {

    @NonNull
    @Override
    public PreferencesEditor getPreferencesEditor(Context context) {
        return new PreferencesEditorImpl(context);
    }
}
