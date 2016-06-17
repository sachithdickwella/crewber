package com.grabm.util;

import com.grabm.entity.LatestTracking;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Sachith Dickwella
 */
public interface GrabMConstant {

    /**
     * Tracking server thread.
     */
    public static final ExecutorService TRACKER_SERVICE = Executors.newCachedThreadPool();
    /**
     * Ongoing tracking list.
     */
    public static final Map<String, LatestTracking> ONGOING_TRACKINGS = new HashMap<>();

    /**
     * Keep the hosting application server running status.
     */
    public static abstract class ServerStatus {

        public static boolean isRunning = false;
    }

    /**
     * Authentication bundle access interface.
     */
    public static interface AuthBundle {

        /**
         * The authentication.properties file key codes.
         *
         * GrabM technical team email addresses resource key.
         */
        public static final String AUTH_DESTINATIONS = "auth_destinations";
        /**
         * Authentication private-key for GarbM rest API
         */
        public static final String PRIVATE_KEY = "private_key";
        /**
         * Resource bundle for authentication.properties file.
         */
        public static final ResourceBundle BUNDLE = ResourceBundle.getBundle("bundle/authentication", Locale.US);
    }

    /**
     * Entity CRUD operation failing messages.
     */
    public static final String ENTITY_EXCEPTION_CREATE = "Create Failed";
    public static final String ENTITY_EXCEPTION_BULKCREATE = "Bulk Create Failed";
    public static final String ENTITY_EXCEPTION_UPDATE = "Update Failed";
    public static final String ENTITY_EXCEPTION_FETCH = "Fetching Failed";
    public static final String ENTITY_EXCEPTION_DELETE = "Delete Failed";
    public static final String ENTITY_EXCEPTION_SAVE_OR_UPDATE = "Delete Failed";
    public static final String ENTITY_EXCEPTION_VALIDATE = "Validation Failed";
    
    public static final String EMAIL_GENERATION_FAILED = "Email genration failed";

    public static final String EXCEPTION_JERSEY = "Jersey server error";

    public static final String EXCEPTION_TRACKER = "Tracker server error";

    public abstract class GoogleCloudMessagingClient {

        /**
         * Google cloud messaging API private key.
         */
        protected static final String GOOGLE_MESSEAGING_API_KEY = "";
        /**
         * Google Cloud Messaging API.
         */
        protected static final String GOOGLE_MESSAGING_API = "https://gcm-http.googleapis.com/gcm/send";
    }

    public abstract class GoogleMapDistanceMatrixClient {

        /**
         * Google Map Distance Matrix API.
         */
        protected static final String GOOGLE_MAP_DISTANCE_MATRIX_API = "https://maps.googleapis.com/maps/api/distancematrix";
        /**
         * Google Map Distance Matrix API Server Key.
         */
        public static final String GOOGLE_MAP_DISTANCE_MATRIX_SERVER_KEY = "";

        /**
         * Google Direction API output types.
         */
        public enum Output {
            JSON, XML
        }
    }

    /**
     * Attribute names.
     *
     * Online trackers latest details.
     */
    public static final String ONLINE_TRACKER_LATEST = "online_tracker_latest";

    public final String CONTEXT_DUMMY_DATA = "dummy_coordinates";
}
