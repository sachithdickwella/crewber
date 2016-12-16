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
public class Pickup implements Serializable {

    private static final long serialVersionUID = -3756772589166926940L;

    private long id;

    private String flightNo;

    private Date flightDateTime;

    private String note;

    private long createUser;

    private Date createDatetime;

    private long lastupdateUser;

    private Date lastupdateDatetime;

    private String lastusourceIp;
    
    private MobilePickupSummery mobilePickupSummery;

    @XmlTransient
    private List<PickupSchedule> pickupScheduleList;

    @XmlTransient
    private List<PickupReSchedule> pickupReScheduleList;

    public Pickup() {
        /**
         * Nothing goes here.
         */
    }

    public Pickup(long id) {
        this.id = id;
    }

    public Pickup(long id, String flightNo, Date flightDateTime, String note, long createUser,
            Date createDatetime, long lastupdateUser, Date lastupdateDatetime) {
        this.id = id;
        this.flightNo = flightNo;
        this.flightDateTime = flightDateTime;
        this.note = note;
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

    public String getLastusourceIp() {
        return lastusourceIp;
    }

    public void setLastusourceIp(String lastusourceIp) {
        this.lastusourceIp = lastusourceIp;
    }

    public List<PickupSchedule> getPickupScheduleList() {
        return pickupScheduleList;
    }

    public void setPickupScheduleList(List<PickupSchedule> pickupScheduleList) {
        this.pickupScheduleList = pickupScheduleList;
    }

    public List<PickupReSchedule> getPickupReScheduleList() {
        return pickupReScheduleList;
    }

    public void setPickupReScheduleList(List<PickupReSchedule> pickupReScheduleList) {
        this.pickupReScheduleList = pickupReScheduleList;
    }

    /**
     * @return the flightNo
     */
    public String getFlightNo() {
        return flightNo;
    }

    /**
     * @param flightNo the flightNo to set
     */
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    /**
     * @return the flightDateTime
     */
    public Date getFlightDateTime() {
        return flightDateTime;
    }

    /**
     * @param flightDateTime the flightDateTime to set
     */
    public void setFlightDateTime(Date flightDateTime) {
        this.flightDateTime = flightDateTime;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the mobilePickupSummery
     */
    public MobilePickupSummery getMobilePickupSummery() {
        return mobilePickupSummery;
    }

    /**
     * @param mobilePickupSummery the mobilePickupSummery to set
     */
    public void setMobilePickupSummery(MobilePickupSummery mobilePickupSummery) {
        this.mobilePickupSummery = mobilePickupSummery;
    }
}
