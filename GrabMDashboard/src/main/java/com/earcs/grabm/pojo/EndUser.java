package com.earcs.grabm.pojo;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.log4j.Logger;

/**
 *
 * @author Sachith Dickwella
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EndUser implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(EndUser.class);
    private static final long serialVersionUID = 5388995106291731675L;

    private long id;

    private String prefix;

    private Designation designation;

    private PickupArea pickupArea;

    private String firstName;

    private String lastName;

    private String email;

    private String addressLine1;

    private String addressLine2;

    private String cityTown;

    private String zipCode;

    private String country;

    private double currentLatitude;

    private double currentLongitude;

    private double previousLatitude;

    private double previousLongitude;

    private String mobileNumber;

    private String membershipNumber;

    private String fcmToken;

    private String pin;

    private Date pinDateTime;

    private String note;

    private Character status;

    private Character subscriptionStatus;

    private Character locationStatus;

    private long indexId;

    private long createUser;

    private Date createDatetime;

    private long lastupdateUser;

    private Date lastupdateDatetime;

    private String lastsourceIp;

    @XmlTransient
    private List<PickupSchedule> pickupScheduleList;

    @XmlTransient
    private List<DropoffReSchedule> dropoffReScheduleList;

    @XmlTransient
    private List<DropoffSchedule> dropoffScheduleList;

    @XmlTransient
    private List<PickupReSchedule> pickupReScheduleList;

    @XmlTransient
    private List<MobilePickupSummery> mobilePickupSummeryList;

    @XmlTransient
    public static List<EndUser> fromJson(String json) {
        JsonArray jsonArray = Json.createReader(new StringReader(json)).readArray();

        List<EndUser> endUsers = new ArrayList<>();
        jsonArray.stream().forEachOrdered((JsonValue jsonValue) -> {
            EndUser endUser = new EndUser();
            switch (jsonValue.getValueType()) {
                case OBJECT:
                    try {
                        JsonObject obj = Json.createReader(new StringReader(jsonValue.toString())).readObject();
                        endUser.setId(obj.getJsonNumber("id").longValue());
                        endUser.setFirstName(obj.getString("firstName"));
                        endUser.setLastName(obj.getString("lastName"));
                        endUser.setMembershipNumber(obj.getString("membershipNumber"));

                        JsonObject designationJson = obj.getJsonObject("designation");
                        Designation designation = new Designation();
                        designation.setId(designationJson.getJsonNumber("id") != null ? designationJson.getJsonNumber("id").longValue() : 0L);
                        designation.setShortName(designationJson.getString("shortName"));
                        designation.setLongName(designationJson.getString("longName"));

                        endUser.setDesignation(designation);
                        endUser.setNote(obj.getString("note"));

                    } catch (Exception ex) {
                        LOGGER.error(ex.getMessage(), ex);
                    }
                    endUsers.add(endUser);
                    break;
            }
        });
        return endUsers;
    }

    public EndUser() {
        /**
         * Nothing goes here.
         */
    }

    public EndUser(long id) {
        this.id = id;
    }

    public EndUser(long id, String prefix, String firstName, String lastName,
            String addressLine1, String cityTown, String country,
            String mobileNumber, String membershipNumber, String gcmToken,
            Character status, long createUser, Date createDatetime,
            long lastupdateUser, Date lastupdateDatetime) {
        this.id = id;
        this.prefix = prefix;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLine1 = addressLine1;
        this.cityTown = cityTown;
        this.country = country;
        this.mobileNumber = mobileNumber;
        this.membershipNumber = membershipNumber;
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

    /**
     * @return the designation
     */
    public Designation getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    /**
     * @return the pickupArea
     */
    public PickupArea getPickupArea() {
        return pickupArea;
    }

    /**
     * @param pickupArea the pickupArea to set
     */
    public void setPickupArea(PickupArea pickupArea) {
        this.pickupArea = pickupArea;
    }

    /**
     * @return the currentLatitude
     */
    public double getCurrentLatitude() {
        return currentLatitude;
    }

    /**
     * @param currentLatitude the currentLatitude to set
     */
    public void setCurrentLatitude(double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    /**
     * @return the currentLongitude
     */
    public double getCurrentLongitude() {
        return currentLongitude;
    }

    /**
     * @param currentLongitude the currentLongitude to set
     */
    public void setCurrentLongitude(double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    /**
     * @return the previousLatitude
     */
    public double getPreviousLatitude() {
        return previousLatitude;
    }

    /**
     * @param previousLatitude the previousLatitude to set
     */
    public void setPreviousLatitude(double previousLatitude) {
        this.previousLatitude = previousLatitude;
    }

    /**
     * @return the fcmToken
     */
    public String getFcmToken() {
        return fcmToken;
    }

    /**
     * @param fcmToken the fcmToken to set
     */
    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    /**
     * @return the subscriptionStatus
     */
    public Character getSubscriptionStatus() {
        return subscriptionStatus;
    }

    /**
     * @param subscriptionStatus the subscriptionStatus to set
     */
    public void setSubscriptionStatus(Character subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    /**
     * @return the locationStatus
     */
    public Character getLocationStatus() {
        return locationStatus;
    }

    /**
     * @param locationStatus the locationStatus to set
     */
    public void setLocationStatus(Character locationStatus) {
        this.locationStatus = locationStatus;
    }

    /**
     * @return the previousLongitude
     */
    public double getPreviousLongitude() {
        return previousLongitude;
    }

    /**
     * @param previousLongitude the previousLongitude to set
     */
    public void setPreviousLongitude(double previousLongitude) {
        this.previousLongitude = previousLongitude;
    }
}
