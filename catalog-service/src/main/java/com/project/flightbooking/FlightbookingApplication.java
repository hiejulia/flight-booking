package com.project.flightbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Controller
public class FlightbookingApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(FlightbookingApplication.class);

	@Autowired 
	private DiscoveryClient DiscoveryClient;


	@GetMapping("/ping")
	public List<ServiceInstance> ping() {
	 List<ServiceInstance> instances = discoveryClient.getInstances("catalog-service");
	 LOGGER.info("INSTANCES: count={}", instances.size());
	 instances.stream().forEach(it -> LOGGER.info("INSTANCE: id={}, port={}", it.getServiceId(), it.getPort()));
	 return instances;
	}

	public static void main(String[] args) {
		SpringApplication.run(FlightbookingApplication.class, args);
	}
}
