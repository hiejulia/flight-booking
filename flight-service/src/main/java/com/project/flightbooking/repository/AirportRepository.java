package com.project.flightbooking.repository;


import com.project.flightbooking.domain.Airport;
import com.project.flightbooking.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {

    Collection<Airport> findByName(String name);

    // Find flights by airport name


}
