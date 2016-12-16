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
public class AreaGroup implements Serializable {

    private static final long serialVersionUID = -178295426190620575L;

    private long id;

    private String name;

    private long createUser;

    private Date createDateTime;

    private long lastUpdateUser;

    private Date lastUpdateDateTime;

    private String lastsourceIp;

    @XmlTransient
    private List<Vehicle> vehicleList;

    public AreaGroup() {
        /**
         * Nothing goes here.
         */
    }

    public AreaGroup(long id) {
        this.id = id;
    }
    
    public AreaGroup(long id, String name,
            long createUser, Date createDatetime, long lastupdateUser, Date lastupdateDatetime) {
        this.id = id;
        this.name = name;
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
