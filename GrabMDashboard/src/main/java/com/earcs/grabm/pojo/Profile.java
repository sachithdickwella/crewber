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
public class Profile implements Serializable {

    private static final long serialVersionUID = 2978344710167813846L;
    
    protected long id;

    protected String name;

    protected String description;

    protected long createUser;

    protected Date createDatetime;

    protected long lastupdateUser;

    protected Date lastupdateDatetime;

    protected String lastsourceIp;

    @XmlTransient
    protected List<WebPage> webPageList;

    @XmlTransient
    protected List<Administrator> admininstratorList;

    public Profile() {
        /**
         * Nothing goes here.
         */
    }

    public Profile(long id) {
        this.id = id;
    }

    public Profile(long id, String name, long createUser, Date createDatetime,
            long lastupdateUser, Date lastupdateDatetime) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<WebPage> getWebPageList() {
        return webPageList;
    }

    public void setWebPageList(List<WebPage> webPageList) {
        this.webPageList = webPageList;
    }

    public List<Administrator> getAdmininstratorList() {
        return admininstratorList;
    }

    public void setAdmininstratorList(List<Administrator> admininstratorList) {
        this.admininstratorList = admininstratorList;
    }
}
