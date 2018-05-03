package com.project.flightbooking.service;


import com.project.flightbooking.domain.Airport;
import com.project.flightbooking.domain.Flight;
import com.project.flightbooking.repository.AirportRepository;
import com.project.flightbooking.repository.FlightRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FlightService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private FlightRepository flightRepository;


    /**
     * FIND ALL FLIGHTS BY AIRPORT NAME
     * @param name
     * @return
     */
    public List<Flight> findFlightByAirportName(String name) {
        List<Flight> flights = new ArrayList<>();

        for (Airport airport : airportRepository.findAll()) {
            if (airport.getName().equals(name)) {
                flights.addAll(airport.getFlights());
            }
        }

        return flights;
    }
}
