package com.earcs.grabm.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.earcs.grabm.R;
import com.earcs.grabm.util.executor.GrabAsyncTask;
import com.earcs.grabm.util.executor.GrabTaskHandler;

/**
 * @author Sachith Dickwella
 */
public class ValidateMemberNumberDialog extends DialogFragment {

    private static final String TAG = ValidateMemberNumberDialog.class.getName();
    private String memberNo;

    @Override
    public Dialog onCreateDialog(Bundle savedBundle) {
        setCancelable(false);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View memberValidation = inflater.inflate(R.layout.dialog_member_validation, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(memberValidation)
                .setTitle(getString(R.string.title_validate_member_no))
                .setMessage(getString(R.string.message_validate_member_no))
                .setPositiveButton(getString(R.string.dialog_continue), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        memberNo = ((EditText) memberValidation.findViewById(R.id.memberNo)).getText().toString();
                        try {
                            GrabTaskHandler.executeSingleThread(runnable);
                            dismiss();
                        } catch (InterruptedException ex) {
                            Log.e(TAG, ex.toString());
                        }
                    }
                }).setNegativeButton(getString(R.string.dialog_cancle), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

        return builder.create();
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            GrabAsyncTask.ValidateUser validateUser = new GrabAsyncTask.ValidateUser(getActivity());
            validateUser.execute(memberNo);
        }
    };
}
