package com.earcs.grabm.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Sachith Dickwella
 */
public class DropoffSchedule implements Serializable {

    private static final long serialVersionUID = -3989110729161794089L;

    private long id;

    private Date pickupDatetime;

    private long pti;

    private String note;

    private long rescheduledTurn;

    private String pickupPoint;

    private String dropoffPoint;

    private Character status;

    private long indexId;

    private long createUser;

    private Date createDatetime;

    private long lastupdateUser;

    private Date lastupdateDatetime;

    private String lastsourceIp;

    private Dropoff dropoffId;

    private VehicleDriver vehicleDriver;

    private EndUser enduserId;

    public DropoffSchedule() {
        /**
         * Nothing goes here.
         */
    }

    public DropoffSchedule(long id) {
        this.id = id;
    }

    public DropoffSchedule(long id, Date pickupDatetime, Character status,
            long createUser, Date createDatetime, long lastupdateUser, Date lastupdateDatetime) {
        this.id = id;
        this.pickupDatetime = pickupDatetime;
        this.status = status;
        this.createUser = createUser;
        this.createDatetime = createDatetime;
        this.lastupdateUser = lastupdateUser;
        this.lastupdateDatetime = lastupdateDatetime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPickupDatetime() {
        return pickupDatetime;
    }

    public void setPickupDatetime(Date pickupDatetime) {
        this.pickupDatetime = pickupDatetime;
    }

    public long getPti() {
        return pti;
    }

    public void setPti(long pti) {
        this.pti = pti;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getRescheduledTurn() {
        return rescheduledTurn;
    }

    public void setRescheduledTurn(long rescheduledTurn) {
        this.rescheduledTurn = rescheduledTurn;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }

    public void setPickupPoint(String pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public String getDropoffPoint() {
        return dropoffPoint;
    }

    public void setDropoffPoint(String dropoffPoint) {
        this.dropoffPoint = dropoffPoint;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public long getIndexId() {
        return indexId;
    }

    public void setIndexId(long indexId) {
        this.indexId = indexId;
    }

    public long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public long getLastupdateUser() {
        return lastupdateUser;
    }

    public void setLastupdateUser(long lastupdateUser) {
        this.lastupdateUser = lastupdateUser;
    }

    public Date getLastupdateDatetime() {
        return lastupdateDatetime;
    }

    public void setLastupdateDatetime(Date lastupdateDatetime) {
        this.lastupdateDatetime = lastupdateDatetime;
    }

    public String getLastsourceIp() {
        return lastsourceIp;
    }

    public void setLastsourceIp(String lastsourceIp) {
        this.lastsourceIp = lastsourceIp;
    }

    public Dropoff getDropoffId() {
        return dropoffId;
    }

    public void setDropoffId(Dropoff dropoffId) {
        this.dropoffId = dropoffId;
    }

    public VehicleDriver getVehicleDriver() {
        return vehicleDriver;
    }

    public void setVehicleDriver(VehicleDriver vehicleDriver) {
        this.vehicleDriver = vehicleDriver;
    }

    public EndUser getEnduserId() {
        return enduserId;
    }

    public void setEnduserId(EndUser enduserId) {
        this.enduserId = enduserId;
    }
}
