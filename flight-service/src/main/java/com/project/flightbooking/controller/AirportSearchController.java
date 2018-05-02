package com.project.flightbooking.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

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
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate);

        return "home";
    }

    @RequestMapping(value = "/flights", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody QpxExpressFlightSearchResult search(@RequestBody FlightSearchInput input) {

        flightSearchService.setFlightSearchInput(input);
        QpxExpressFlightSearchResult flightResults = flightSearchService.search();
        return flightResults;
    }

    @RequestMapping(value = "/airportIataCode", method = RequestMethod.GET)
    public @ResponseBody AirportIata airportIataCode(@RequestParam("lat") String lat, @RequestParam("log") String log) {

        return airportIataSearchService.search(lat, log);
    }



}
