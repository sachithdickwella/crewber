package com.earcs.grabm.util.message;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.earcs.grabm.R;
import com.earcs.grabm.util.GrabMConstant;
import com.earcs.grabm.util.executor.GrabAsyncTask;
import com.earcs.grabm.util.executor.GrabTaskHandler;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * @author Sachith Dickwella
 */
public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"global"};

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(GrabMConstant.GOOGLE_MESSAGING_SENDER_ID,
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE);

            sendTokenToServer(token);
            subscribeTopics(token);

            sharedPreferences.edit().putBoolean(GrabMConstant.QuickstartPreferences.SENT_TOKEN_TO_SERVER, true).apply();
        } catch (Exception ex) {
            sharedPreferences.edit().putBoolean(GrabMConstant.QuickstartPreferences.SENT_TOKEN_TO_SERVER, false).apply();
        }
        Intent registerIntent = new Intent(GrabMConstant.QuickstartPreferences.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registerIntent);
    }

    private void sendTokenToServer(final String token) {
        final SharedPreferences mSharedPreferences = getSharedPreferences(GrabMConstant.FILE_KEY, MODE_PRIVATE);
        final String mLoggedStatus = mSharedPreferences.getString(
                GrabMConstant.KEY_LOGIN_STATUS, getString(R.string.empty_string));
        try {
            if (mLoggedStatus.equals(GrabMConstant.LoginStatus.LOGIN.toString())) {
                final String memNo = mSharedPreferences.getString(GrabMConstant.KEY_MEMBER_NO, getString(R.string.empty_string));
                final Runnable updateToken = new Runnable() {
                    @Override
                    public void run() {
                        GrabAsyncTask.SendTokenToServer tokenToServer = new GrabAsyncTask.SendTokenToServer(getApplicationContext());
                        tokenToServer.execute(memNo, token);
                    }
                };
                GrabTaskHandler.executeSingleThread(updateToken);
            }
        } catch (InterruptedException ex) {
            Log.e(TAG, ex.toString());
        }
    }

    private void subscribeTopics(String token) throws IOException {
        GcmPubSub gcmPubSub = GcmPubSub.getInstance(this);
        for (String topic : TOPICS) {
            gcmPubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
}
