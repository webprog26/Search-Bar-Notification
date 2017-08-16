package com.example.webprog.a107test;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.webprog.a107test.interfaces.SearchNotification;
import com.example.webprog.a107test.interfaces.SearchNotificationModule;

/**
 * Created by webprog on 16.08.17.
 */

public class SearchNotificationModuleImpl implements SearchNotificationModule {

    @NonNull
    @Override
    public SearchNotification getSearchNotification(Context context) {
        return new SearchNotificationImpl(context);
    }
}
