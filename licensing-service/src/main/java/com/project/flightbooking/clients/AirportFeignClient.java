package com.project.flightbooking.clients;



import com.project.flightbooking.model.Airport;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// FeignClient
@FeignClient("flight-service")
public interface AirportFeignClient {
    // Get One airport by id
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/airports/{id}",
            consumes = "application/json")
    Airport getAirport(@PathVariable("id") Long id );
}