package com.project.flightbooking.service.search;


import com.project.flightbooking.domain.Airport;
import com.project.flightbooking.dto.AirportIata;
import org.springframework.stereotype.Service;

import java.io.IOException;

import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AirportSearchByGeoServiceImpl implements AirportSearchByGeoService{

    static private String url;

    public AirportSearchByGeoServiceImpl() {

    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public AirportIata search(String lat, String log) {
        ObjectMapper mapper = new ObjectMapper();

        AirportIata airportIata = null;

        try {
            // query for airport iata through restful call
            // Query for IATA through Restful call
            RestTemplate restTemplate = new RestTemplate();
            String results = restTemplate.getForObject(url, String.class, lat, log);

            // convert json to AirportIata object
            airportIata = mapper.readValue(results, AirportIata.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return airportIata;

    }
}
