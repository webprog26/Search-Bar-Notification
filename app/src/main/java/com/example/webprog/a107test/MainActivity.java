package com.example.webprog.a107test;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.webprog.a107test.interfaces.AppMenuDelegate;
import com.example.webprog.a107test.interfaces.Presenter;
import com.example.webprog.a107test.interfaces.MainView;

import static com.example.webprog.a107test.interfaces.AppMenuDelegate.IS_SEARCH_BAR_ACTIVE_TAG;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String LOG_TAG = "MainView";

    private Presenter mPresenter;
    private AppMenuDelegate mAppMenuDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new PresenterImpl();
        mPresenter.setView(this);
        mAppMenuDelegate = new AppMenuDelegateImpl(mPresenter);

        EditText mEtSearch = (EditText) findViewById(R.id.et_search);

        final Intent onGoingIntent = getIntent();

        if(isSearchBarUserActionIntent(onGoingIntent)) {

            if(mEtSearch.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }

        if(savedInstanceState == null){
            if(!App.getPreferencesEditor().getBooleanValue(IS_SEARCH_BAR_ACTIVE_TAG)) {
                incrementTimesAppWasLaunchedCount();
            }
        }

        Log.i(LOG_TAG, "App was launched " +
                App.getPreferencesEditor().getIntValue(Presenter.TIMES_APP_WAS_LAUNCHED_TAG)
        + " times");
    }


    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().askUserToActivateSearchBar();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
       return mAppMenuDelegate.onPrepareAppOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return mAppMenuDelegate.onCreateAppOptionsMenu(menu, getMenuInflater());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       return mAppMenuDelegate.onAppOptionsItemSelected(item, super.onOptionsItemSelected(item));
    }

    @Override
    public boolean isSearchBarUserActionIntent(Intent searchBarUserActionIntent) {
        return searchBarUserActionIntent != null
                && searchBarUserActionIntent.getAction().equals(SearchNotificationImpl.SEARCH_BAR_ACTION);
    }

    @NonNull
    @Override
    public Handler getHandler() {
        return new Handler();
    }

    @Override
    public void incrementTimesAppWasLaunchedCount() {

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

    private Presenter getPresenter() {
        return mPresenter;
    }
}
