package com.earcs.grabm.util;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.earcs.grabm.util.message.LocationPullService;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sachith Dickwella
 */
public interface GrabMConstant {

    int LOCATION_UPDATE_INTERVAL = 15000;

    int AUTO_HIDE_DELAY_MILLIS = 3000;
    int UI_ANIMATION_DELAY = 300;
    int AUTO_HIDE_INIT_DELAY_MILLIS = 100;

    Handler mHideHandler = new Handler();
    /**
     * Google cloud messaging sender id.
     */
    String GOOGLE_MESSAGING_SENDER_ID = "";
    /**
     * Server transaction drop down notifier constants.
     */
    int SERVER_ERROR = -1;
    int EXECUTION_ERROR = -2;

    String FILE_KEY = "com.earcs.grabm.SHARED_PREFERENCE_FILE_KEY";

    String KEY_MEMBER_NO = "KEY_MEMBER_NO";
    String KEY_NAME = "KEY_NAME";
    String KEY_EMAIL = "KEY_EMAIL";
    String KEY_LOGIN_STATUS = "KEY_LOGIN_STATUS";
    String KEY_LOGIN_BY = "KEY_LOGIN_BY";

    @SuppressWarnings("unused")
    enum LoginStatus {
        LOGIN, LOGOUT
    }

    enum LoginMethod {BY_GOOGLE, BY_TWITTER}

    int INTERVAL = 3000;
    int FASTEST_INTERVAL = 500;

    class QuickstartPreferences {

        public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
        public static final String REGISTRATION_COMPLETE = "registrationComplete";
    }

    /**
     * Twitter oauth related constants.
     */
    String TWITTER_CONSUMER_KEY = "";
    String TWITTER_CONSUMER_SECRET = "";

    /**
     * Notification ID
     */
    int MESSAGE_NOTIFICATION_ID = 0x3e8;

    enum NotificationType {
        ASSIGNED, DISPATCHED, PICKUP, ARRIVED;

        @Override
        public String toString() {
            return name();
        }
    }

    abstract class LocationService {
        private static Intent LOCATION_PULL_SERVICE_INTENT;
        public static boolean mRunning = false;

        public static synchronized Intent getIntent(Context context) {
            if (LOCATION_PULL_SERVICE_INTENT == null) {
                LOCATION_PULL_SERVICE_INTENT = new Intent(context, LocationPullService.class);
            }
            return LOCATION_PULL_SERVICE_INTENT;
        }
    }

    Map<String, LatLng> MAP_LATLANG = new HashMap<>();

    String MY_LOCATION = "myLocation";
    String VEHICLE_LOCATION = "vehicleLocation";


    abstract class LoginMethodByte {
        public static boolean isGoogle = true;
    }

    enum NotificationContent {

        TYPE, TITLE, LONG_MESSAGE
    }
}
