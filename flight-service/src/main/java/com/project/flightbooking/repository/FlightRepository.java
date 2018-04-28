package com.project.flightbooking.repository;


import com.project.flightbooking.domain.Airport;
import com.project.flightbooking.domain.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface FlightRepository extends CrudRepository<Flight, String> {

}
