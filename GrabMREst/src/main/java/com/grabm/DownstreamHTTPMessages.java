package com.grabm;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.validation.constraints.NotNull;

/**
 * This interface represents the Google Cloud Messaging API downstream HTTP
 * message templates and it contains both Payload (Data) and Notification
 * message formats.
 *
 * These classes are specially designed for the GCM API client messaging and its
 * structure may depend on the GCM API documentation guidelines.
 *
 * @author Sachith Dickwella
 * @version 1.0
 *
 * @since 1.0
 */
public interface DownstreamHTTPMessages {

    public enum Priorities {
        HIGH, NORMAL
    }

    public class Payload implements DownstreamHTTPMessages {

        private static final String TO = "to";
        private static final String REGISTRATION_IDS = "registration_ids";
        private static final String COLLAPSE_KEY = "collapse_key";
        private static final String PRIORITY = "priority";
        private static final String CONTENT_AVAILABLE = "content_available";
        private static final String DELAY_WHILE_IDLE = "delay_while_idle";
        private static final String TIME_TO_LIVE = "time_to_live";
        private static final String RESTRICTED_PACKAGE_NAME = "restricted_package_name";
        private static final String DRY_RUN = "dry_run";
        private static final String DATA = "data";
        private static final String NOTIFICATION = "notification";

        /**
         * This parameter specifies the recipient of a message.
         *
         * The value must be a registration token, notification key, or topic.
         */
        @NotNull
        private String to;
        /**
         * This parameter specifies a list of devices (registration tokens, or
         * IDs) receiving a multicast message. It must contain at least 1 and at
         * most 1000 registration tokens.
         *
         * Use this parameter only for multicast messaging, not for single
         * recipients. Multicast messages (sending to more than 1 registration
         * tokens) are allowed using HTTP JSON format only.
         */
        private String[] registrationIds;
        /**
         * This parameter identifies a group of messages (e.g., with
         * collapse_key: "Updates Available") that can be collapsed, so that
         * only the last message gets sent when delivery can be resumed. This is
         * intended to avoid sending too many of the same messages when the
         * device comes back online or becomes active (see delay_while_idle).
         *
         * Note that there is no guarantee of the order in which messages get
         * sent.
         *
         * Note: A maximum of 4 different collapse keys is allowed at any given
         * time. This means a GCM connection server can simultaneously store 4
         * different send-to-sync messages per client app. If you exceed this
         * number, there is no guarantee which 4 collapse keys the GCM
         * connection server will keep.
         */
        private String collapseKey;
        /**
         * Sets the priority of the message. Valid values are "normal" and
         * "high." On iOS, these correspond to APNs priority 5 and 10.
         *
         * By default, messages are sent with normal priority. Normal priority
         * optimizes the client app's battery consumption, and should be used
         * unless immediate delivery is required. For messages with normal
         * priority, the app may receive the message with unspecified delay.
         *
         * When a message is sent with high priority, it is sent immediately,
         * and the app can wake a sleeping device and open a network connection
         * to your server.
         *
         * Values should be: High or Normal
         */
        @NotNull
        private String priority;
        /**
         * On iOS, use this field to represent content-available in the APNS
         * payload. When a notification or message is sent and this is set to
         * true, an inactive client app is awoken. On Android, data messages
         * wake the app by default. On Chrome, currently not supported.
         */
        private boolean contentAvailable;
        /**
         * When this parameter is set to true, it indicates that the message
         * should not be sent until the device becomes active. The default value
         * is false.
         */
        private boolean delayWhileIdle;
        /**
         * This parameter specifies how long (in seconds) the message should be
         * kept in GCM storage if the device is offline. The maximum time to
         * live supported is 4 weeks, and the default value is 4 weeks
         */
        private long timeToLive;
        /**
         * This parameter specifies the package name of the application where
         * the registration tokens must match in order to receive the message.
         */
        private String restrictedPackageName;
        /**
         * This parameter, when set to true, allows developers to test a request
         * without actually sending a message.
         *
         * The default value is false.
         */
        private boolean dryRun;
        /**
         * This parameter specifies the custom key-value pairs of the message's
         * payload.
         *
         * For example, with data:{"score":"3x1"}:
         *
         * On Android, this would result in an intent extra named score with the
         * string value 3x1.
         *
         * On iOS, if the message is sent via APNS, it represents the custom
         * data fields. If it is sent via GCM connection server, it would be
         * represented as key value dictionary in AppDelegate
         * application:didReceiveRemoteNotification:.
         *
         * The key should not be a reserved word ("from" or any word starting
         * with "google" or "gcm"). Do not use any of the words defined in this
         * table (such as collapse_key).
         *
         * Values in string types are recommended. You have to convert values in
         * objects or other non-string data types (e.g., integers or booleans)
         * to string.
         */
        @NotNull
        private Map<String, String> data;
        /**
         * This parameter specifies the predefined, user-visible key-value pairs
         * of the notification payload. See Notification payload support for
         * detail.
         */
        private Notification notification;

        public JsonObject toJson() {
            final JsonObjectBuilder dataObjBuilder = Json.createObjectBuilder();
            {
                final Set<String> keys = data.keySet();

                final Iterator<String> keysIterator = keys.iterator();
                while (keysIterator.hasNext()) {
                    final String key = keysIterator.next();
                    dataObjBuilder.add(key, data.get(key));
                }
            }

            final JsonObjectBuilder builder = Json.createObjectBuilder()
                    .add(TO, to)
                    .add(PRIORITY, priority)
                    .add(DATA, dataObjBuilder);

            if (notification != null) {
                contentAvailable = true;
                builder.add(NOTIFICATION, notification.toJson())
                        .add(CONTENT_AVAILABLE, contentAvailable);
            }
            if (registrationIds != null
                    && registrationIds.length > 0) {
                final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                for (String id : registrationIds) {
                    arrayBuilder.add(id);
                }
                builder.add(REGISTRATION_IDS, arrayBuilder);
            }
            if (collapseKey != null
                    && !collapseKey.equals("")) {
                builder.add(COLLAPSE_KEY, collapseKey);
            }
            if (timeToLive > 0) {
                builder.add(TIME_TO_LIVE, timeToLive);
            }
            if (restrictedPackageName != null
                    && !restrictedPackageName.equals("")) {
                builder.add(RESTRICTED_PACKAGE_NAME, restrictedPackageName);
            }
            if (dryRun) {
                builder.add(DRY_RUN, dryRun);
            }

            builder.add(DELAY_WHILE_IDLE, delayWhileIdle);

            return builder.build();
        }

        /**
         * @param to the to to set
         * @return this
         */
        public Payload setTo(String to) {
            this.to = to;
            return this;
        }

        /**
         * @param registrationIds the registrationIds to set
         * @return this
         */
        public Payload setRegistrationIds(String[] registrationIds) {
            this.registrationIds = registrationIds;
            return this;
        }

        /**
         * @param collapseKey the collapseKey to set
         * @return this
         */
        public Payload setCollapseKey(String collapseKey) {
            this.collapseKey = collapseKey;
            return this;
        }

        /**
         * @param priority the priority to set
         * @return this
         */
        public Payload setPriority(String priority) {
            this.priority = priority;
            return this;
        }

        /**
         * @param delayWhileIdle the delayWhileIdle to set
         * @return this
         */
        public Payload setDelayWhileIdle(boolean delayWhileIdle) {
            this.delayWhileIdle = delayWhileIdle;
            return this;
        }

        /**
         * @param timeToLive the timeToLive to set
         * @return this
         */
        public Payload setTimeToLive(long timeToLive) {
            this.timeToLive = timeToLive;
            return this;
        }

        /**
         * @param restrictedPackageName the restrictedPackageName to set
         * @return this
         */
        public Payload setRestrictedPackageName(String restrictedPackageName) {
            this.restrictedPackageName = restrictedPackageName;
            return this;
        }

        /**
         * @param dryRun the dryRun to set
         * @return this
         */
        public Payload setDryRun(boolean dryRun) {
            this.dryRun = dryRun;
            return this;
        }

        /**
         * @param data the data to set
         * @return this
         */
        public Payload setData(Map<String, String> data) {
            this.data = data;
            return this;
        }

        /**
         * @param notification the notification to set
         * @return this
         */
        public Payload setNotification(Notification notification) {
            this.notification = notification;
            return this;
        }
    }

    public class Notification implements DownstreamHTTPMessages {

        private static final String TITLE = "title";
        private static final String BODY = "body";
        private static final String ICON = "icon";
        private static final String SOUND = "sound";
        private static final String BADGE = "badge";
        private static final String TAG = "tag";
        private static final String COLOR = "color";
        private static final String CLICK_ACTION = "click_action";
        private static final String BODY_LOCK_KEY = "body_loc_key";
        private static final String BODY_LOCK_ARGS = "body_loc_args";
        private static final String TITLE_LOCK_KEY = "title_loc_key";
        private static final String TITLE_LOCK_ARGS = "title_loc_args";

        /**
         * Indicates notification title. This field is not visible on iOS phones
         * and tablets.
         */
        private String title;
        /**
         * Indicates notification body text.
         */
        private String body;
        /**
         * Indicates notification icon. On Android: sets value to myicon for
         * drawable resource myicon.
         */
        private String icon;
        /**
         * Indicates a sound to play when the device receives the notification.
         * Supports default, or the filename of a sound resource bundled in the
         * app.
         *
         * Android sound files must reside in /res/raw/, while iOS sound files
         * can be in the main bundle of the client app or in the Library/Sounds
         * folder of the app’s data container. See the iOS Developer Library for
         * more information.
         */
        private String sound;
        /**
         * Indicates the badge on client app home icon.
         */
        private String badge;
        /**
         * Indicates whether each notification message results in a new entry on
         * the notification center on Android. If not set, each request creates
         * a new notification. If set, and a notification with the same tag is
         * already being shown, the new notification replaces the existing one
         * in notification center.
         */
        private String tag;
        /**
         * Indicates color of the icon, expressed in #rrggbb format
         */
        private String color;
        /**
         * The action associated with a user click on the notification.
         *
         * On Android, if this is set, an activity with a matching intent filter
         * is launched when user clicks the notification.
         *
         * If set on iOS, corresponds to category in APNS payload.
         */
        private String clickAction;
        /**
         * Indicates the key to the body string for localization.
         *
         * On iOS, this corresponds to "loc-key" in APNS payload.
         *
         * On Android, use the key in the app's string resources when populating
         * this value.
         */
        private String bodyLockKey;
        /**
         * Indicates the string value to replace format specifiers in body
         * string for localization.
         *
         * On iOS, this corresponds to "loc-args" in APNS payload.
         *
         * On Android, these are the format arguments for the string resource.
         * For more information, see Formatting strings.
         */
        private String bodyLockArgs;
        /**
         * Indicates the key to the title string for localization.
         *
         * On iOS, this corresponds to "title-loc-key" in APNS payload.
         *
         * On Android, use the key in the app's string resources when populating
         * this value.
         */
        private String titleLockKey;
        /**
         * Indicates the string value to replace format specifiers in title
         * string for localization.
         *
         * On iOS, this corresponds to "title-loc-args" in APNS payload.
         *
         * On Android, these are the format arguments for the string resource.
         * For more information, see Formatting strings.
         */
        private String titleLockArgs;

        public JsonObject toJson() {
            final JsonObjectBuilder builder = Json.createObjectBuilder();
            return builder.build();
        }
    }
}
