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
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 3459965483484457133L;

    private long id;

    private VehicleModel vehicleModelId;

    private Company companyId;

    private AreaGroup groupId;

    private String registrationNumber;

    private long passengersMax;

    private long passengersMin;

    private String color1;

    private String color2;

    private String note;

    private Character status;

    private int mileage;

    private String trackerImei;

    private String trackerMobileNumber;

    private long indexId;

    private long createUser;

    private Date createDatetime;

    private long lastupdateUser;

    private Date lastupdateDatetime;

    private String lastsourceIp;

    @XmlTransient
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
            String trackerImei, String trackerMobileNumber, long createUser, Date createDatetime,
            long lastupdateUser, Date lastupdateDatetime) {
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
