package com.project.flightbooking;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.project.flightbooking.domain.Airport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FlightAirportServiceAPI {

    private static final Logger LOG = LoggerFactory.getLogger(FlightAirportServiceAPI.class);

    //@Qualifier("userInfoRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    DiscoveryClient client;

    @RequestMapping("/service")
    public List<String> home() {
        return client.getServices();
    }

    @RequestMapping("/airports/{id}")
    @HystrixCommand(fallbackMethod = "defaultAiport")
    public ResponseEntity<Airport> getAirport(
            @PathVariable("id") String id) {
        MDC.put("airportId", id);
        String url = "http://airport-service/v1/airports/" + id;
        LOG.debug("GetAirport from URL: {}", url);

        ResponseEntity<Airport> result = restTemplate.getForEntity(url, Airport.class);
        LOG.info("GetAirport http-status: {}", result.getStatusCode());
        LOG.debug("GetAirport body: {}", result.getBody());

        return new ResponseEntity<>(result.getBody(), HttpStatus.OK);
    }

    /**
     * Fetch restaurants with the specified name. A partial case-insensitive
     * match is supported. So <code>http://.../restaurants/rest</code> will find
     * any restaurants with upper or lower case 'rest' in their name.
     *
     * @param name
     * @return A non-null, non-empty collection of restaurants.
     */
    @HystrixCommand(fallbackMethod = "defaultRestaurants")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Airport>> findByName(@RequestParam("name") String name) {
        LOG.info(String.format("api-service findByName() invoked:{} for {} ", "v1/restaurants?name=", name));
        MDC.put("restaurantId", name);
        String url = "http://restaurant-service/v1/restaurants?name=".concat(name);
        LOG.debug("GetRestaurant from URL: {}", url);
        Collection<Airport> restaurants;
        ResponseEntity<Collection> result = restTemplate.getForEntity(url, Collection.class);
        LOG.info("GetRestaurant http-status: {}", result.getStatusCode());
        LOG.debug("GetRestaurant body: {}", result.getBody());

        return new ResponseEntity<>(result.getBody(), HttpStatus.OK);
    }

    /**
     * Fallback method for getAirport()
     *
     * @param restaurantId
     * @return
     */
    public ResponseEntity<Airport> defaultRestaurant(
            @PathVariable int restaurantId) {
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }

    /**
     * Fallback method for findByName()
     *
     * @param input
     * @return
     */
    public ResponseEntity<Collection<Airport>> defaultAirports(String input) {
        LOG.warn("Fallback method for user-service is being used.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
