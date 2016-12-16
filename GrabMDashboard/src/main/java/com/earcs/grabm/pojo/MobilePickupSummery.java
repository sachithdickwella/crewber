package com.earcs.grabm.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sachith Dickwella
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MobilePickupSummery implements Serializable {

    private static final long serialVersionUID = 8291843332882440746L;

    private long id;

    private Pickup pickupId;

    private String vehicleRegistrationNumber;

    private String vehicleColor1;

    private String vehicleColor2;

    private String driverFirstName;

    private String driverLastName;

    private byte[] driverPhoto;

    private int rating;

    private Date pickupDateTime;

    private String pickupPoint;

    private String dropoffPoint;

    private char pickupStatus;

    private long createUser;

    private Date createDateTime;

    private long lastUpdateUser;

    private Date lastUpdateDateTime;

    @XmlTransient
    private List<EndUser> endUserList;

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
     * @return the pickupId
     */
    public Pickup getPickupId() {
        return pickupId;
    }

    /**
     * @param pickupId the pickupId to set
     */
    public void setPickupId(Pickup pickupId) {
        this.pickupId = pickupId;
    }

    /**
     * @return the vehicleRegistrationNumber
     */
    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    /**
     * @param vehicleRegistrationNumber the vehicleRegistrationNumber to set
     */
    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    /**
     * @return the vehicleColor1
     */
    public String getVehicleColor1() {
        return vehicleColor1;
    }

    /**
     * @param vehicleColor1 the vehicleColor1 to set
     */
    public void setVehicleColor1(String vehicleColor1) {
        this.vehicleColor1 = vehicleColor1;
    }

    /**
     * @return the vehicleColor2
     */
    public String getVehicleColor2() {
        return vehicleColor2;
    }

    /**
     * @param vehicleColor2 the vehicleColor2 to set
     */
    public void setVehicleColor2(String vehicleColor2) {
        this.vehicleColor2 = vehicleColor2;
    }

    /**
     * @return the driverFirstName
     */
    public String getDriverFirstName() {
        return driverFirstName;
    }

    /**
     * @param driverFirstName the driverFirstName to set
     */
    public void setDriverFirstName(String driverFirstName) {
        this.driverFirstName = driverFirstName;
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
     * @return the driverPhoto
     */
    public byte[] getDriverPhoto() {
        return driverPhoto;
    }

    /**
     * @param driverPhoto the driverPhoto to set
     */
    public void setDriverPhoto(byte[] driverPhoto) {
        this.driverPhoto = driverPhoto;
    }

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return the pickupDateTime
     */
    public Date getPickupDateTime() {
        return pickupDateTime;
    }

    /**
     * @param pickupDateTime the pickupDateTime to set
     */
    public void setPickupDateTime(Date pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    /**
     * @return the pickupPoint
     */
    public String getPickupPoint() {
        return pickupPoint;
    }

    /**
     * @param pickupPoint the pickupPoint to set
     */
    public void setPickupPoint(String pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    /**
     * @return the dropoffPoint
     */
    public String getDropoffPoint() {
        return dropoffPoint;
    }

    /**
     * @param dropoffPoint the dropoffPoint to set
     */
    public void setDropoffPoint(String dropoffPoint) {
        this.dropoffPoint = dropoffPoint;
    }

    /**
     * @return the pickupStatus
     */
    public char getPickupStatus() {
        return pickupStatus;
    }

    /**
     * @param pickupStatus the pickupStatus to set
     */
    public void setPickupStatus(char pickupStatus) {
        this.pickupStatus = pickupStatus;
    }

    /**
     * @return the createUser
     */
    public long getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser the createUser to set
     */
    public void setCreateUser(long createUser) {
        this.createUser = createUser;
    }

    /**
     * @return the createDateTime
     */
    public Date getCreateDateTime() {
        return createDateTime;
    }

    /**
     * @param createDateTime the createDateTime to set
     */
    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    /**
     * @return the lastUpdateUser
     */
    public long getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * @param lastUpdateUser the lastUpdateUser to set
     */
    public void setLastUpdateUser(long lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    /**
     * @return the lastUpdateDateTime
     */
    public Date getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    /**
     * @param lastUpdateDateTime the lastUpdateDateTime to set
     */
    public void setLastUpdateDateTime(Date lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    /**
     * @return the endUserList
     */
    public List<EndUser> getEndUserList() {
        return endUserList;
    }

    /**
     * @param endUserList the endUserList to set
     */
    public void setEndUserList(List<EndUser> endUserList) {
        this.endUserList = endUserList;
    }
}
