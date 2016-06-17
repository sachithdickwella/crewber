package com.grabm.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sachith Dickwella
 */
@Entity
@Table(name = "admininstrator")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Administrator implements Serializable {

    private static final long serialVersionUID = 276979430399381033L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USER_NAME")
    protected String userName;

    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "PASSWORD")
    protected String password;

    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "FIRST_NAME")
    protected String firstName;

    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "LAST_NAME")
    protected String lastName;

    @Size(max = 15)
    @Column(name = "MOBILE_NO")
    protected String mobileNo;

    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "EMAIL")
    protected String email;

    @NotNull
    @Column(name = "STATUS")
    protected Character status;

    @Column(name = "INDEX_ID")
    protected long indexId;

    @Lob
    @Size(max = 65535)
    @Column(name = "NOTE")
    protected String note;

    @JoinColumn(name = "PROFILE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    protected Profile profileId;

    @Column(name = "CREATE_USER")
    protected long createUser;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATETIME")
    protected Date createDateTime;

    @Column(name = "LASTUPDATE_USER")
    protected long lastUpdateUser;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LASTUPDATE_DATETIME")
    protected Date lastUpdateDateTime;

    public Administrator() {
        /**
         * Nothing goes here.
         */
    }

    public Administrator(long id) {
        this.id = id;
    }

    public Administrator(long id, String userName, String password, String firstName,
            String lastName, String email, Character status) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Profile getProfileId() {
        return profileId;
    }

    public void setProfileId(Profile profileId) {
        this.profileId = profileId;
    }

    public long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public long getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(long lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Date getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(Date lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }
}
