package com.example.webprog.a107test;

import java.util.HashMap;

/**
 * Created by webprog on 15.08.17.
 */

public class RemoteConfig {

    private static final String notification_search_bar_widget = "active=true, launch_count=2, activate_after_seconds_period=60";

    public static String getNotificationSearchBarWidgetRemoteConfigParams(){
        return notification_search_bar_widget;
    }
}
