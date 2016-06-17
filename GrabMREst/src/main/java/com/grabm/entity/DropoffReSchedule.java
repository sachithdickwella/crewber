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
import javax.persistence.JoinColumns;
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
@Table(name = "dropoffreschedule")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DropoffReSchedule implements Serializable {

    private static final long serialVersionUID = -7012977492105510588L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "REASON")
    private String reason;

    @NotNull
    @Column(name = "PICKUP_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pickupDatetime;

    @Column(name = "PTI")
    private long pti;

    @NotNull
    @Column(name = "RESCHEDULED_TURN")
    private long rescheduledTurn;

    @Lob
    @Size(max = 65535)
    @Column(name = "NOTE")
    private String note;

    @NotNull
    @Column(name = "STATUS")
    private Character status;

    @Size(max = 50)
    @Column(name = "PICKUP_POINT")
    private String pickupPoint;

    @Size(max = 50)
    @Column(name = "DROPOFF_POINT")
    private String dropoffPoint;

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

    @JoinColumn(name = "DROPOFF_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Dropoff dropoffId;

    @JoinColumn(name = "VEHICLEDRIVER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private VehicleDriver vehicleDriver;

    @JoinColumn(name = "ENDUSER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EndUser enduserId;

    public DropoffReSchedule() {
        /**
         * Nothing goes here.
         */
    }

    public DropoffReSchedule(long id) {
        this.id = id;
    }

    public DropoffReSchedule(long id, String reason, Date pickupDatetime,
            long rescheduledTurn, Character status, long createUser, Date createDatetime,
            long lastupdateUser, Date lastupdateDatetime) {
        this.id = id;
        this.reason = reason;
        this.pickupDatetime = pickupDatetime;
        this.rescheduledTurn = rescheduledTurn;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public long getRescheduledTurn() {
        return rescheduledTurn;
    }

    public void setRescheduledTurn(long rescheduledTurn) {
        this.rescheduledTurn = rescheduledTurn;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
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
