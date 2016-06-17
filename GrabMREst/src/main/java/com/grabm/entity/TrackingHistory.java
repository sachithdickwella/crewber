package com.grabm.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sachith Dickwella
 */
@Entity
@Table(name = "trackinghistory")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TrackingHistory implements Serializable {
    
    private static final long serialVersionUID = -3727807185212208424L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IMIE")
    private String imie;
    
    @Size(max = 25)
    @Column(name = "ALARM")
    private String alarm;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TRACKER_DATETIME")
    private Date trackerDateTime;
    
    @NotNull
    @Column(name = "VALIDITY")
    private boolean validity;
    
    @NotNull
    @Column(name = "LATITUDE")
    private double latitude;
    
    @NotNull
    @Column(name = "LONGITUDE")
    private double longitude;
    
    @NotNull
    @Column(name = "SPEED")
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
