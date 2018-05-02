package com.project.flightbooking.domain;


import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Airport extends BaseEntity<String> {

    private List<Flight> flights = new ArrayList<>();

    @JsonProperty("city")
    private String city;

    @JsonProperty("code")
    private String code;

    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public Airport(String name, String id, String city, List<Flight> flights) {
        super(id, name);
        this.city = city;
        this.flights = flights;
    }


    public void setTables(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFlights() {
        return flights;
    }


    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, address: %s, tables: %s}", this.getId(),
                this.getName(), this.getCity(), this.getFlights());
    }
}