package com.project.flightbooking.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.BigInteger;



public class Flight extends BaseEntity<BigInteger> implements Serializable {
    // Flight : capacity

    private int capacity;

    private BigDecimal lat;

    private BigDecimal longitude;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    public Flight(@JsonProperty("name") String name, @JsonProperty("id") BigInteger id, @JsonProperty("capacity") int capacity,@JsonProperty("lat") BigDecimal lat, @JsonProperty("longitude") BigDecimal longitude) {
        super(id, name);
        this.capacity = capacity;
        this.lat = lat;
        this.longitude = longitude;

    }


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

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }


    @Override
    public String toString() {
        return "Flight{" +
                "capacity=" + capacity +
                ", lat=" + lat +
                ", longitude=" + longitude +
                '}';
    }
}