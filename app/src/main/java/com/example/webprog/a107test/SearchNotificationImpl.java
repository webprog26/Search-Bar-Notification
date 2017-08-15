package com.example.webprog.a107test;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.example.webprog.a107test.interfaces.SearchNotification;

/**
 * Created by webprog on 09.08.17.
 */

public class SearchNotificationImpl implements SearchNotification {

    private static final String LOG_TAG = "SearchNotificationImpl";
    private static final int NOTIFICATION_ID = 1;
    public static final String SEARCH_BAR_ACTION = "action_search_bar";


    private final Context mContext;
    private final NotificationManager mNotificationManager;
    @IdRes
    private final int mNotificationIconResId = R.mipmap.ic_launcher;

    public SearchNotificationImpl(final Context context) {
        this.mContext = context;
        this.mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void sendForegroundNotification() {

        android.support.v4.app.NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getContext())
                .setSmallIcon(getNotificationIconResId())
                .setContent(getContentView())
                .setAutoCancel(false)
                .setOngoing(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentIntent(getContentIntent())
                .setDefaults(Notification.DEFAULT_ALL);

        getNotificationManager().notify(NOTIFICATION_ID, notificationBuilder.build());
    }

    @Override
    public void cancelForegroundNotification() {
        mNotificationManager.cancel(NOTIFICATION_ID);
    }

    @NonNull
    private Context getContext() {
        return mContext;
    }

    @NonNull
    private Intent getNotificationIntent() {
        Intent notificationIntent = new Intent(mContext, MainActivity.class);
        notificationIntent.setAction(SEARCH_BAR_ACTION);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return notificationIntent;
    }

    @NonNull
    private PendingIntent getContentIntent() {
        return PendingIntent.getActivity(
                getContext(),
                0,
                getNotificationIntent(),
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @NonNull
    private RemoteViews getContentView(){
        return new RemoteViews(getContext().getPackageName(), R.layout.search_notification);
    }

    private int getNotificationIconResId() {
        return mNotificationIconResId;
    }

    @NonNull
    private NotificationManager getNotificationManager() {
        return mNotificationManager;
    }
}
