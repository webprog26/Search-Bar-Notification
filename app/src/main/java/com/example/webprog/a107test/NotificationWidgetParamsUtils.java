package com.example.webprog.a107test;

import java.util.HashMap;

/**
 * Created by webprog on 17.08.17.
 */

public class NotificationWidgetParamsUtils {

    private static final String NOTIFICATION_SEARCH_BAR_IS_ACTIVE_KEY = "active";
    private static final String NOTIFICATION_SEARCH_BAR_LAUNCH_COUNT_KEY = "launch_count";
    private static final String NOTIFICATION_SEARCH_BAR_ACTIVATE_AFTER_SECONDS_PERIOD_KEY = "activate_after_seconds_period";


    public static boolean getIsSearchBarActive(){
        final HashMap<String, String> notificationSearchBarWidgetRemoteConfigParamsHashMap
                = getNotificationSearchBarWidgetRemoteConfigParamsHashMap();
        return (isNotificationSearchBarWidgetRemoteConfigParamsHashMapCorrect(notificationSearchBarWidgetRemoteConfigParamsHashMap,
                NOTIFICATION_SEARCH_BAR_IS_ACTIVE_KEY))
                && getBooleanConvertedFromStringValue(notificationSearchBarWidgetRemoteConfigParamsHashMap
                .get(NOTIFICATION_SEARCH_BAR_IS_ACTIVE_KEY));
    }

    public static long getSearchBarWidgetActivateAfterSecondsPeriod(){
        final HashMap<String, String> notificationSearchBarWidgetRemoteConfigParamsHashMap
                = getNotificationSearchBarWidgetRemoteConfigParamsHashMap();
        return isNotificationSearchBarWidgetRemoteConfigParamsHashMapCorrect(notificationSearchBarWidgetRemoteConfigParamsHashMap,
                NOTIFICATION_SEARCH_BAR_ACTIVATE_AFTER_SECONDS_PERIOD_KEY)
                ? getLongConvertedFromStringValue(notificationSearchBarWidgetRemoteConfigParamsHashMap
                .get(NOTIFICATION_SEARCH_BAR_ACTIVATE_AFTER_SECONDS_PERIOD_KEY))
                : -1;
    }

    public static int getNotificationSearchBarLaunchParam() {
        final HashMap<String, String> notificationSearchBarWidgetRemoteConfigParamsHashMap
                = getNotificationSearchBarWidgetRemoteConfigParamsHashMap();
        return isNotificationSearchBarWidgetRemoteConfigParamsHashMapCorrect(notificationSearchBarWidgetRemoteConfigParamsHashMap,
                NOTIFICATION_SEARCH_BAR_LAUNCH_COUNT_KEY)
                ? getIntConvertedFromStringValue(notificationSearchBarWidgetRemoteConfigParamsHashMap.get(NOTIFICATION_SEARCH_BAR_LAUNCH_COUNT_KEY))
                : -1;
    }

    private static String[] parseParamsString(){
        final String delims = "[ ,=]+";
        return RemoteConfig.getNotificationSearchBarWidgetRemoteConfigParams().split(delims);
    }

    private static boolean getBooleanConvertedFromStringValue(final String stringValue){
        return stringValue != null && (stringValue.equalsIgnoreCase(String.valueOf(Boolean.TRUE))
                || stringValue.equalsIgnoreCase(String.valueOf(Boolean.FALSE))) && Boolean.parseBoolean(stringValue);
    }

    private static boolean isNotificationSearchBarWidgetRemoteConfigParamsHashMapCorrect(final HashMap<String, String>
                                                                                                 notificationSearchBarWidgetRemoteConfigParamsHashMap,
                                                                                         final String key){
        return notificationSearchBarWidgetRemoteConfigParamsHashMap != null
                && notificationSearchBarWidgetRemoteConfigParamsHashMap.containsKey(key);
    }

    private static long getLongConvertedFromStringValue(final String stringValue){
        long longValue = -1;

        if(stringValue != null) {
            try {
                longValue = Long.parseLong(stringValue);
            } catch (NumberFormatException nfe){
                nfe.printStackTrace();
            }
        }
        return longValue;
    }

    private static int getIntConvertedFromStringValue(final String stringValue){
        int intValue = -1;

        if(stringValue != null) {
            try {
                intValue = Integer.parseInt(stringValue);
            } catch (NumberFormatException nfe){
                nfe.printStackTrace();
            }
        }
        return intValue;
    }

    private static HashMap<String, String> getNotificationSearchBarWidgetRemoteConfigParamsHashMap(){
        final String[] notificationSearchBarWidgetRemoteConfigParamsArray = parseParamsString();
        final HashMap<String, String> notificationSearchBarWidgetRemoteConfigParamsHashMap = new HashMap<>();
        for(int i = 0; i < notificationSearchBarWidgetRemoteConfigParamsArray.length; i++) {
            notificationSearchBarWidgetRemoteConfigParamsHashMap.put(
                    notificationSearchBarWidgetRemoteConfigParamsArray[i],
                    notificationSearchBarWidgetRemoteConfigParamsArray[++i]);
        }
        return notificationSearchBarWidgetRemoteConfigParamsHashMap;
    }
}
