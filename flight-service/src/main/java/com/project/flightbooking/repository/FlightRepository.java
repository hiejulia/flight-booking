package com.project.flightbooking.repository;


import com.project.flightbooking.domain.Airport;
import com.project.flightbooking.domain.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
    // Find flight by airport name
    @Cacheable("flightsByName")
    List<Flight> findAllByAirportName(String name );

    // Find flight by name


    Flight findByName(String name );

     
    @CacheEvict(cacheNames="flightsCache", allEntries=true) 
    Fligth save(Fligth flight); 
     
    @CacheEvict(cacheNames="flightsCache", allEntries=true) 
    void delete(Flight flight); 

}
