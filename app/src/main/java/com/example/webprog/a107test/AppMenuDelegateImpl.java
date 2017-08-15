package com.example.webprog.a107test;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.webprog.a107test.interfaces.AppMenuDelegate;
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

        searchBarItem.setChecked(App.getPreferencesEditor().getBooleanValue(IS_SEARCH_BAR_ACTIVE_TAG));

        if(RemoteConfig.getIsSearchBarActive()) {
            searchBarItem.setVisible(true);
        } else {
            searchBarItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onCreateAppOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onAppOptionsItemSelected(MenuItem item, boolean defValue) {

        final Presenter presenter = getPresenter();

        switch (item.getItemId()){
            case R.id.action_start_notification:
                if(!item.isChecked()) {
                    App.getPreferencesEditor().setBooleanValue(IS_SEARCH_BAR_ACTIVE_TAG, true);
                    item.setChecked(true);
                    presenter.sendNotification();
                    presenter.resetTimesAppWasLaunchedCount();
                } else {
                    App.getPreferencesEditor().setBooleanValue(IS_SEARCH_BAR_ACTIVE_TAG, false);
                    item.setChecked(false);
                    presenter.cancelNotification();
                }
                return true;
            default:
                return defValue;
        }
    }

    private Presenter getPresenter() {
        return mPresenter;
    }
}
