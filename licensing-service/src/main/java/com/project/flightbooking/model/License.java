package com.project.flightbooking.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "licenses")
public class License {
    @Id
    @Column(name = "license_id", nullable = false)
    private Long licenseId;

    @Column(name = "airport_id", nullable = false)
    private Long airportId;

    @Transient
    private String airportName = "";

    @Transient
    private String contactName = "";

    @Transient
    private String contactPhone = "";

    @Transient
    private String contactEmail = "";

    @Column(name = "license_type", nullable = false)
    private String licenseType;

    @Column(name = "license_max", nullable = false)
    private Integer licenseMax;

    @Column(name = "license_allocated", nullable = false)
    private Integer licenseAllocated;


    public Long getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Long licenseId) {
        this.licenseId = licenseId;
    }

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public Integer getLicenseMax() {
        return licenseMax;
    }

    public void setLicenseMax(Integer licenseMax) {
        this.licenseMax = licenseMax;
    }

    public Integer getLicenseAllocated() {
        return licenseAllocated;
    }

    public void setLicenseAllocated(Integer licenseAllocated) {
        this.licenseAllocated = licenseAllocated;
    }

    public License(Long licenseId, Long airportId, String airportName, String contactName, String contactPhone, String contactEmail, String licenseType, Integer licenseMax, Integer licenseAllocated) {
        this.licenseId = licenseId;
        this.airportId = airportId;
        this.airportName = airportName;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.licenseType = licenseType;
        this.licenseMax = licenseMax;
        this.licenseAllocated = licenseAllocated;
    }

    @Override
    public String toString() {
        return "License{" +
                "licenseId=" + licenseId +
                ", airportId=" + airportId +
                ", airportName='" + airportName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", licenseType='" + licenseType + '\'' +
                ", licenseMax=" + licenseMax +
                ", licenseAllocated=" + licenseAllocated +
                '}';
    }
}
