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
 * @author Roshin Perera
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PickupArea implements Serializable {

    private static final long serialVersionUID = 6849695207299185335L;

    public PickupArea() {
        /**
         * Nothing goes here.
         */
    }

    public PickupArea(long id) {
        this.id = id;
    }

    private long id;

    private String name;

    private Date pickupTime;

    private long createUser;

    private Date createDatetime;

    private long lastupdateUser;

    private Date lastupdateDatetime;

    @XmlTransient
    private List<EndUser> endUsers;

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
     * @return the pickupTime
     */
    public Date getPickupTime() {
        return pickupTime;
    }

    /**
     * @param pickupTime the pickupTime to set
     */
    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
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
     * @return the createDatetime
     */
    public Date getCreateDatetime() {
        return createDatetime;
    }

    /**
     * @param createDatetime the createDatetime to set
     */
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
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
     * @return the lastupdateDatetime
     */
    public Date getLastupdateDatetime() {
        return lastupdateDatetime;
    }

    /**
     * @param lastupdateDatetime the lastupdateDatetime to set
     */
    public void setLastupdateDatetime(Date lastupdateDatetime) {
        this.lastupdateDatetime = lastupdateDatetime;
    }

    /**
     * @return the endUsers
     */
    public List<EndUser> getEndUsers() {
        return endUsers;
    }

    /**
     * @param endUsers the endUsers to set
     */
    public void setEndUsers(List<EndUser> endUsers) {
        this.endUsers = endUsers;
    }
}
