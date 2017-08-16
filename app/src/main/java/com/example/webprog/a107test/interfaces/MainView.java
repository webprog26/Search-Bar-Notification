package com.example.webprog.a107test.interfaces;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;

/**
 * Manages MainView implementation behaviour
 */

public interface MainView {

    @NonNull
    Handler getHandler();
    @NonNull
    Context getContext();
    @NonNull
    AppMenuDelegate getAppMenuDelegate();
    boolean isSearchInputFieldInFocus();
    void hideKeyboard();
}
