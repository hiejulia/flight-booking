package com.project.flightbooking.clients;


import com.project.flightbooking.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AirportRestTemplateClient {
    // Rest template
    @Autowired
    RestTemplate restTemplate;

    public Airport getAirport(String id) {
        ResponseEntity<Airport> restExchange =
                restTemplate.exchange(
                        "http://flight-service/v1/airports/{id}",
                        HttpMethod.GET,
                        null, Airport.class, id);

        return restExchange.getBody();
    }
}