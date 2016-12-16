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
public class VehicleModel implements Serializable {

    private static final long serialVersionUID = 1089098749433087965L;

    private long id;

    private VehicleBrand vehicleBrandId;
    
    private VehicleType vehicleTypeId;
    
    private FuelType fuelType;

    private String name;

    private int year;

    private int fuelTankSize;

    private int fuelEfficiency;

    private long createUser;

    private Date createDateTime;

    private long lastUpdateUser;

    private Date lastUpdateDateTime;

    private String lastsourceIp;

    @XmlTransient
    private List<Vehicle> vehicleList;

    public VehicleModel() {
        /**
         * Nothing goes here.
         */
    }

    public VehicleModel(long id) {
        this.id = id;
    }

    public VehicleModel(long id, VehicleBrand vehicleBrandId, String name, int year,
            int fuelTankSize, int fuelEfficiency,
            long createUser, Date createDatetime, long lastupdateUser, Date lastupdateDatetime) {
        this.id = id;
        this.name = name;
        this.vehicleBrandId = vehicleBrandId;
        this.year = year;
        this.fuelTankSize = fuelTankSize;
        this.fuelEfficiency = fuelEfficiency;
        this.createUser = createUser;
        this.createDateTime = createDatetime;
        this.lastUpdateUser = lastupdateUser;
        this.lastUpdateDateTime = lastupdateDatetime;
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
     * @return the vehicleBrandId
     */
    public VehicleBrand getVehicleBrandId() {
        return vehicleBrandId;
    }

    /**
     * @param vehicleBrandId the vehicleBrandId to set
     */
    public void setVehicleBrandId(VehicleBrand vehicleBrandId) {
        this.vehicleBrandId = vehicleBrandId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the fuelTankSize
     */
    public int getFuelTankSize() {
        return fuelTankSize;
    }

    /**
     * @param fuelTankSize the fuelTankSize to set
     */
    public void setFuelTankSize(int fuelTankSize) {
        this.fuelTankSize = fuelTankSize;
    }

    /**
     * @return the fuelEfficiency
     */
    public int getFuelEfficiency() {
        return fuelEfficiency;
    }

    /**
     * @param fuelEfficiency the fuelEfficiency to set
     */
    public void setFuelEfficiency(int fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
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

    /**
     * @return the vehicleList
     */
    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    /**
     * @param vehicleList the vehicleList to set
     */
    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    /**
     * @return the vehicleTypeId
     */
    public VehicleType getVehicleTypeId() {
        return vehicleTypeId;
    }

    /**
     * @param vehicleTypeId the vehicleTypeId to set
     */
    public void setVehicleTypeId(VehicleType vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    /**
     * @return the fuelType
     */
    public FuelType getFuelType() {
        return fuelType;
    }

    /**
     * @param fuelType the fuelType to set
     */
    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }
}
