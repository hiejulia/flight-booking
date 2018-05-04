package com.project.flightbooking.rest;

import com.project.flightbooking.domain.Flight;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="flights") // xmlRootElement : flights
public class FlightList
{
    private List<Flight> flights;

    public FlightList() {}

    public FlightList(List<Flight> flights)
    {
        this.flights = flights;
    }

    @XmlElement(name="flight")
    public List<Flight> getVehicles() {
        return flights;
    }

    public void setVehicles(List<Flight> flights) {
        this.flights = flights;
    }


}