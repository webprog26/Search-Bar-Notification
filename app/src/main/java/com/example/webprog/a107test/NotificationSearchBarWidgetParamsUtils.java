package com.example.webprog.a107test;

import java.util.HashMap;

/**
 * Created by webprog on 17.08.17.
 */

public class NotificationSearchBarWidgetParamsUtils {

    //Notification search bar widget Firebase remote config keys
    private static final String NOTIFICATION_SEARCH_BAR_IS_ACTIVE_KEY = "active";
    private static final String NOTIFICATION_SEARCH_BAR_LAUNCH_COUNT_KEY = "launch_count";
    private static final String NOTIFICATION_SEARCH_BAR_OFFERING_DIALOG_SHOW_AFTER_SECONDS_PERIOD_KEY = "offering_dialog_show_after_seconds_period";
    private static final String NOTIFICATION_SEARCH_BAR_IS_ACTIVATED_BY_DEFAULT_KEY = "activated_by_default";

    /**
     * Reads Notification search bar activeness state from Firebase remote config params
     * @return true is parameter exists and and active, otherwise - false
     */
    public static boolean getIsNotificationSearchBarWidgetActive(){
        final HashMap<String, String> notificationSearchBarWidgetRemoteConfigParamsHashMap
                = getNotificationSearchBarWidgetRemoteConfigParamsHashMap();
        return (isNotificationSearchBarWidgetRemoteConfigParamsHashMapCorrect(notificationSearchBarWidgetRemoteConfigParamsHashMap,
                NOTIFICATION_SEARCH_BAR_IS_ACTIVE_KEY))
                && getBooleanConvertedFromStringValue(notificationSearchBarWidgetRemoteConfigParamsHashMap
                .get(NOTIFICATION_SEARCH_BAR_IS_ACTIVE_KEY));
    }

    /**
     * Reads Notification search bar offering dialog show after seconds param value
     * from Firebase remote config params
     * @return long which equals the period offering dialog with proposition to activate notification search
     * bar widget will be shown after. -1 if this Firebase remote config param can not be converted to long
     */
    public static long getNotificationSearchBarWidgetOfferingDialogShowAfterSecondsPeriod(){
        final HashMap<String, String> notificationSearchBarWidgetRemoteConfigParamsHashMap
                = getNotificationSearchBarWidgetRemoteConfigParamsHashMap();
        return isNotificationSearchBarWidgetRemoteConfigParamsHashMapCorrect(notificationSearchBarWidgetRemoteConfigParamsHashMap,
                NOTIFICATION_SEARCH_BAR_OFFERING_DIALOG_SHOW_AFTER_SECONDS_PERIOD_KEY)
                ? getLongConvertedFromStringValue(notificationSearchBarWidgetRemoteConfigParamsHashMap
                .get(NOTIFICATION_SEARCH_BAR_OFFERING_DIALOG_SHOW_AFTER_SECONDS_PERIOD_KEY)) * 1000
                : -1;
    }

    /**
     * Reads Notification search bar offering dialog show after app launches param value
     * from Firebase remote config params
     * @return int which equals the times app was launched offering dialog with proposition to activate notification search
     * bar widget will be shown after. -1 if this Firebase remote config param can not be converted to int
     */
    public static int getNotificationSearchBarLaunchParam() {
        final HashMap<String, String> notificationSearchBarWidgetRemoteConfigParamsHashMap
                = getNotificationSearchBarWidgetRemoteConfigParamsHashMap();
        return isNotificationSearchBarWidgetRemoteConfigParamsHashMapCorrect(notificationSearchBarWidgetRemoteConfigParamsHashMap,
                NOTIFICATION_SEARCH_BAR_LAUNCH_COUNT_KEY)
                ? getIntConvertedFromStringValue(notificationSearchBarWidgetRemoteConfigParamsHashMap.get(NOTIFICATION_SEARCH_BAR_LAUNCH_COUNT_KEY))
                : -1;
    }

    /**
     * Reads Notification search bar activeness state from Firebase remote config params
     * @return true is parameter exists and and active, otherwise - false
     */
    public static boolean getIsNotificationSearchBarWidgetActivatedByDefault(){
        final HashMap<String, String> notificationSearchBarWidgetRemoteConfigParamsHashMap
                = getNotificationSearchBarWidgetRemoteConfigParamsHashMap();
        return (isNotificationSearchBarWidgetRemoteConfigParamsHashMapCorrect(notificationSearchBarWidgetRemoteConfigParamsHashMap,
                NOTIFICATION_SEARCH_BAR_IS_ACTIVATED_BY_DEFAULT_KEY))
                && getBooleanConvertedFromStringValue(notificationSearchBarWidgetRemoteConfigParamsHashMap
                .get(NOTIFICATION_SEARCH_BAR_IS_ACTIVATED_BY_DEFAULT_KEY));
    }

    /**
     * Parses Firebase remote config params string
     * @return array of {@link String} which contains remote config paramas keys and values
     * or null, if Firebase remote config params string is null or empty
     */
    private static String[] parseParamsString(){
        final String notificationSearchBarWidgetRemoteConfigParams = RemoteConfig.getNotificationSearchBarWidgetRemoteConfigParams();
        final String delims = "[ ,=]+";
        return notificationSearchBarWidgetRemoteConfigParams != null
                && notificationSearchBarWidgetRemoteConfigParams.length() > 0
                ? notificationSearchBarWidgetRemoteConfigParams.split(delims) : null;
    }

    /**
     * Checks, that given {@link HashMap} exists and contains given key
     * @param notificationSearchBarWidgetRemoteConfigParamsHashMap {@link HashMap}
     * @param key {@link String}
     * @return true if {@link HashMap} exists and contains given key, otherwise - false
     */
    private static boolean isNotificationSearchBarWidgetRemoteConfigParamsHashMapCorrect(final HashMap<String, String>
                                                                                                 notificationSearchBarWidgetRemoteConfigParamsHashMap,
                                                                                         final String key){
        return notificationSearchBarWidgetRemoteConfigParamsHashMap != null
                && notificationSearchBarWidgetRemoteConfigParamsHashMap.containsKey(key);
    }

    /**
     * Converts string to boolean
     * @param stringValue {@link String}
     * @return true if parameter equals true and can be converted into boolean, otherwise - false
     */
    private static boolean getBooleanConvertedFromStringValue(final String stringValue){
        return stringValue != null && (stringValue.equalsIgnoreCase(String.valueOf(Boolean.TRUE))
                || stringValue.equalsIgnoreCase(String.valueOf(Boolean.FALSE))) && Boolean.parseBoolean(stringValue);
    }

    /**
     * Converts string to long
     * @param stringValue {@link String}
     * @return converted long value if string can be converted into long, otherwise - 1
     */
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

    /**
     * Converts string to int
     * @param stringValue {@link String}
     * @return converted int value if string can be converted into int, otherwise - 1
     */
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

    /**
     * Converts array of {@link String} which contains notification search bar widget Firebase remote config params
     * @return {@link HashMap} which contains notification search bar widget Firebase remote config
     * params in "key=value" format
     */
    private static HashMap<String, String> getNotificationSearchBarWidgetRemoteConfigParamsHashMap(){
        final String[] notificationSearchBarWidgetRemoteConfigParamsArray = parseParamsString();
        final HashMap<String, String> notificationSearchBarWidgetRemoteConfigParamsHashMap = new HashMap<>();

        if(notificationSearchBarWidgetRemoteConfigParamsArray != null) {

            for(int i = 0; i < notificationSearchBarWidgetRemoteConfigParamsArray.length; i++) {
                notificationSearchBarWidgetRemoteConfigParamsHashMap.put(
                        notificationSearchBarWidgetRemoteConfigParamsArray[i],
                        notificationSearchBarWidgetRemoteConfigParamsArray[++i]);
            }
        }
        return notificationSearchBarWidgetRemoteConfigParamsHashMap;
    }
}
