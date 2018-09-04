
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

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

import com.codinglab.flightsearch.dto.AirportIata;
import com.codinglab.flightsearch.dto.FlightSearchInput;
import com.codinglab.flightsearch.dto.QpxExpressFlightSearchResult;
import com.codinglab.flightsearch.service.AirportIataSearchByGeoService;
import com.codinglab.flightsearch.service.QpxExpressFlightSearchService;


@RequestMapping("api")
@RestController
public class FlightSearchController {

	private static final Logger logger = LoggerFactory.getLogger(FlightSearchController.class);

	@Autowired
	private QpxExpressFlightSearchService flightSearchService;

	@Autowired
	private AirportIataSearchByGeoService airportIataSearchService;

    // Search request for flights - 
	@RequestMapping(value = "/flights", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody QpxExpressFlightSearchResult search(@RequestBody FlightSearchInput input) {
		flightSearchService.setFlightSearchInput(input);
		QpxExpressFlightSearchResult flightResults = flightSearchService.search();
		return flightResults;
	}

    // Search request for searching airport IataCode 
	@RequestMapping(value = "/airportIataCode", method = RequestMethod.GET)
	public @ResponseBody AirportIata airportIataCode(@RequestParam("lat") String lat, @RequestParam("log") String log) {
        // Search service 
		return airportIataSearchService.search(lat, log);
	}

}