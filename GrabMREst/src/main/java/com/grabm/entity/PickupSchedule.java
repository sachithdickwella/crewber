package com.grabm.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "pickupschedule")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PickupSchedule implements Serializable {

    private static final long serialVersionUID = 1432059235583864344L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "PICKUP_INDEX")
    private long pickupIndex;

    @NotNull
    @Column(name = "PICKUP_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pickupDatetime;

    @Column(name = "DISPATCH_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dispatchDateTime;

    @Column(name = "PTI")
    private long pti;

    @Lob
    @Size(max = 65535)
    @Column(name = "NOTE")
    private String note;

    @Column(name = "RESCHEDULED_TURN")
    private long rescheduledTurn;

    @Size(max = 50)
    @Column(name = "PICKUP_POINT")
    private String pickupPoint;

    @Size(max = 50)
    @Column(name = "DROPOFF_POINT")
    private String dropoffPoint;

    @NotNull
    @Column(name = "STATUS")
    private Character status;

    @Column(name = "INDEX_ID")
    private long indexId;

    @NotNull
    @Column(name = "CREATE_USER")
    private long createUser;

    @NotNull
    @Column(name = "CREATE_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDatetime;

    @NotNull
    @Column(name = "LASTUPDATE_USER")
    private long lastupdateUser;

    @NotNull
    @Column(name = "LASTUPDATE_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastupdateDatetime;

    @Size(max = 45)
    @Column(name = "LASTSOURCE_IP")
    private String lastsourceIp;

    @JoinColumn(name = "PICKUP_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pickup pickupId;

    @JoinColumn(name = "VEHICLEDRIVER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private VehicleDriver vehicleDriver;

    @JoinColumn(name = "ENDUSER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EndUser enduserId;

    public PickupSchedule() {
        /**
         * Nothing goes here.
         */
    }

    public PickupSchedule(long id) {
        this.id = id;
    }

    public PickupSchedule(long id, Date pickupDatetime, Character status,
            long createUser, Date createDatetime, long lastupdateUser,
            Date lastupdateDatetime) {
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

    public long getPickupIndex() {
        return pickupIndex;
    }

    public void setPickupIndex(long pickupIndex) {
        this.pickupIndex = pickupIndex;
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

    public Pickup getPickupId() {
        return pickupId;
    }

    public void setPickupId(Pickup pickupId) {
        this.pickupId = pickupId;
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

    /**
     * @return the dispatchDateTime
     */
    public Date getDispatchDateTime() {
        return dispatchDateTime;
    }

    /**
     * @param dispatchDateTime the dispatchDateTime to set
     */
    public void setDispatchDateTime(Date dispatchDateTime) {
        this.dispatchDateTime = dispatchDateTime;
    }
}
