package com.example.webprog.a107test.interfaces;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by webprog on 16.08.17.
 */

public interface SearchNotificationModule {

    @NonNull
    SearchNotification getSearchNotification(final Context context);
}
