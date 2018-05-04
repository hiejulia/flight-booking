package com.project.flightbooking.service;


import com.project.flightbooking.dto.Position;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



// Rest call

@FeignClient(name= "fleetman-position-tracker") // FeignClient - fleetman-position-tracker
public interface RemotePositionMicroserviceCalls {

    // REST call - Feign is automatically use the REST template
    // in this case - the method will call first and we are sending the request in the normal case this is the opposite way

    @RequestMapping(method=RequestMethod.GET,value="/flights/{name}")
    public Position getLatestPositionForVehicle(@PathVariable("name") String name);


}