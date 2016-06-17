package com.earcs.grabm.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.earcs.grabm.R;

/**
 * @author Sachith Dickwella
 */
public class ErrorLoginDialog extends DialogFragment {

    public Dialog onCreateDialog(final Bundle savedInstance) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.title_error_login_dialog)
                .setMessage(R.string.message_error_login_dialog)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        return builder.create();
    }
}
