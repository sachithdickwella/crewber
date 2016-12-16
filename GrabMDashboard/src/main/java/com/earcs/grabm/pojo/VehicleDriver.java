package com.earcs.grabm.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sachith Dickwella
 */
public class VehicleDriver implements Serializable {

    private static final long serialVersionUID = -8841872493248287944L;

    private long id;
    
    private char status;

    private String note;
    
    private long createUser;

    private Date createDateTime;

    private long lastupdateUser;

    private Date lastupdateDateTime;

    private String lastsourceIp;

    private List<PickupSchedule> pickupScheduleList;

    private List<DropoffReSchedule> dropoffReScheduleList;

    private List<DropoffSchedule> dropoffScheduleList;

    private List<PickupReSchedule> pickupReScheduleList;

    private Driver driver;

    private Vehicle vehicle;

    public VehicleDriver() {
        /**
         * Nothing goes here.
         */
    }
    
    public VehicleDriver(long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<PickupSchedule> getPickupScheduleList() {
        return pickupScheduleList;
    }

    public void setPickupScheduleList(List<PickupSchedule> pickupScheduleList) {
        this.pickupScheduleList = pickupScheduleList;
    }

    public List<DropoffReSchedule> getDropoffReScheduleList() {
        return dropoffReScheduleList;
    }

    public void setDropoffReScheduleList(List<DropoffReSchedule> dropoffReScheduleList) {
        this.dropoffReScheduleList = dropoffReScheduleList;
    }

    public List<DropoffSchedule> getDropoffScheduleList() {
        return dropoffScheduleList;
    }

    public void setDropoffScheduleList(List<DropoffSchedule> dropoffScheduleList) {
        this.dropoffScheduleList = dropoffScheduleList;
    }

    public List<PickupReSchedule> getPickupReScheduleList() {
        return pickupReScheduleList;
    }

    public void setPickupReScheduleList(List<PickupReSchedule> pickupReScheduleList) {
        this.pickupReScheduleList = pickupReScheduleList;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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
     * @return the status
     */
    public char getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(char status) {
        this.status = status;
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
     * @return the lastupdateUser
     */
    public long getLastupdateUser() {
        return lastupdateUser;
    }

    /**
     * @param lastupdateUser the lastupdateUser to set
     */
    public void setLastupdateUser(long lastupdateUser) {
        this.lastupdateUser = lastupdateUser;
    }

    /**
     * @return the lastupdateDateTime
     */
    public Date getLastupdateDateTime() {
        return lastupdateDateTime;
    }

    /**
     * @param lastupdateDateTime the lastupdateDateTime to set
     */
    public void setLastupdateDateTime(Date lastupdateDateTime) {
        this.lastupdateDateTime = lastupdateDateTime;
    }

    /**
     * @return the lastsourceIp
     */
    public String getLastsourceIp() {
        return lastsourceIp;
    }

    /**
     * @param lastsourceIp the lastsourceIp to set
     */
    public void setLastsourceIp(String lastsourceIp) {
        this.lastsourceIp = lastsourceIp;
    }
}
