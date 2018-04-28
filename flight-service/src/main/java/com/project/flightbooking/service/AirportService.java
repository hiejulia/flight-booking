package com.project.flightbooking.service;


import com.project.flightbooking.domain.Airport;
import com.project.flightbooking.domain.Entity;
import com.project.flightbooking.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Service
@Transactional
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;


    public void add(Airport airport) throws Exception {
        // Validation if airport name is already exists
        if (airportRepository.getOne(airport.getId()).getName()==airport.getName()) {
            throw new Exception(String.format("There is already a product with the name - %s", airport.getName()));
        }

        if (airport.getName() == null || "".equals(airport.getName())) {
            throw new Exception("Restaurant name cannot be null or empty string.");
        }
        airportRepository.save(airport);
    }


    public Collection<Airport> findByName(String name) throws Exception {
        return airportRepository.findByName(name);
    }

    public void update(Airport airport) throws Exception {
        // Get airport by id

        // Update

        // Save airport
    }

    public void delete(String id) throws Exception {
        Airport airport = airportRepository.getOne(id);
        airportRepository.delete(airport);
    }


    public Entity findById(String id) throws Exception {
        return airportRepository.getOne(id);
    }


    public Collection<Airport> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
