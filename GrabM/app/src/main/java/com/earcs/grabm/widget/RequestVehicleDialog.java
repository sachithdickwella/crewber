package com.earcs.grabm.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.earcs.grabm.R;
import com.earcs.grabm.util.executor.GrabAsyncTask;
import com.earcs.grabm.util.executor.GrabTaskHandler;

/**
 * @author Sachith Dickwella.
 */
public class RequestVehicleDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle saveBundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.title_requesting_vehicle)
                .setMessage(R.string.message_requesting_vehicle)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            GrabTaskHandler.executeSingleThread(requestVehicle);
                        } catch (InterruptedException ex) {
                            Log.e(ex.getMessage(), ex.toString());
                        }
                        dismiss();
                    }
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

        return builder.create();
    }

    private Runnable requestVehicle = new Runnable() {
        @Override
        public void run() {
            GrabAsyncTask.RequestVehicle requestVehicle = new GrabAsyncTask.RequestVehicle(getActivity());
            requestVehicle.execute((Void) null);
        }
    };
}
