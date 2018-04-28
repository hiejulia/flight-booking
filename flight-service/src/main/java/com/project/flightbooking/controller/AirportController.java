package com.project.flightbooking.controller;



import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.project.flightbooking.domain.Airport;
import com.project.flightbooking.domain.Entity;
import com.project.flightbooking.service.AirportService;
import com.project.flightbooking.valueobject.AirportVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/airports")
public class AirportController {


    protected Logger logger = Logger.getLogger(AirportController.class.getName());

    @Autowired
    private AirportService airportService;

    @Autowired
    DiscoveryClient client;

    @RequestMapping("/services")
    public List<String> home() {
        return client.getServices();
    }

    /**
     * Fetch airports with the specified name. A partial case-insensitive
     * match is supported. So <code>http://.../airports/rest</code> will find
     * any airports with upper or lower case 'rest' in their name.
     *
     * @param name
     * @return A non-null, non-empty collection of airports.
     */
    @HystrixCommand(fallbackMethod = "defaultAirports")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Airport>> findByName(@RequestParam("name") String name) {
        logger.info(String.format("airport-service findByName() invoked:{} for {} ", airportService.getClass().getName(), name));
        name = name.trim().toLowerCase();
        Collection<Airport> airports;
        try {
            airports = airportService.findByName(name);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception raised findByName REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return airports.size() > 0 ? new ResponseEntity<>(airports, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Fetch airports with the given id.
     * <code>http://.../v1/airports/{airport_id}</code> will return
     * airport with given id.
     *
     * @param id
     * @return A non-null, non-empty collection of airports.
     */
    @HystrixCommand(fallbackMethod = "defaultAiport")
    @RequestMapping(value = "/{airport_id}", method = RequestMethod.GET)
    public ResponseEntity<Entity> findById(@PathVariable("airport_id") String id) {
        logger.info(String.format("airport-service findById() invoked:{} for {} ", airportService.getClass().getName(), id));
        id = id.trim();
        Entity airport;
        try {
            airport = airportService.findById(id);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised findById REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return airport != null ? new ResponseEntity<>(airport, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Add airport with the specified information.
     *
     * @param airportVO
     * @return A non-null airport.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Airport> add(@RequestBody AirportVO airportVO) {
        Airport airport = new Airport(null, null, null, null);
        BeanUtils.copyProperties(airportVO, airport);
        try {
            airportService.add(airport);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised add Airport REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Fallback method
     *
     * @param input
     * @return
     */
    public ResponseEntity<Entity> defaultAirport(String input) {
        logger.warning("Fallback method for airport-service is being used.");
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    /**
     * Fallback method
     *
     * @param input
     * @return
     */
    public ResponseEntity<Collection<Airport>> defaultAirports(String input) {
        logger.warning("Fallback method for user-service is being used.");
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}