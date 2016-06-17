package com.earcs.grabm.entity;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Sachith Dickwella
 */
public class GoogleDistanceMatrixInfo {

    private String destination;

    private String origin;

    private String distance;

    private String duration;

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @return the origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * @return the distance
     */
    public String getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public static GoogleDistanceMatrixInfo fromJson(String json) {
        try {
            if (json != null) {
                JSONObject jsonObject = new JSONObject(json);
                GoogleDistanceMatrixInfo info = new GoogleDistanceMatrixInfo();
                info.setOrigin(jsonObject.getString("origin"));
                info.setDestination(jsonObject.getString("destination"));
                info.setDistance(jsonObject.getString("distance"));
                info.setDuration(jsonObject.getString("duration"));

                return info;
            }
        } catch (JSONException ex) {
            Log.e(ex.getMessage(), ex.toString());
        }
        return null;
    }
}
