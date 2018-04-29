package com.project.flightbooking.domain;

import java.util.ArrayList;
import java.util.List;

public class Airport
{
    private List<Flight> flights = new ArrayList<>();

    private String id;

    private boolean isModified;

    private String name;

    private String city;

    public Airport(List<Flight> flights, String id, boolean isModified, String name, String city) {
        this.flights = flights;
        this.id = id;
        this.isModified = isModified;
        this.name = name;
        this.city = city;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
