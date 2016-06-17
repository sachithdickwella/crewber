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
@Table(name = "vehiclebrand")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleBrand implements Serializable {

    private static final long serialVersionUID = -6299708796126451786L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME")
    private String name;

    @Size(max = 45)
    @Column(name = "COUNTRY")
    private String country;

    @NotNull
    @Column(name = "CREATE_USER")
    private long createUser;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATETIME")
    private Date createDateTime;

    @NotNull
    @Column(name = "LASTUPDATE_USER")
    private long lastUpdateUser;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LASTUPDATE_DATETIME")
    private Date lastUpdateDateTime;

    @Size(max = 45)
    @Column(name = "LASTSOURCE_IP")
    private String lastsourceIp;

    @XmlTransient
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicleBrandId", fetch = FetchType.EAGER)
    private List<VehicleModel> vehicleModelList;

    public VehicleBrand() {
        /**
         * Nothing goes here.
         */
    }

    public VehicleBrand(long id) {
        this.id = id;
    }

    public VehicleBrand(long id, String name, String country,
            long createUser, Date createDatetime, long lastupdateUser, Date lastupdateDatetime) {
        this.id = id;
        this.name = name;
        this.country = country;
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
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
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
     * @return the vehicleBrandList
     */
    public List<VehicleModel> getVehicleBrandList() {
        return vehicleModelList;
    }

    /**
     * @param vehicleModelList the vehicleBrandList to set
     */
    public void setVehicleBrandList(List<VehicleModel> vehicleModelList) {
        this.vehicleModelList = vehicleModelList;
    }
}
