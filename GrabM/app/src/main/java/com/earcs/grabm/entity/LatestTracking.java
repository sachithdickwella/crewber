package com.earcs.grabm.entity;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sachith Dickwella
 */
public class LatestTracking implements Serializable {

    private static final String TAG = LatestTracking.class.getName();

    private static final long serialVersionUID = 8001078998526389718L;

    private String imie;

    private String alarm;

    private Date trackerDateTime;

    private boolean validity;

    private double latitude;

    private double longitude;

    private double speed;

    /**
     * @return the imie
     */
    public String getImie() {
        return imie;
    }

    /**
     * @param imie the imie to set
     */
    public void setImie(String imie) {
        this.imie = imie;
    }

    /**
     * @return the alarm
     */
    public String getAlarm() {
        return alarm;
    }

    /**
     * @param alarm the alarm to set
     */
    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    /**
     * @return the trackerDateTime
     */
    public Date getTrackerDateTime() {
        return trackerDateTime;
    }

    /**
     * @param trackerDateTime the trackerDateTime to set
     */
    public void setTrackerDateTime(Date trackerDateTime) {
        this.trackerDateTime = trackerDateTime;
    }

    /**
     * @return the validity
     */
    public boolean isValidity() {
        return validity;
    }

    /**
     * @param validity the validity to set
     */
    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public static LatestTracking fromJson(String json) {
        try {
            System.err.println(json);
            JSONObject object = new JSONObject(json);
            LatestTracking lt = new LatestTracking();
            lt.setImie(object.getString("imie"));
            lt.setAlarm(object.getString("alarm"));

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date trackerDate = formatter.parse(object.getString("trackerDateTime"));

            lt.setTrackerDateTime(trackerDate);
            lt.setLatitude(object.getDouble("latitude"));
            lt.setLongitude(object.getDouble("longitude"));
            lt.setSpeed(object.getDouble("speed"));
            lt.setValidity(object.getBoolean("validity"));

            return lt;
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return null;
    }
}
