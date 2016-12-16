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
public class Designation implements Serializable {

    private static final long serialVersionUID = 5290229574544809399L;

    public Designation() {
        /**
         * Nothing goes here.
         */
    }

    public Designation(long id) {
        this.id = id;
    }

    private long id;

    private String shortName;

    private String longName;

    private char memberType;

    private Date hoursBeforeReport;

    private long createUser;

    private Date createDatetime;

    private long lastupdateUser;

    private Date lastupdateDatetime;

    private String lastsourceIp;

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
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @return the longName
     */
    public String getLongName() {
        return longName;
    }

    /**
     * @param longName the longName to set
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }

    /**
     * @return the memberType
     */
    public char getMemberType() {
        return memberType;
    }

    /**
     * @param memberType the memberType to set
     */
    public void setMemberType(char memberType) {
        this.memberType = memberType;
    }

    /**
     * @return the hoursBeforeReport
     */
    public Date getHoursBeforeReport() {
        return hoursBeforeReport;
    }

    /**
     * @param hoursBeforeReport the hoursBeforeReport to set
     */
    public void setHoursBeforeReport(Date hoursBeforeReport) {
        this.hoursBeforeReport = hoursBeforeReport;
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
