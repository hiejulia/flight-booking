package com.project.flightbooking.repository;


import com.project.flightbooking.domain.Airport;
import com.project.flightbooking.domain.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface FlightRepository extends CrudRepository<Flight, String> {
    // Find flight by airport name
    List<Flight> findAllByAirportName(String name );

}
