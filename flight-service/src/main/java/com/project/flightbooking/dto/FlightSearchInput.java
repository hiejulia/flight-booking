package com.project.flightbooking.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightSearchInput {

    // Flight Search query input : origin , destination , departDate, returnDate

    @JsonProperty("origin")
    private String origin;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("departDate")
    private String departDate;

    @JsonProperty("returnDate")
    private String returnDate;

    @JsonProperty("adultCount")
    private int adultCount;

    public FlightSearchInput() {

    }

    public FlightSearchInput(String origin, String destination, String departDate, String returnDate, int adultCount) {

        this.origin = origin;
        this.destination = destination;
        this.departDate = departDate;
        this.returnDate = returnDate;
        this.adultCount = adultCount;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

}