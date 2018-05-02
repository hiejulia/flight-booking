package com.project.flightbooking.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightSearchResult {

    @JsonProperty("result")
    private String result;

    public FlightSearchResult() {

    }

    public FlightSearchResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}