package com.example.webprog.a107test.interfaces;

import android.support.annotation.NonNull;

/**
 * Manages SearchBarFeature behaviour
 */

public interface Presenter {

    String TIMES_APP_WAS_LAUNCHED_TAG = "times_app_was_launched_tag";
    String SHARED_PREFERENCES_INITIALIZING_ERROR_MESSAGE = "Shared preferences were not initialized";

    void setView(MainView mainView);
    @NonNull
    MainView getMainView();
    void sendNotification();
    void askUserToActivateSearchBar();
    void cancelNotification();
    void incrementTimesAppWasLaunchedCount() throws Exception;
    void resetTimesAppWasLaunchedCount();

}
