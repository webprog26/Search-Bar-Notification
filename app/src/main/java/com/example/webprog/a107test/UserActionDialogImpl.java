package com.example.webprog.a107test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import com.example.webprog.a107test.interfaces.Presenter;
import com.example.webprog.a107test.interfaces.UserActionDialog;

import static com.example.webprog.a107test.interfaces.AppMenuDelegate.IS_SEARCH_BAR_ACTIVE_TAG;

/**
 * Created by webprog on 15.08.17.
 */

public class UserActionDialogImpl implements UserActionDialog {

    @Override
    public void showUserActionDialog(final Presenter presenter) {
        final AlertDialog askUserToActivateSearchBarAlertDialog
                = getAskUserToActivateSearchBarAlertDialog(presenter);

        if(askUserToActivateSearchBarAlertDialog != null){
            askUserToActivateSearchBarAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    presenter.resetTimesAppWasLaunchedCount();
                }
            });
            askUserToActivateSearchBarAlertDialog.show();
        }
    }

    @Nullable
    private AlertDialog getAskUserToActivateSearchBarAlertDialog(final Presenter presenter){
        final Context context = presenter.getMainView().getContext();
        if(context != null && context instanceof Activity) {
            AlertDialog.Builder askUserToActivateSearchBarAlertDialog = new AlertDialog.Builder(context);
            askUserToActivateSearchBarAlertDialog.setTitle(context.getString(R.string.activate_search_bar_alert_dialog_title));
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            final View dialogView = inflater.inflate(R.layout.activate_search_bar_dialog, null);

            askUserToActivateSearchBarAlertDialog.setView(dialogView);
            askUserToActivateSearchBarAlertDialog.setPositiveButton(context.getString(R.string.activate), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    App.getPreferenceEditorModule().getPreferencesEditor(context)
                            .setBooleanValue(IS_SEARCH_BAR_ACTIVE_TAG, true);
                    presenter.sendNotification();
                }
            });

            askUserToActivateSearchBarAlertDialog.setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            askUserToActivateSearchBarAlertDialog.setCancelable(true);
            return askUserToActivateSearchBarAlertDialog.create();
        }
        return null;
    }
}
