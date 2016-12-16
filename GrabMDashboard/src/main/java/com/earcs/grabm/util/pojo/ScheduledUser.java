package com.earcs.grabm.util.pojo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Roshin Perera
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ScheduledUser implements Serializable {

    private static final long serialVersionUID = 5224472122886287069L;

    private long id;

    private int pickupIndex;

    private double pickupLatitude;

    private double pickupLongitude;

    private double dropoffLatitude;

    private double dropoffLongitude;
    
    private String vehicleGroup;

    public ScheduledUser() {
        /**
         * NOTHING GOES HERE.
         */
    }

    public ScheduledUser(long id) {
        this.id = id;
    }

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
     * @return the pickupIndex
     */
    public int getPickupIndex() {
        return pickupIndex;
    }

    /**
     * @param pickupIndex the pickupIndex to set
     */
    public void setPickupIndex(int pickupIndex) {
        this.pickupIndex = pickupIndex;
    }

    /**
     * @return the pickupLatitude
     */
    public double getPickupLatitude() {
        return pickupLatitude;
    }

    /**
     * @param pickupLatitude the pickupLatitude to set
     */
    public void setPickupLatitude(double pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    /**
     * @return the pickupLongitude
     */
    public double getPickupLongitude() {
        return pickupLongitude;
    }

    /**
     * @param pickupLongitude the pickupLongitude to set
     */
    public void setPickupLongitude(double pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    /**
     * @return the dropoffLatitude
     */
    public double getDropoffLatitude() {
        return dropoffLatitude;
    }

    /**
     * @param dropoffLatitude the dropoffLatitude to set
     */
    public void setDropoffLatitude(double dropoffLatitude) {
        this.dropoffLatitude = dropoffLatitude;
    }

    /**
     * @return the dropoffLongitude
     */
    public double getDropoffLongitude() {
        return dropoffLongitude;
    }

    /**
     * @param dropoffLongitude the dropoffLongitude to set
     */
    public void setDropoffLongitude(double dropoffLongitude) {
        this.dropoffLongitude = dropoffLongitude;
    }

    /**
     * @return the vehicleGroup
     */
    public String getVehicleGroup() {
        return vehicleGroup;
    }

    /**
     * @param vehicleGroup the vehicleGroup to set
     */
    public void setVehicleGroup(String vehicleGroup) {
        this.vehicleGroup = vehicleGroup;
    }
}
