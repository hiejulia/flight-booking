package com.project.flightbooking.rest;


import java.util.List;

import com.project.flightbooking.domain.Flight;
import com.project.flightbooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
@CrossOrigin
public class FlightRestController
{
    @Autowired
    private FlightRepository data;

    /**
     * GET LIST OF ALL FLIGHTS
     * @return
     */
    @RequestMapping("/flights")
    public FlightList allVehicles()
    {
        List<Flight> all = data.findAll();
        return new FlightList(all);
    }


    /**
     * Add new flight to the database
     * @param flight
     * @return
     */
    @RequestMapping(value="/flights", method=RequestMethod.POST)
    public ResponseEntity<Flight> createANewFlight(@RequestBody Flight flight)
    {
        data.save(flight);
        return new ResponseEntity<Flight>(flight,HttpStatus.CREATED);
    }
}