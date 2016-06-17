package com.earcs.grabm.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.earcs.grabm.R;
import com.earcs.grabm.SplashActivity;
import com.earcs.grabm.util.DialogType;
import com.earcs.grabm.util.NetConnectionStatus;
import com.earcs.grabm.util.executor.DialogExecutor;

/**
 * @author Sachith Dickwella
 */
public class ConnectionTryAgainDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedBundle) {
        setCancelable(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.title_connection_init_dialog))
                .setMessage(getString(R.string.message_connection_init_dialog))
                .setPositiveButton(getString(R.string.dialog_tryagain), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (NetConnectionStatus.isConnected(getActivity())) {
                            SplashActivity.SplashRunner runner = new SplashActivity.SplashRunner(SplashActivity.getSharedContext());
                            runner.execute((Void) null);
                            dismiss();
                        } else {
                            DialogExecutor.getDialogExceutor(getActivity(), null).showDialog(DialogType.DIALOG_CONNECTION_INIT);
                        }
                    }
                });
        return builder.create();
    }
}
