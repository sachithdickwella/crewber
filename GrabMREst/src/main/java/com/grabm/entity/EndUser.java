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
import javax.persistence.ManyToMany;
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
@Table(name = "enduser")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EndUser implements Serializable {

    private static final long serialVersionUID = 5388995106291731675L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "PREFIX")
    private String prefix;

    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESIGNATION")
    private String designation;

    @Size(max = 40)
    @Column(name = "TYPE")
    private String type;

    @Size(max = 75)
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ADDRESS_LINE1")
    private String addressLine1;

    @Size(max = 100)
    @Column(name = "ADDRESS_LINE2")
    private String addressLine2;

    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "CITY_TOWN")
    private String cityTown;

    @Size(max = 10)
    @Column(name = "ZIP_CODE")
    private String zipCode;

    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "HOME_LATITUDE")
    private double homeLatitude;

    @Column(name = "HOME_LONGITUDE")
    private double homeLongitude;

    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MEMBERSHIP_NUMBER")
    private String membershipNumber;

    @Size(max = 200)
    @Column(name = "GCM_TOKEN")
    private String gcmToken;
    
    @Size(max = 6)
    @Column(name = "PIN")
    private String pin;
    
    @Column(name = "PIN_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pinDateTime;

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
    @Temporal(TemporalType.DATE)
    private Date lastupdateDatetime;

    @Size(max = 45)
    @Column(name = "LASTSOURCE_IP")
    private String lastsourceIp;

    @XmlTransient
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enduserId", fetch = FetchType.EAGER)
    private List<PickupSchedule> pickupScheduleList;

    @XmlTransient
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enduserId", fetch = FetchType.EAGER)
    private List<DropoffReSchedule> dropoffReScheduleList;

    @XmlTransient
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enduserId", fetch = FetchType.EAGER)
    private List<DropoffSchedule> dropoffScheduleList;

    @XmlTransient
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enduserId", fetch = FetchType.EAGER)
    private List<PickupReSchedule> pickupReScheduleList;

    @Fetch(FetchMode.SUBSELECT)
    @XmlTransient
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "endUserList")
    private List<MobilePickupSummery> mobilePickupSummeryList;

    public EndUser() {
        /**
         * Nothing goes here.
         */
    }

    public EndUser(long id) {
        this.id = id;
    }

    public EndUser(long id, String prefix, String firstName, String lastName,
            String designation, String addressLine1, String cityTown, String country,
            double homeLatitude, double homeLongitude,
            String mobileNumber, String membershipNumber, String gcmToken,
            Character status, long createUser, Date createDatetime,
            long lastupdateUser, Date lastupdateDatetime) {
        this.id = id;
        this.prefix = prefix;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        this.addressLine1 = addressLine1;
        this.cityTown = cityTown;
        this.country = country;
        this.homeLatitude = homeLatitude;
        this.homeLongitude = homeLongitude;
        this.mobileNumber = mobileNumber;
        this.membershipNumber = membershipNumber;
        this.gcmToken = gcmToken;
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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCityTown() {
        return cityTown;
    }

    public void setCityTown(String cityTown) {
        this.cityTown = cityTown;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(String membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getGcmToken() {
        return gcmToken;
    }

    public void setGcmToken(String gcmToken) {
        this.gcmToken = gcmToken;
    }

    /**
     * @return the homeLatitude
     */
    public double getHomeLatitude() {
        return homeLatitude;
    }

    /**
     * @param homeLatitude the homeLatitude to set
     */
    public void setHomeLatitude(double homeLatitude) {
        this.homeLatitude = homeLatitude;
    }

    /**
     * @return the homeLongitude
     */
    public double getHomeLongitude() {
        return homeLongitude;
    }

    /**
     * @param homeLongitude the homeLongitude to set
     */
    public void setHomeLongitude(double homeLongitude) {
        this.homeLongitude = homeLongitude;
    }

    /**
     * @return the mobilePickupSummeryList
     */
    public List<MobilePickupSummery> getMobilePickupSummeryList() {
        return mobilePickupSummeryList;
    }

    /**
     * @param mobilePickupSummeryList the mobilePickupSummeryList to set
     */
    public void setMobilePickupSummeryList(List<MobilePickupSummery> mobilePickupSummeryList) {
        this.mobilePickupSummeryList = mobilePickupSummeryList;
    }

    /**
     * @return the pin
     */
    public String getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * @return the pinDateTime
     */
    public Date getPinDateTime() {
        return pinDateTime;
    }

    /**
     * @param pinDateTime the pinDateTime to set
     */
    public void setPinDateTime(Date pinDateTime) {
        this.pinDateTime = pinDateTime;
    }
}
