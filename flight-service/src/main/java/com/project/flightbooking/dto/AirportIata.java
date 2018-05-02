package com.project.flightbooking.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AirportIata {

    @JsonProperty("code")
    private String code;

    @JsonProperty("distance_meters")
    private String distance;

    public AirportIata() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

}