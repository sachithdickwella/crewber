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
public class Dropoff implements Serializable {

    private static final long serialVersionUID = 5781604476674271422L;

    private long id;

    private long createUser;

    private Date createDatetime;

    private long lastupdateUser;

    private Date lastupdateDatetime;

    private String lastusourceIp;

    @XmlTransient
    private List<DropoffReSchedule> dropoffReScheduleList;

    @XmlTransient
    private List<DropoffSchedule> dropoffScheduleList;

    public Dropoff() {
        /**
         * Nothing goes here.
         */
    }

    public Dropoff(long id) {
        this.id = id;
    }

    public Dropoff(long id, long createUser, Date createDatetime, long lastupdateUser,
            Date lastupdateDatetime) {
        this.id = id;
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

    public String getLastusourceIp() {
        return lastusourceIp;
    }

    public void setLastusourceIp(String lastusourceIp) {
        this.lastusourceIp = lastusourceIp;
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
}
