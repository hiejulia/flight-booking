package com.project.flightbooking.service.search;


import com.project.flightbooking.dto.FlightSearchInput;
import com.project.flightbooking.dto.QpxExpressFlightSearchResult;

public interface QpxExpressFlightSearchService {

    // Search QpxExpressFlightSearchResult
    public QpxExpressFlightSearchResult search();

    // set flight search input
    public void setFlightSearchInput(FlightSearchInput input);
}