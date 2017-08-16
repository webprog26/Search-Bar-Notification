package com.example.webprog.a107test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.webprog.a107test.interfaces.SearchNotification;

import static com.example.webprog.a107test.interfaces.AppMenuDelegate.IS_SEARCH_BAR_ACTIVE_TAG;

public class SearchBarNotificationReceiver extends BroadcastReceiver {

    private static final String ACTION_BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";
    private static final String ACTION_QUICK_BOOT_POWERON = "android.intent.action.QUICKBOOT_POWERON";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(isIntentNotNull(intent)) {
            final String action = intent.getAction();

            if(isActionCorrect(action)) {

                final SearchNotification searchNotification = App.getSearchNotificationModule()
                        .getSearchNotification(context);

                    if(searchNotification != null) {

                        if(App.getPreferenceEditorModule().getPreferencesEditor(context)
                                .getBooleanValue(IS_SEARCH_BAR_ACTIVE_TAG)) {
                            searchNotification.sendForegroundNotification();
                        }
                    }

                }
            }
    }

    private static boolean isIntentNotNull(final Intent intent){
        return intent != null;
    }

    private static boolean isActionCorrect(final String action){
        return action != null
                && (action.equals(ACTION_BOOT_COMPLETED) || action.equals(ACTION_QUICK_BOOT_POWERON));
    }
}
