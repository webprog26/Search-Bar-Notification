package com.example.webprog.a107test;

/**
 * Created by webprog on 15.08.17.
 */

public class RemoteConfig {

    private static final boolean IS_SEARCH_BAR_ACTIVE = true;
    private static final long SEARCH_BAR_WIDGET_USER_DIALOG_SHOWING_TIME_PERIOD = 5000;
    private static final int TIMES_TO_SHOW_ACTIVATE_SEARCH_BAR_DIALOG = 2;

    public static boolean getIsSearchBarActive(){
        return IS_SEARCH_BAR_ACTIVE;
    }

    public static long getSearchBarWidgetUserDialogShowingTimePeriod(){
        return SEARCH_BAR_WIDGET_USER_DIALOG_SHOWING_TIME_PERIOD;
    }

    public static int getTimesToShowActivateSearchBarDialog() {
        return TIMES_TO_SHOW_ACTIVATE_SEARCH_BAR_DIALOG;
    }
}
