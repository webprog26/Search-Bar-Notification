package com.example.webprog.a107test;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.webprog.a107test.interfaces.AppMenuDelegate;
import com.example.webprog.a107test.interfaces.MainView;
import com.example.webprog.a107test.interfaces.PreferencesEditor;
import com.example.webprog.a107test.interfaces.Presenter;
import com.example.webprog.a107test.interfaces.SearchNotification;

import static com.example.webprog.a107test.interfaces.AppMenuDelegate.IS_SEARCH_BAR_ACTIVE_TAG;

/**
 * Created by webprog on 15.08.17.
 */

public class PresenterImpl implements Presenter {

    private static final String LOG_TAG = "Presenter";

    private MainView mainView;

    @Override
    public void setView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void sendNotification() {
        final SearchNotification searchNotification = getSearchNotification();

            if(searchNotification != null) {
                searchNotification.sendForegroundNotification();
            }
    }

    @Override
    public void askUserToActivateSearchBar() {
        final PreferencesEditor preferencesEditor = getPreferencesEditor();
            if(preferencesEditor != null) {

                final int currentTimesAppWasLaunchedCount = preferencesEditor
                        .getIntValue(Presenter.TIMES_APP_WAS_LAUNCHED_TAG);

                if(currentTimesAppWasLaunchedCount == RemoteConfig.getTimesToShowActivateSearchBarDialog()) {

                    resetTimesAppWasLaunchedCount();

                    final Handler handler = mainView.getHandler();

                    if(handler != null) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                new UserActionDialogImpl().showUserActionDialog(PresenterImpl.this);
                            }
                        }, RemoteConfig.getSearchBarWidgetUserDialogShowingTimePeriod());
                    }

                }

            }
    }

    @Override
    public void cancelNotification() {
        final SearchNotification searchNotification = getSearchNotification();

            if(searchNotification != null) {
                searchNotification.cancelForegroundNotification();
            }
    }

    @Override
    public void incrementTimesAppWasLaunchedCount() throws Exception {

        final PreferencesEditor preferencesEditor = getPreferencesEditor();

            if(preferencesEditor != null) {
                final int currentTimesAppWasLaunchedCount = preferencesEditor
                    .getIntValue(Presenter.TIMES_APP_WAS_LAUNCHED_TAG);
                        if(currentTimesAppWasLaunchedCount != ILLEGAL_LAUNCHES_COUNT) {
                            preferencesEditor.setIntValue(TIMES_APP_WAS_LAUNCHED_TAG, (currentTimesAppWasLaunchedCount + 1));
                        } else {
                            throw new Exception(SHARED_PREFERENCES_INITIALIZING_ERROR_MESSAGE);
                        }
            }
    }

    @Override
    public void resetTimesAppWasLaunchedCount() {
        final PreferencesEditor preferencesEditor = getPreferencesEditor();

            if(preferencesEditor != null) {
                preferencesEditor.setIntValue(TIMES_APP_WAS_LAUNCHED_TAG, Presenter.ZERO_LAUNCHES_COUNT);
            }

    }

    @NonNull
    @Override
    public MainView getMainView() {
        return mainView;
    }

    @Override
    public boolean onPrepareAppOptionsMenu(Menu menu) {
        final MainView mainView = getMainView();
        if(mainView != null) {
            final AppMenuDelegate appMenuDelegate = getMainView().getAppMenuDelegate();
            return (appMenuDelegate != null ? appMenuDelegate.onPrepareAppOptionsMenu(menu) : false);
        }
        return false;
    }

    @Override
    public boolean onCreateAppOptionsMenu(Menu menu, MenuInflater menuInflater) {
        final MainView mainView = getMainView();
        if(mainView != null) {
            final AppMenuDelegate appMenuDelegate = getMainView().getAppMenuDelegate();
            return (appMenuDelegate != null ? appMenuDelegate.onCreateAppOptionsMenu(menu, menuInflater) : false);
        }
        return false;
    }

    @Override
    public boolean onAppOptionsItemSelected(MenuItem item, boolean defValue) {
        final MainView mainView = getMainView();
        if(mainView != null) {
            final AppMenuDelegate appMenuDelegate = getMainView().getAppMenuDelegate();
            return (appMenuDelegate != null ? appMenuDelegate.onAppOptionsItemSelected(item, defValue) : false);
        }
        return false;
    }

    @Override
    public boolean isNotificationSearchBarWidgetActive() {
        final PreferencesEditor preferencesEditor = getPreferencesEditor();

            if(preferencesEditor != null) {

                return preferencesEditor.getBooleanValue(IS_SEARCH_BAR_ACTIVE_TAG);
            }
        return false;
    }

    private PreferencesEditor getPreferencesEditor(){
        final MainView mainView = getMainView();
        PreferencesEditor preferencesEditor = null;
        if(mainView != null) {
            final Context context = mainView.getContext();

            if(context != null) {
                preferencesEditor = App.getPreferenceEditorModule()
                        .getPreferencesEditor(context);
            }
        }
        return preferencesEditor;
    }

    private SearchNotification getSearchNotification(){
        final MainView mainView = getMainView();
        SearchNotification searchNotification = null;
        if(mainView != null) {
            final Context context = mainView.getContext();

            if(context != null) {

                searchNotification = App.getSearchNotificationModule()
                        .getSearchNotification(context);
            }
        }
        return searchNotification;
    }
}
