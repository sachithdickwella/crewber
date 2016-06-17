package com.earcs.grabm.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.earcs.grabm.R;
import com.earcs.grabm.util.GrabMConstant;
import com.earcs.grabm.util.executor.GrabAsyncTask;
import com.earcs.grabm.util.executor.GrabTaskHandler;

/**
 * @author Sachith Dickwella
 */
public class ValidatePINDialog extends DialogFragment {

    private static final String TAG = ValidatePINDialog.class.getName();
    private String PIN;
    private String memNo;

    @Override
    public Dialog onCreateDialog(Bundle saveBundle) {
        setCancelable(false);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View pinValidation = inflater.inflate(R.layout.dialog_pin_validation, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.title_validate_pin))
                .setMessage(getString(R.string.message_validate_pin))
                .setView(pinValidation)
                .setPositiveButton(R.string.dialog_continue, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PIN = ((EditText) pinValidation.findViewById(R.id.pin_)).getText().toString();
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(
                                GrabMConstant.FILE_KEY, Context.MODE_PRIVATE);
                        memNo = sharedPreferences.getString(GrabMConstant.KEY_MEMBER_NO, null);
                        try {
                            GrabTaskHandler.executeSingleThread(pin_validate);
                            dismiss();
                        } catch (InterruptedException ex) {
                            Log.e(TAG, ex.toString());
                        }
                    }
                }).setNegativeButton(R.string.dialog_cancle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
        return builder.create();
    }

    private Runnable pin_validate = new Runnable() {
        @Override
        public void run() {
            GrabAsyncTask.ValidatePin validatePin = new GrabAsyncTask.ValidatePin(getActivity());
            validatePin.execute(PIN, memNo);
        }
    };
}
