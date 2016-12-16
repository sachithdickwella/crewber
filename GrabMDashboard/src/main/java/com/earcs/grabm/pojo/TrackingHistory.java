package com.earcs.grabm.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sachith Dickwella
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TrackingHistory implements Serializable {
    
    private static final long serialVersionUID = -3727807185212208424L;
    
    private long id;
    
    private String imie;
    
    private String alarm;
    
    private Date trackerDateTime;
    
    private boolean validity;
    
    private double latitude;
    
    private double longitude;
    
    private double speed;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

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
}
