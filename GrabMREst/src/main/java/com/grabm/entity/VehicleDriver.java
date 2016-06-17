package com.grabm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Sachith Dickwella
 */
@Entity
@Table(name = "vehicledriver")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleDriver implements Serializable {

    private static final long serialVersionUID = -8841872493248287944L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    
    @NotNull
    @Column(name = "STATUS")
    private char status;

    @Lob
    @Size(max = 65535)
    @Column(name = "NOTE")
    private String note;
    
    @NotNull
    @Column(name = "CREATE_USER")
    private long createUser;

    @NotNull
    @Column(name = "CREATE_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateTime;

    @NotNull
    @Column(name = "LASTUPDATE_USER")
    private long lastupdateUser;

    @NotNull
    @Column(name = "LASTUPDATE_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastupdateDateTime;

    @Size(max = 45)
    @Column(name = "LASTSOURCE_IP")
    private String lastsourceIp;

    @XmlTransient
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicleDriver", fetch = FetchType.EAGER)
    private List<PickupSchedule> pickupScheduleList;

    @XmlTransient
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicleDriver", fetch = FetchType.EAGER)
    private List<DropoffReSchedule> dropoffReScheduleList;

    @XmlTransient
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicleDriver", fetch = FetchType.EAGER)
    private List<DropoffSchedule> dropoffScheduleList;

    @XmlTransient
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicleDriver", fetch = FetchType.EAGER)
    private List<PickupReSchedule> pickupReScheduleList;

    @JoinColumn(name = "DRIVER_ID", referencedColumnName = "ID", updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Driver driver;

    @JoinColumn(name = "VEHICLE_ID", referencedColumnName = "ID", updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Vehicle vehicle;

    public VehicleDriver() {
        /**
         * Nothing goes here.
         */
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
