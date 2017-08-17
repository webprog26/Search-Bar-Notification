package com.example.webprog.a107test;

/**
 * Created by webprog on 15.08.17.
 */

public class RemoteConfig {

    private static final String notification_search_bar_widget_params = "active=true, launch_count=2, offering_dialog_show_after_seconds_period=5, activated_by_default=false";

    public static String getNotificationSearchBarWidgetRemoteConfigParams(){
        return notification_search_bar_widget_params;
    }
}
