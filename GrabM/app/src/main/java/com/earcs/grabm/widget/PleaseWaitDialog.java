package com.earcs.grabm.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import com.earcs.grabm.R;

/**
 * @author Sachith Dickwella
 */
public class PleaseWaitDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstances) {
        setCancelable(false);
        return new AlertDialog.Builder(getActivity())
                .setView(R.layout.dialog_please_wait)
                .create();
    }
}
