package com.project.flightbooking.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import com.project.flightbooking.dto.AirportIata;
import com.project.flightbooking.dto.FlightSearchInput;
import com.project.flightbooking.dto.QpxExpressFlightSearchResult;
import com.project.flightbooking.service.search.AirportSearchByGeoService;
import com.project.flightbooking.service.search.QpxExpressFlightSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
@RequestMapping("/v1/api/flights/search")
public class AirportSearchController {

    private static final Logger logger = LoggerFactory.getLogger(AirportSearchController.class);

    @Autowired
    private QpxExpressFlightSearchService flightSearchService;

    @Autowired
    private AirportSearchByGeoService airportIataSearchService;

    /**
     * search for flight
     * @param input
     * @return
     */
    @RequestMapping(value = "/flights", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    QpxExpressFlightSearchResult search(@RequestBody FlightSearchInput input) {
        // Set search input
        flightSearchService.setFlightSearchInput(input);
        // implement search service
        QpxExpressFlightSearchResult flightResults = flightSearchService.search();
        // return the result
        return flightResults;
    }

    /**
     * Search for airport by lat and long
     * @param lat
     * @param log
     * @return
     */
    @RequestMapping(value = "/airportIataCode", method = RequestMethod.GET)
    public @ResponseBody
    AirportIata airportIataCode(@RequestParam("lat") String lat, @RequestParam("log") String log) {

        return airportIataSearchService.search(lat, log);
    }



}
