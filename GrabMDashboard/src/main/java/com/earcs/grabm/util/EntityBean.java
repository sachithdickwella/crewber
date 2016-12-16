package com.earcs.grabm.util;

/**
 *
 * @author Roshin Perera
 */
public interface EntityBean {

    /**
     * Statuses of entities.
     */
    public enum Status {

        ACTIVE, INACTIVE, BLOCK, DISABLED, FIRST_LOGIN,
        SCHEDULED, DISPATCHED, NEXT_PICKUP, ARRIVED;

        @Override
        public String toString() {
            switch (this) {
                case ACTIVE:
                    return "A";
                case INACTIVE:
                    return "I";
                case BLOCK:
                    return "B";
                case DISABLED:
                    return "D";
                case FIRST_LOGIN:
                    return "F";
                case SCHEDULED:
                    return "S";
                case DISPATCHED:
                    return "P";
                case NEXT_PICKUP:
                    return "N";
                case ARRIVED:
                    return "V";
                default:
                    return "";
            }
        }
    }

    /**
     * Mobile application subscription status.
     */
    public enum SubscriptionStatus {

        /**
         * Not subscribed user yet.
         */
        NO_SUBSCRIPTION, 
        /**
         * Usual subscribed user.
         */
        GENERAL_SUBSCRIBER, 
        /**
         * Halt subscription due to payment issue.
         */
        HOLD_PAYMENT_SUBSCRIPTION,
        /**
         * Halt subscription due to other issue.
         */
        HOLD_OTHER_SUBSCRIPTION, 
        /**
         * Un-subscribed user.
         */
        UNSUBSCRIBED;

        @Override
        public String toString() {
            switch (this) {
                case NO_SUBSCRIPTION:
                    return "0";
                case GENERAL_SUBSCRIBER:
                    return "1";
                case HOLD_PAYMENT_SUBSCRIPTION:
                    return "2";
                case HOLD_OTHER_SUBSCRIPTION:
                    return "3";
                case UNSUBSCRIBED:
                    return "4";
                default:
                    return "";
            }
        }
    }

    /**
     * Location change and update status.
     */
    public enum LocationStatus {
        /**
         * GPS locations has not fed yet.
         */
        NO_GPS_LOCATION,
        /**
         * User has entered GPS location and waiting for approval by transport
         * provider.
         */
        WAITING_APPROVAL_GPS_LOCATION,
        /**
         * User has an approved GPS location (use current pickup location
         * coordinates).
         */
        APPROVED_GPS_LOCATION,
        /**
         * User has requested GPS location update and waiting for approval by
         * transport provider (use Previous pickup location coordinates).
         */
        NEW_REQUESTED_GPS_LOCATION,
        /**
         * User has requested New Address|GPS location update and waiting for
         * approval by transport provider (use Previous pickup location
         * coordinates).
         */
        WAITING_APPROVAL_UPDATED_GPS_LOCATION,
        /**
         * Transport provider has denied GPS location for First Location
         * Request.
         */
        REJECTED_FIRST_GPS_LOCATION,
        /**
         * Transport provider has denied GPS location for Location update
         * Request (use Previous pickup location coordinates).
         */
        REJECTED_UPDATE_GPS_LOCATION,
        /**
         * Transport provider has denied GPS location for Address Change Request
         * (use Previous pickup location coordinates).
         */
        REJECTED_ADDRESS_CHANGE_REQUEST;

        @Override
        public String toString() {
            switch (this) {
                case NO_GPS_LOCATION:
                    return "0";
                case WAITING_APPROVAL_GPS_LOCATION:
                    return "1";
                case APPROVED_GPS_LOCATION:
                    return "2";
                case NEW_REQUESTED_GPS_LOCATION:
                    return "3";
                case WAITING_APPROVAL_UPDATED_GPS_LOCATION:
                    return "4";
                case REJECTED_FIRST_GPS_LOCATION:
                    return "5";
                case REJECTED_UPDATE_GPS_LOCATION:
                    return "6";
                case REJECTED_ADDRESS_CHANGE_REQUEST:
                    return "7";
                default:
                    return "";
            }
        }
    }
}
