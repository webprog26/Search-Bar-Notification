package com.example.webprog.a107test.interfaces;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;

/**
 * Manages MainView implementation behaviour
 */

public interface MainView {

    boolean isSearchBarUserActionIntent(final Intent searchBarUserActionIntent);
    @NonNull
    Handler getHandler();
    void incrementTimesAppWasLaunchedCount();
    @NonNull
    Context getContext();
}
