package com.grabm.entity.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sachith Dickwella
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientTrip implements POJOMarker {

    private static final long serialVersionUID = 6917192794238651606L;
    
    private long pickupIndex;
    
    private String driverFisrtName;
    
    private String driverLastName;
    
    private String driverMobileNumber;
    
    private double homeLatitude;
    
    private double homeLongitude;
    
    private double vehicleLatitude;
    
    private double vehicleLongitude;
    
    private GoogleDistanceMatrixInfo googleDistanceMatrixInfo;

    /**
     * @return the pickupIndex
     */
    public long getPickupIndex() {
        return pickupIndex;
    }

    /**
     * @param pickupIndex the pickupIndex to set
     */
    public void setPickupIndex(long pickupIndex) {
        this.pickupIndex = pickupIndex;
    }

    /**
     * @return the driverFisrtName
     */
    public String getDriverFisrtName() {
        return driverFisrtName;
    }

    /**
     * @param driverFisrtName the driverFisrtName to set
     */
    public void setDriverFisrtName(String driverFisrtName) {
        this.driverFisrtName = driverFisrtName;
    }

    /**
     * @return the driverLastName
     */
    public String getDriverLastName() {
        return driverLastName;
    }

    /**
     * @param driverLastName the driverLastName to set
     */
    public void setDriverLastName(String driverLastName) {
        this.driverLastName = driverLastName;
    }

    /**
     * @return the driverMobileNumber
     */
    public String getDriverMobileNumber() {
        return driverMobileNumber;
    }

    /**
     * @param driverMobileNumber the driverMobileNumber to set
     */
    public void setDriverMobileNumber(String driverMobileNumber) {
        this.driverMobileNumber = driverMobileNumber;
    }

    /**
     * @return the homeLatitude
     */
    public double getHomeLatitude() {
        return homeLatitude;
    }

    /**
     * @param homeLatitude the homeLatitude to set
     */
    public void setHomeLatitude(double homeLatitude) {
        this.homeLatitude = homeLatitude;
    }

    /**
     * @return the homeLongitude
     */
    public double getHomeLongitude() {
        return homeLongitude;
    }

    /**
     * @param homeLongitude the homeLongitude to set
     */
    public void setHomeLongitude(double homeLongitude) {
        this.homeLongitude = homeLongitude;
    }

    /**
     * @return the googleDistanceMatrixInfo
     */
    public GoogleDistanceMatrixInfo getGoogleDistanceMatrixInfo() {
        return googleDistanceMatrixInfo;
    }

    /**
     * @param googleDistanceMatrixInfo the googleDistanceMatrixInfo to set
     */
    public void setGoogleDistanceMatrixInfo(GoogleDistanceMatrixInfo googleDistanceMatrixInfo) {
        this.googleDistanceMatrixInfo = googleDistanceMatrixInfo;
    }

    /**
     * @return the vehicleLatitude
     */
    public double getVehicleLatitude() {
        return vehicleLatitude;
    }

    /**
     * @param vehicleLatitude the vehicleLatitude to set
     */
    public void setVehicleLatitude(double vehicleLatitude) {
        this.vehicleLatitude = vehicleLatitude;
    }

    /**
     * @return the vehicleLongitude
     */
    public double getVehicleLongitude() {
        return vehicleLongitude;
    }

    /**
     * @param vehicleLongitude the vehicleLongitude to set
     */
    public void setVehicleLongitude(double vehicleLongitude) {
        this.vehicleLongitude = vehicleLongitude;
    }
    
}
