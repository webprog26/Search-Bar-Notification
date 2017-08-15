package com.example.webprog.a107test;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.example.webprog.a107test.interfaces.MainView;
import com.example.webprog.a107test.interfaces.PreferencesEditor;
import com.example.webprog.a107test.interfaces.Presenter;

/**
 * Created by webprog on 15.08.17.
 */

public class PresenterImpl implements Presenter {

    private MainView mainView;

    @Override
    public void setView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void sendNotification() {
        App.getSearchNotification().sendForegroundNotification();
    }

    @Override
    public void askUserToActivateSearchBar() {

        final PreferencesEditor preferencesEditor = App.getPreferencesEditor();

        if(preferencesEditor != null) {
            final int currentTimesAppWasLaunchedCount = preferencesEditor
                    .getIntValue(Presenter.TIMES_APP_WAS_LAUNCHED_TAG);

            if(currentTimesAppWasLaunchedCount == RemoteConfig.getTimesToShowActivateSearchBarDialog()) {

                final MainView mainView = getMainView();

                if(mainView != null) {
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
    }

    @Override
    public void cancelNotification() {
        App.getSearchNotification().cancelForegroundNotification();
    }


    @Override
    public void incrementTimesAppWasLaunchedCount() throws Exception {
        final PreferencesEditor preferencesEditor = App.getPreferencesEditor();

        if(preferencesEditor != null) {
            final int currentTimesAppWasLaunchedCount = preferencesEditor.getIntValue(TIMES_APP_WAS_LAUNCHED_TAG);

            if(currentTimesAppWasLaunchedCount != -1){
                preferencesEditor.setIntValue(TIMES_APP_WAS_LAUNCHED_TAG, (currentTimesAppWasLaunchedCount + 1));
            } else {
                throw new Exception(SHARED_PREFERENCES_INITIALIZING_ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void resetTimesAppWasLaunchedCount() {
        final PreferencesEditor preferencesEditor = App.getPreferencesEditor();

        if(preferencesEditor != null) {
            preferencesEditor.setIntValue(TIMES_APP_WAS_LAUNCHED_TAG, 0);
        }
    }


    @NonNull
    @Override
    public MainView getMainView() {
        return mainView;
    }
}
