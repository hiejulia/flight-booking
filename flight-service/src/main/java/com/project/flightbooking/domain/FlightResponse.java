package com.project.flightbooking.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "flightResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightResponse {

    // error
    private String error;

    public FlightResponse() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public FlightResponse(String error) {

        this.error = error;
    }
}