package com.project.flightbooking.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



@Controller
@Transactional
@RequestMapping("/website/flights")
public class FlightController
{
    @Autowired
    private FlightRepository data;

    @Autowired
    private PositionTrackingExternalService externalService;

    // Here we replace DiscoveryClient with the loadBalancer Client, still call Eureka, this comes in place when there is more than one instance this will call chose one of the instances using round robin algorithm.
	/*@Autowired
	private DiscoveryClient discoveryClient;*/

    @RequestMapping(value="/newFlight.html",method=RequestMethod.POST)
    public String newVehicle(Flight flight)
    {
        data.save(flight);
        return "redirect:/website/flights/list.html";
    }

    @RequestMapping(value="/deleteFlight.html", method=RequestMethod.POST)
    public String deleteFlight(@RequestParam Long id)
    {
        data.delete(id);
        return "redirect:/website/flights/list.html";
    }

    @RequestMapping(value="/newFlight.html",method=RequestMethod.GET)
    public ModelAndView renderNewFlightForm()
    {
        Flight newFlight = new Flight();
        return new ModelAndView("newFlight","form",newFlight);
    }

    @RequestMapping(value="/list.html", method=RequestMethod.GET)
    public ModelAndView flights()
    {
        List<Flight> allFlights = data.findAll();
        return new ModelAndView("allFlights", "flights", allFlights);
    }

    @RequestMapping(value="/flight/{name}")
    public ModelAndView showFlightByName(@PathVariable("name") String name)
    {
        Flight flight = data.findByName(name);

		/*
		 * Move this block of code to Different class for the Hystrix
		*/

        Position lastestPosition=externalService.getLatestPositionFromRemoteMicroService(name);
        //If Successful, then update in our database;

        if(lastestPosition.isUpToDate()){
            flight.setLat(lastestPosition.getLat());
            flight.setLongitude(lastestPosition.getLongitude());
            flight.setLastRecordedPosition(lastestPosition.getTimestamp());

        }

        Map<String,Object> model = new HashMap<>();
        model.put("flight", flight);
        model.put("position", lastestPosition);
        return new ModelAndView("flightInfo", "model",model);
    }

}