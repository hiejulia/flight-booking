package com.project.flightbooking.valueobject;

import com.project.flightbooking.domain.Flight;

import java.util.ArrayList;
import java.util.List;

public class AirportVO {


    private List<Flight> flightList = new ArrayList<>();

    private String name;

    private String id;

    private String city;

    public AirportVO(List<Flight> flightList, String name, String id, String city) {
        this.flightList = flightList;
        this.name = name;
        this.id = id;
        this.city = city;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "AirportVO{" +
                "flightList=" + flightList +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
