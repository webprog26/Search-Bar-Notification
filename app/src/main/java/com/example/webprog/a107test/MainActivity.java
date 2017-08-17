package com.example.webprog.a107test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.webprog.a107test.interfaces.AppMenuDelegate;
import com.example.webprog.a107test.interfaces.Presenter;
import com.example.webprog.a107test.interfaces.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String LOG_TAG = "MainView";

    private EditText mEtSearch;

    private Presenter mPresenter;
    private AppMenuDelegate mAppMenuDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(LOG_TAG, "params ");
        Log.i(LOG_TAG, "active " + NotificationWidgetParamsUtils.getIsSearchBarActive());
        Log.i(LOG_TAG, "launch_count " + NotificationWidgetParamsUtils.getNotificationSearchBarLaunchParam());
        Log.i(LOG_TAG, "activate_after_seconds_period " + NotificationWidgetParamsUtils.getSearchBarWidgetActivateAfterSecondsPeriod());

        mPresenter = new PresenterImpl();
        mPresenter.setView(this);
        mAppMenuDelegate = new AppMenuDelegateImpl(mPresenter);

        mEtSearch = (EditText) findViewById(R.id.et_search);

        final Intent onGoingIntent = getIntent();

        if(isSearchBarUserActionIntent(onGoingIntent)) {

            if(mEtSearch.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }

        if(savedInstanceState == null){
            if(!mPresenter.isNotificationSearchBarWidgetActive()) {
                incrementTimesAppWasLaunchedCount();
            }
        }

        //Just for testing
        Log.i(LOG_TAG, "App was launched " +
                App.getPreferenceEditorModule().getPreferencesEditor(this).getIntValue(Presenter.TIMES_APP_WAS_LAUNCHED_TAG)
        + " times");
    }


    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().askUserToActivateSearchBar();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
       return getPresenter().onPrepareAppOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return getPresenter().onCreateAppOptionsMenu(menu, getMenuInflater());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       return getPresenter().onAppOptionsItemSelected(item, super.onOptionsItemSelected(item));
    }

    private static boolean isSearchBarUserActionIntent(Intent searchBarUserActionIntent) {
        return searchBarUserActionIntent != null
                && searchBarUserActionIntent.getAction().equals(SearchNotificationImpl.SEARCH_BAR_ACTION);
    }

    @NonNull
    @Override
    public Handler getHandler() {
        return new Handler();
    }

    private void incrementTimesAppWasLaunchedCount() {

        final Presenter presenter = getPresenter();

        if(presenter != null) {
            try {
                presenter.incrementTimesAppWasLaunchedCount();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @NonNull
    @Override
    public Context getContext() {
        return MainActivity.this;
    }

    @NonNull
    @Override
    public AppMenuDelegate getAppMenuDelegate() {
        return mAppMenuDelegate;
    }

    @Override
    public boolean isSearchInputFieldInFocus() {
        return getCurrentFocus() != null && getCurrentFocus().getId() == getEtSearch().getId();
    }

    @Override
    public void hideKeyboard() {
        View view = getCurrentFocus();
        if(view != null) {
            InputMethodManager inputMethodManager
                    = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    private Presenter getPresenter() {
        return mPresenter;
    }

    private EditText getEtSearch() {
        return mEtSearch;
    }
}
