package com.earcs.grabm.util.message;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.earcs.grabm.util.GrabMConstant;
import com.earcs.grabm.util.NetConnectionStatus;
import com.earcs.grabm.util.executor.GrabAsyncTask;
import com.earcs.grabm.util.executor.GrabTaskHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Sachith Dickwella
 */
public class LocationPullService extends Service {

    private final String TAG = getClass().getName();
    private IBinder mBinder = new LocalBinder();

    private static final boolean INFINITY_LOOP = true;

    private static final ExecutorService EXECUTOR_SERVICE
            = Executors.newCachedThreadPool();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        GrabMConstant.LocationService.mRunning = false;
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {

        if (!GrabMConstant.LocationService.mRunning) {
            final SharedPreferences mSharedPreferences
                    = getSharedPreferences(GrabMConstant.FILE_KEY, MODE_PRIVATE);
            EXECUTOR_SERVICE.execute(new Runnable() {
                @Override
                public void run() {
                    while (INFINITY_LOOP) {
                        try {
                            if (NetConnectionStatus.isConnected(LocationPullService.this)) {
                                GrabTaskHandler.executeSingleThread(mLocationPullTask);
                            }
                            Thread.sleep(GrabMConstant.LOCATION_UPDATE_INTERVAL);
                        } catch (InterruptedException ex) {
                            Log.e(TAG, ex.toString());
                        }
                    }
                }
            });
        }

        GrabMConstant.LocationService.mRunning = true;
        return START_STICKY;
    }

    public class LocalBinder extends Binder {
        @SuppressWarnings("unused")
        LocalBinder getService() {
            return LocalBinder.this;
        }
    }

    private final Runnable mLocationPullTask = new Runnable() {
        @Override
        public void run() {
            GrabAsyncTask.RequestCarLocationData requestScheduleData
                    = new GrabAsyncTask.RequestCarLocationData(LocationPullService.this);
            requestScheduleData.execute("sample_id");
        }
    };
}
