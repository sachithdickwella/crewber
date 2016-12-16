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
public class Company implements Serializable {

    private static final long serialVersionUID = -3534917274061480105L;

    private long id;

    private String name;

    private String addressLine1;

    private String addressLine2;

    private String cityTown;

    private String zipCode;

    private String country;

    private String telephoneNumber;

    private String mobileNumber;

    private long createUser;

    private Date createDateTime;

    private long lastUpdateUser;

    private Date lastUpdateDateTime;

    private String lastsourceIp;

    @XmlTransient
    private List<Vehicle> vehicleList;

    public Company() {
        /**
         * Nothing goes here.
         */
    }

    public Company(long id) {
        this.id = id;
    }

    public Company(long id, String name, String addressLine1, String addressLine2, String cityTown, String zipCode,
            String country, String telephoneNumber, String mobileNumber,
            long createUser, Date createDatetime, long lastupdateUser, Date lastupdateDatetime) {
        this.id = id;
        this.name = name;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.cityTown = cityTown;
        this.zipCode = zipCode;
        this.country = country;
        this.telephoneNumber = telephoneNumber;
        this.mobileNumber = mobileNumber;
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
     * @return the addressLine1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * @param addressLine1 the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * @return the addressLine2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * @param addressLine2 the addressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * @return the cityTown
     */
    public String getCityTown() {
        return cityTown;
    }

    /**
     * @param cityTown the cityTown to set
     */
    public void setCityTown(String cityTown) {
        this.cityTown = cityTown;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the telephoneNumber
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * @param telephoneNumber the telephoneNumber to set
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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
}
