package com.example.webprog.a107test.interfaces;

import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Manages SearchBarFeature behaviour
 */

public interface Presenter {

    String TIMES_APP_WAS_LAUNCHED_TAG = "times_app_was_launched_tag";
    String SHARED_PREFERENCES_INITIALIZING_ERROR_MESSAGE = "Shared preferences were not initialized";
    int ILLEGAL_LAUNCHES_COUNT = -1;
    int ZERO_LAUNCHES_COUNT = 0;

    void setView(MainView mainView);
    @NonNull
    MainView getMainView();
    void sendNotification();
    void askUserToActivateSearchBar();
    void cancelNotification();
    void incrementTimesAppWasLaunchedCount() throws Exception;
    void resetTimesAppWasLaunchedCount();
    boolean onPrepareAppOptionsMenu(Menu menu);
    boolean onCreateAppOptionsMenu(Menu menu, MenuInflater menuInflater);
    boolean onAppOptionsItemSelected(MenuItem item, boolean defValue);

}
