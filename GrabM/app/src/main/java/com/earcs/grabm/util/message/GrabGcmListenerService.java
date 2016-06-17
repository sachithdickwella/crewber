package com.earcs.grabm.util.message;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.earcs.grabm.R;
import com.earcs.grabm.SplashActivity;
import com.earcs.grabm.util.GrabMConstant;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * @author Sachith Dickwella
 */
public class GrabGcmListenerService extends GcmListenerService
        implements GrabMConstant {


    @Override
    public void onMessageReceived(String from, Bundle data) {
        String short_message = data.getString("short_message"),
                title = data.getString("title"),
                long_message = data.getString("long_message"),
                type = data.getString("type");

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext())
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_small_notification)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_notification))
                .setContentText(short_message)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setLights(Color.GREEN, 1000, 200)
                .setContentIntent(PendingIntent.getActivity(this, 0,
                        new Intent(this, SplashActivity.class), 0))
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getBaseContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(GrabMConstant.MESSAGE_NOTIFICATION_ID, builder.build());

        SharedPreferences mSharedPreferences = getSharedPreferences(GrabMConstant.FILE_KEY, MODE_PRIVATE);
        mSharedPreferences.edit()
                .putString(NotificationContent.TYPE.toString(), type)
                .putString(NotificationContent.TITLE.toString(), title)
                .putString(NotificationContent.LONG_MESSAGE.toString(), long_message)
                .apply();

    }
}
