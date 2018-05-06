package com.project.flightbooking.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.flightbooking.dao.RouteDao;
import com.project.flightbooking.domain.Flight;
import com.project.flightbooking.dto.Position;
import com.project.flightbooking.repository.FlightRepository;
import com.project.flightbooking.service.PositionTrackingExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;


@Controller
@Transactional
@RequestMapping("/website/flights")
public class FlightController
{
    @Autowired
    private FlightRepository data;

    @Autowired
    private RouteDao routeDao;

    @Autowired
    private PositionTrackingExternalService externalService;

    // Here we replace DiscoveryClient with the loadBalancer Client, still call Eureka, this comes in place when there is more than one instance this will call chose one of the instances using round robin algorithm.
	/*@Autowired
	private DiscoveryClient discoveryClient;*/

    @RequestMapping(value="/flight/{name}")
    public void showFlightByName(@PathVariable("name") String name)
    {
        Flight flight = data.findByName(name);
        // get the position of the flight by name
        Position lastestPosition=externalService.getLatestPositionFromRemoteMicroService(name);
        //If Successful, then update in our database;

        if(lastestPosition.isUpToDate()){
            flight.setLat(lastestPosition.getLat());
            flight.setLongitude(lastestPosition.getLongitude());
            flight.setLastRecordedPosition(lastestPosition.getTimestamp());
        }
    }

    @RequestMapping(value="/flight/{from}/{to}")
    public Flight get(@PathParam("from") String from,
                        @PathParam("to") String to) {
        return routeDao.findAirlines(from, to);
    }
}