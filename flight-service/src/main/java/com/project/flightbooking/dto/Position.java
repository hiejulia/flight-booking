package com.project.flightbooking.dto;


import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Position {
    private BigDecimal lat;

    private BigDecimal longitude;

    private Date timestamp;

    private boolean upToDate;


    public BigDecimal getLat() {
        return lat;
    }
    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }
    public BigDecimal getLongitude() {
        return longitude;
    }
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public boolean isUpToDate() {
        return upToDate;
    }
    public void setUpToDate(boolean upToDate) {
        this.upToDate = upToDate;
    }




}