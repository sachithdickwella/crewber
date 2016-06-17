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
import javax.persistence.Lob;
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

/**
 *
 * @author Sachith Dickwella
 */
@Entity
@Table(name = "driver")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Driver implements Serializable {

    private static final long serialVersionUID = 720726892591017095L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "LAST_NAME")
    private String lastName;
    
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "NIC")
    private String nic;

    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "MOBILE_NUMBER1")
    private String mobileNumber1;

    @Size(max = 15)
    @Column(name = "MOBILE_NUMBER2")
    private String mobileNumber2;

    @Lob
    @Column(name = "PHOTO")
    private byte[] photo;
    
    @Column(name = "RATING")
    private long rating;

    @Lob
    @Size(max = 65535)
    @Column(name = "NOTE")
    private String note;

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

    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver", fetch = FetchType.LAZY)
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

    @XmlTransient
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
