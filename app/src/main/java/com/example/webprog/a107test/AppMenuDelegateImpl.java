package com.example.webprog.a107test;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.webprog.a107test.interfaces.AppMenuDelegate;
import com.example.webprog.a107test.interfaces.MainView;
import com.example.webprog.a107test.interfaces.PreferencesEditor;
import com.example.webprog.a107test.interfaces.Presenter;

/**
 * Created by webprog on 15.08.17.
 */

public class AppMenuDelegateImpl implements AppMenuDelegate {

    private final Presenter mPresenter;

    public AppMenuDelegateImpl(Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public boolean onPrepareAppOptionsMenu(Menu menu) {
        MenuItem searchBarItem = menu.findItem(R.id.action_start_notification);
        searchBarItem.setVisible(NotificationWidgetParamsUtils.getIsSearchBarActive());

        final Presenter presenter= getPresenter();

        if(presenter != null) {

            final MainView mainView = presenter.getMainView();

            if(mainView != null) {

                final Context context = mainView.getContext();

                if(context != null) {

                    final PreferencesEditor preferencesEditor = App.getPreferenceEditorModule()
                            .getPreferencesEditor(context);

                    if(preferencesEditor != null) {

                        searchBarItem.setChecked(preferencesEditor.getBooleanValue(IS_SEARCH_BAR_ACTIVE_TAG));
                        return true;
                    }

                }
            }
        }

        return false;
    }

    @Override
    public boolean onCreateAppOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onAppOptionsItemSelected(MenuItem item, boolean defValue) {

        final Presenter presenter= getPresenter();

        if(presenter != null) {

            final MainView mainView = presenter.getMainView();

            if(mainView != null) {

                final Context context = mainView.getContext();

                if(context != null) {

                    final PreferencesEditor preferencesEditor = App.getPreferenceEditorModule()
                            .getPreferencesEditor(context);

                    if(preferencesEditor != null) {

                        switch (item.getItemId()){
                            case R.id.action_start_notification:
                                if(!item.isChecked()) {
                                    preferencesEditor.setBooleanValue(IS_SEARCH_BAR_ACTIVE_TAG, true);
                                    item.setChecked(true);
                                    presenter.sendNotification();
                                    presenter.resetTimesAppWasLaunchedCount();
                                } else {
                                    preferencesEditor.setBooleanValue(IS_SEARCH_BAR_ACTIVE_TAG, false);
                                    item.setChecked(false);
                                    presenter.cancelNotification();

                                    if(mainView.isSearchInputFieldInFocus()){
                                        mainView.hideKeyboard();
                                    }
                                }
                                return true;
                            default:
                                return defValue;
                        }
                    }

                }
            }
        }
        return defValue;
    }

    private Presenter getPresenter() {
        return mPresenter;
    }
}
