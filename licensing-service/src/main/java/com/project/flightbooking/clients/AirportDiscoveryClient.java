package com.project.flightbooking.clients;



import com.project.flightbooking.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class AirportDiscoveryClient {

    // Discovery Client
    @Autowired
    private DiscoveryClient discoveryClient;

    // Get Airport
    public Airport getAirport(Long id ) {
        // Rest template
        RestTemplate restTemplate = new RestTemplate();

        List<ServiceInstance> instances = discoveryClient.getInstances("flight-service");

        if (instances.size() == 0) return null;

        String serviceUri = String.format("%s/v1/airports/%s", instances.get(0).getUri().toString(), id);


        ResponseEntity<Airport> restExchange =
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, Airport.class, id);

        return restExchange.getBody();// return getBody
    }

}