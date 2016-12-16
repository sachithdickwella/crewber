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
public class Driver implements Serializable {

    private static final long serialVersionUID = 720726892591017095L;

    private long id;

    private String firstName;

    private String lastName;
    
    private String nic;

    private String mobileNumber1;

    private String mobileNumber2;

    private byte[] photo;
    
    private long rating;

    private String note;

    private Character status;

    private long indexId;

    private long createUser;

    private Date createDatetime;

    private long lastupdateUser;

    private Date lastupdateDatetime;

    private String lastsourceIp;

    @XmlTransient
    private List<VehicleDriver> vehicleDriverList;

    public Driver() {
        /**
         * Nothing goes here.
         */
    }

    public Driver(long id) {
        this.id = id;
    }

    public Driver(long id, String firstName, String lastName, String mobileNumber1, String mobileNumber2,
            byte[] photo, Character status, long createUser, Date createDatetime, long lastupdateUser,
            Date lastupdateDatetime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber1 = mobileNumber1;
        this.mobileNumber2 = mobileNumber2;
        this.photo = photo;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
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

    public List<VehicleDriver> getVehicleDriverList() {
        return vehicleDriverList;
    }

    public void setVehicleDriverList(List<VehicleDriver> vehicleDriverList) {
        this.vehicleDriverList = vehicleDriverList;
    }

    /**
     * @return the mobileNumber1
     */
    public String getMobileNumber1() {
        return mobileNumber1;
    }

    /**
     * @param mobileNumber1 the mobileNumber1 to set
     */
    public void setMobileNumber1(String mobileNumber1) {
        this.mobileNumber1 = mobileNumber1;
    }

    /**
     * @return the mobileNumber2
     */
    public String getMobileNumber2() {
        return mobileNumber2;
    }

    /**
     * @param mobileNumber2 the mobileNumber2 to set
     */
    public void setMobileNumber2(String mobileNumber2) {
        this.mobileNumber2 = mobileNumber2;
    }

    /**
     * @return the photo
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }
}
