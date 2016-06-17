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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
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
@Table(name = "vehicletype", uniqueConstraints = {
    @UniqueConstraint(columnNames = "SHORT_NAME"),
    @UniqueConstraint(columnNames = "LONG_NAME")})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleType implements Serializable {

    private static final long serialVersionUID = -8215053829535854082L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "SHORT_NAME")
    private char shortName;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "LONG_NAME")
    private String longName;

    @NotNull
    @Column(name = "CREATE_USER")
    private long createUser;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATETIME")
    private Date createDateTime;

    @Column(name = "LASTUPDATE_USER")
    private long lastUpdateUser;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LASTUPDATE_DATETIME")
    private Date lastUpdateDateTime;

    @XmlTransient
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "vehicleTypeId")
    private List<VehicleModel> vehicleModelList;

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
    public char getShortName() {
        return shortName;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName(char shortName) {
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
     * @return the vehicleModelList
     */
    public List<VehicleModel> getVehicleModelList() {
        return vehicleModelList;
    }

    /**
     * @param vehicleModelList the vehicleModelList to set
     */
    public void setVehicleModelList(List<VehicleModel> vehicleModelList) {
        this.vehicleModelList = vehicleModelList;
    }
}
