package com.earcs.grabm.util.message;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * @author Sachith Dickwella
 */
public class GrabInstanceIdListener extends InstanceIDListenerService {

    private static final String TAG = "GrabInstanceIDLS";

    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
