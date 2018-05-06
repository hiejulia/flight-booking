package com.project.flightbooking.dao;


import com.project.flightbooking.domain.Flight;
import com.project.flightbooking.domain.Route;
import org.springframework.stereotype.Service;


@Service
public interface RouteDao {
    // Find flight in Route - From - To
    public Flight findAirlines(String from, String to);

    // Find flight
    public Flight findAirlines(Route route);
}