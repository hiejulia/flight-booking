package com.project.flightbooking.client;


import java.util.List;

import com.project.flightbooking.domain.Passenger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "passenger-service")
public interface PassengerClient {

    // Find passenger by organisation
    @GetMapping("/organization/{organizationId}")
    List<Passenger> findByOrganization(@PathVariable("organizationId") Long organizationId);

}