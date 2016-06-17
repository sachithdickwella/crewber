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
@Table(name = "vehicle")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 3459965483484457133L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @NotNull
    @JoinColumn(name = "VEHICLEMODEL_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private VehicleModel vehicleModelId;

    @NotNull
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Company companyId;

    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private AreaGroup groupId;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "REGISTRATION_NUMBER")
    private String registrationNumber;

    @NotNull
    @Column(name = "PASSENGERS_MAX")
    private long passengersMax;

    @NotNull
    @Column(name = "PASSENGERS_MIN")
    private long passengersMin;

    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "COLOR_1")
    private String color1;

    @Size(max = 10)
    @Column(name = "COLOR_2")
    private String color2;

    @Lob
    @Size(max = 65535)
    @Column(name = "NOTE")
    private String note;

    @NotNull
    @Column(name = "STATUS")
    private Character status;

    @NotNull
    @Column(name = "MILEAGE")
    private int mileage;

    @NotNull
    @Column(name = "TRACKER_IMEI")
    private String trackerImei;

    @NotNull
    @Column(name = "TRACKER_MOBILENUMBER")
    private String trackerMobileNumber;

    @Column(name = "INDEX_ID")
    private long indexId;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle", fetch = FetchType.LAZY)
    private List<VehicleDriver> vehicleDriverList;

    public Vehicle() {
        /**
         * Nothing goes here.
         */
    }

    public Vehicle(long id) {
        this.id = id;
    }

    public Vehicle(long id, VehicleModel vehicleModelId, Company companyId, AreaGroup groupId, String registrationNumber,
            long passengersMax, long passengersMin, String color1, Character status, int mileage,
            String trackerImei, String trackerMobileNumber, long createUser, Date createDateTime,
            long lastupdateUser, Date lastupdateDateTime) {
        this.id = id;
        this.vehicleModelId = vehicleModelId;
        this.companyId = companyId;
        this.groupId = groupId;
        this.registrationNumber = registrationNumber;
        this.passengersMax = passengersMax;
        this.passengersMin = passengersMin;
        this.color1 = color1;
        this.status = status;
        this.mileage = mileage;
        this.trackerImei = trackerImei;
        this.trackerMobileNumber = trackerMobileNumber;
        this.createUser = createUser;
        this.createDateTime = createDateTime;
        this.lastupdateUser = lastupdateUser;
        this.lastupdateDateTime = lastupdateDateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public long getPassengersMax() {
        return passengersMax;
    }

    public void setPassengersMax(long passengersMax) {
        this.passengersMax = passengersMax;
    }

    public long getPassengersMin() {
        return passengersMin;
    }

    public void setPassengersMin(long passengersMin) {
        this.passengersMin = passengersMin;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
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

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public long getLastupdateUser() {
        return lastupdateUser;
    }

    public void setLastupdateUser(long lastupdateUser) {
        this.lastupdateUser = lastupdateUser;
    }

    public Date getLastupdateDateTime() {
        return lastupdateDateTime;
    }

    public void setLastupdateDateTime(Date lastupdateDateTime) {
        this.lastupdateDateTime = lastupdateDateTime;
    }

    public String getLastsourceIp() {
        return lastsourceIp;
    }

    public void setLastsourceIp(String lastsourceIp) {
        this.lastsourceIp = lastsourceIp;
    }

    @XmlTransient
    public List<VehicleDriver> getVehicleDriverList() {
        return vehicleDriverList;
    }

    public void setVehicleDriverList(List<VehicleDriver> vehicleDriverList) {
        this.vehicleDriverList = vehicleDriverList;
    }

    /**
     * @return the vehicleModelId
     */
    public VehicleModel getVehicleModelId() {
        return vehicleModelId;
    }

    /**
     * @param vehicleModelId the vehicleModelId to set
     */
    public void setVehicleModelId(VehicleModel vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
    }

    /**
     * @return the companyId
     */
    public Company getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId the companyId to set
     */
    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    /**
     * @return the groupId
     */
    public AreaGroup getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(AreaGroup groupId) {
        this.groupId = groupId;
    }

    /**
     * @return the mileage
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * @param mileage the mileage to set
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    /**
     * @return the trackerImei
     */
    public String getTrackerImei() {
        return trackerImei;
    }

    /**
     * @param trackerImei the trackerImei to set
     */
    public void setTrackerImei(String trackerImei) {
        this.trackerImei = trackerImei;
    }

    /**
     * @return the trackerMobileNumber
     */
    public String getTrackerMobileNumber() {
        return trackerMobileNumber;
    }

    /**
     * @param trackerMobileNumber the trackerMobileNumber to set
     */
    public void setTrackerMobileNumber(String trackerMobileNumber) {
        this.trackerMobileNumber = trackerMobileNumber;
    }
}
