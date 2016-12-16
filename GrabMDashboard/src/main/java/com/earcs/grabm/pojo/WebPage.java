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
public class WebPage implements Serializable {

    private static final long serialVersionUID = 8624351476871174198L;
    
    protected long id;

    protected String fileName;

    protected String mappingUrl;

    protected long createUser;

    protected Date createDatetime;

    protected long lastupdateUser;

    protected Date lastupdateDatetime;

    protected String lastsourceIp;

    @XmlTransient
    protected List<Profile> profileList;

    public WebPage() {
        /**
         * Nothing goes here.
         */
    }

    public WebPage(long id) {
        this.id = id;
    }

    public WebPage(long id, String fileName, String mappingUrl, long createUser,
            Date createDatetime, long lastupdateUser, Date lastupdateDatetime) {
        this.id = id;
        this.fileName = fileName;
        this.mappingUrl = mappingUrl;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMappingUrl() {
        return mappingUrl;
    }

    public void setMappingUrl(String mappingUrl) {
        this.mappingUrl = mappingUrl;
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

    public List<Profile> getProfileList() {
        return profileList;
    }

    public void setProfileList(List<Profile> profileList) {
        this.profileList = profileList;
    }
}
