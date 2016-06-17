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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "profile")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Profile implements Serializable {

    private static final long serialVersionUID = 2978344710167813846L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected long id;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NAME")
    protected String name;

    @Lob
    @Size(max = 65535)
    @Column(name = "DESCRIPTION")
    protected String description;

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

    @JoinTable(name = "profilewebpage", joinColumns = {
        @JoinColumn(name = "PROFILE_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "WEBPAGE_ID", referencedColumnName = "ID")})
    @Fetch(FetchMode.SUBSELECT)
    @XmlTransient
    @ManyToMany(fetch = FetchType.EAGER)
    protected List<WebPage> webPageList;

    @Fetch(FetchMode.SUBSELECT)
    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profileId", fetch = FetchType.EAGER)
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
