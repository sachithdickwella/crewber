package com.grabm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "webpage")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WebPage implements Serializable {

    private static final long serialVersionUID = 8624351476871174198L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FILE_NAME")
    protected String fileName;

    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "MAPPING_URL")
    protected String mappingUrl;

    @NotNull
    @Column(name = "CREATE_USER")
    protected long createUser;

    @NotNull
    @Column(name = "CREATE_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createDatetime;

    @NotNull
    @Column(name = "LASTUPDATE_USER")
    protected long lastupdateUser;

    @NotNull
    @Column(name = "LASTUPDATE_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastupdateDatetime;

    @Size(max = 45)
    @Column(name = "LASTSOURCE_IP")
    protected String lastsourceIp;

    @Fetch(FetchMode.SUBSELECT)
    @XmlTransient
    @ManyToMany(mappedBy = "webPageList", fetch = FetchType.EAGER)
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
