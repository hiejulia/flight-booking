import java.io.IOException;

import org.springframework.web.client.RestTemplate;

import com.codinglab.flightsearch.dto.AirportIata;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


// Search service 
public class AirportIataSearchByGeoServiceImpl implements AirportIataSearchByGeoService {

    // Param : url 
	private String url;

	public AirportIataSearchByGeoServiceImpl() {

	}

	public void setUrl(String url) {
		this.url = url;
	}

    /**
     * 
     * Search main function 
     */
	@Override
	public AirportIata search(String lat, String log) {

		ObjectMapper mapper = new ObjectMapper();

		AirportIata airportIata = null;

		try {
			// query for airport iata through restful call
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