package com.example.webprog.a107test.interfaces;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Manages three-dots-menu states and actions
 */

public interface AppMenuDelegate {

    String IS_SEARCH_BAR_ACTIVE_TAG = "is_search_bar_active_tag";

    boolean onPrepareAppOptionsMenu(Menu menu);
    boolean onCreateAppOptionsMenu(Menu menu, MenuInflater menuInflater);
    boolean onAppOptionsItemSelected(MenuItem item, boolean defValue);
}