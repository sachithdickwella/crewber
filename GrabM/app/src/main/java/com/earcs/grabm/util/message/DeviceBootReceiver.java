package com.earcs.grabm.util.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.earcs.grabm.util.GrabMConstant;

/**
 * @author Sachith Dickwella
 */
public class DeviceBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            context.startService(GrabMConstant.LocationService.getIntent(context));
        }
    }
}
