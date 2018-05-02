package com.project.flightbooking.service.search;


import com.project.flightbooking.domain.Airport;
import com.project.flightbooking.dto.AirportIata;

public interface AirportSearchByGeoService {

    // Search airport by lat and long
    public AirportIata search(String lat, String log);

}