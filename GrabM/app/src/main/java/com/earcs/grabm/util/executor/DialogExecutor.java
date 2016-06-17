package com.earcs.grabm.util.executor;

import android.app.Activity;
import android.app.DialogFragment;

import com.earcs.grabm.util.DialogType;
import com.earcs.grabm.widget.ConnectionDialog;
import com.earcs.grabm.widget.ConnectionTryAgainDialog;
import com.earcs.grabm.widget.ErrorLoginDialog;
import com.earcs.grabm.widget.PleaseWaitDialog;
import com.earcs.grabm.widget.RequestVehicleDialog;
import com.earcs.grabm.widget.ValidateMemberNumberDialog;
import com.earcs.grabm.widget.ValidatePINDialog;

/**
 * @author Sachith Dickwella
 */
public final class DialogExecutor {

    private final Activity context;
    private final Interceptable interceptable;

    private DialogExecutor(Activity context, Interceptable interception) {
        this.context = context;
        this.interceptable = interception;
    }

    public static DialogExecutor getDialogExceutor(
            Activity context, Interceptable interceptable) {
        return new DialogExecutor(context, interceptable);
    }

    public DialogFragment showDialog(DialogType dialogType) {
        boolean isIntercepted = (interceptable != null);
        DialogFragment dFragment = null;
        if (isIntercepted) {
            interceptable.doBefore();
        }
        switch (dialogType) {
            case DIALOG_CONNECTION:
                dFragment = new ConnectionDialog();
                dFragment.show(context.getFragmentManager(), null);
                break;
            case DIALOG_PLEASEWAIT:
                dFragment = new PleaseWaitDialog();
                dFragment.show(context.getFragmentManager(), null);
                break;
            case DIALOG_ERROR_LOGIN:
                dFragment = new ErrorLoginDialog();
                dFragment.show(context.getFragmentManager(), null);
                break;
            case DIALOG_CONNECTION_INIT:
                dFragment = new ConnectionTryAgainDialog();
                dFragment.show(context.getFragmentManager(), null);
                break;
            case DIALOG_MEMBERNUMBER_VALIDATION:
                dFragment = new ValidateMemberNumberDialog();
                dFragment.show(context.getFragmentManager(), null);
                break;
            case DIALOG_PIN_VALIDATION:
                dFragment = new ValidatePINDialog();
                dFragment.show(context.getFragmentManager(), null);
                break;
            case DIALOG_REQUEST_VEHICLE:
                dFragment = new RequestVehicleDialog();
                dFragment.show(context.getFragmentManager(), null);
            default:
                break;
        }
        if (isIntercepted) {
            interceptable.doAfter();
        }
        return dFragment;
    }
}
