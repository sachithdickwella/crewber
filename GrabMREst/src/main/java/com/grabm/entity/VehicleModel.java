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
@Table(name = "vehiclemodel")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleModel implements Serializable {

    private static final long serialVersionUID = 1089098749433087965L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @NotNull
    @JoinColumn(name = "VEHICLEBRAND_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private VehicleBrand vehicleBrandId;
    
    @NotNull
    @JoinColumn(name = "VEHICLETYPE_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private VehicleType vehicleTypeId;
    
    @NotNull
    @JoinColumn(name = "FUELTYPE_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private FuelType fuelType;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "YEAR")
    private int year;

    @NotNull
    @Column(name = "FULE_TANKSIZE")
    private int fuelTankSize;

    @NotNull
    @Column(name = "FUEL_EFFICIENCY")
    private int fuelEfficiency;

    @NotNull
    @Column(name = "CREATE_USER")
    private long createUser;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATETIME")
    private Date createDateTime;

    @NotNull
    @Column(name = "LASTUPDATE_USER")
    private long lastUpdateUser;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LASTUPDATE_DATETIME")
    private Date lastUpdateDateTime;

    @Size(max = 45)
    @Column(name = "LASTSOURCE_IP")
    private String lastsourceIp;

    @XmlTransient
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicleModelId", fetch = FetchType.EAGER)
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
