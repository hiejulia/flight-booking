package com.project.flightbooking.service;


import com.project.flightbooking.domain.Flight;
import com.project.flightbooking.dto.Position;
import com.project.flightbooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PositionTrackingExternalService {

    @Autowired
    private FlightRepository repository;
    @Autowired
    private RemotePositionMicroserviceCalls remoteService;

    @HystrixCommand(fallbackMethod="handleExternalServiceDown")
    public Position getLatestPositionFromRemoteMicroService(String name){
        Position response= remoteService.getLatestPositionForVehicle(name);// call the remote service with the flight name
        response.setUpToDate(true);
        return response;

    }

    public Position handleExternalServiceDown(String name){
        // Fall back method
        // Read the last known position for this Vehicle from the cache that was saved in database before

        Position position= new Position();
        Flight vehicle =repository.findByName(name);
        position.setLat(vehicle.getLat());
        position.setLongitude(vehicle.getLongitude());
        position.setTimestamp(vehicle.getLastRecordedPosition());
        position.setUpToDate(false);


        return position;

    }



}