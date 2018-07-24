package com.project.flightbooking.clients;

@Component
public class PassengerClient {
        private final Logger log = LoggerFactory.getLogger(PassengerClient.class);
    
        private RestTemplate restTemplate;
        
        private boolean useRibbon;
        private LoadBalancerClient loadBalancer;
    
        @Autowired
        public PassengerClient(
            @Value("${ribbon.eureka.enabled:false}") boolean useRibbon) {
            super();
            this.restTemplate = getRestTemplate();
            this.useRibbon = useRibbon;
        }
    
        // Load balancer
        @Autowired(required = false)
        public void setLoadBalancer(LoadBalancerClient loadBalancer) {
            this.loadBalancer = loadBalancer;
        }

        /**
         * Check for valid passenger id 
         */
        public boolean isValidPassengerId(long id) {
            RestTemplate restTemplate = new RestTemplate();
            try {
                ResponseEntity<String> entity = restTemplate.getForEntity(
                        passengerURL() + id, String.class);
                return entity.getStatusCode().is2xxSuccessful();
            } catch (final HttpClientErrorException e) {
                if (e.getStatusCode().value() == 404)
                    return false;
                else
                    throw e;
            }
        }
    
        protected RestTemplate getRestTemplate() {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            mapper.registerModule(new Jackson2HalModule());
    
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Arrays.asList(MediaTypes.HAL_JSON));
            converter.setObjectMapper(mapper);
    
            return new RestTemplate(
                    Collections.<HttpMessageConverter<?>>singletonList(converter));
        }
    
        /**
         * Get all passengers 
         *
         */
        public Collection<Passenger> findAll() {
            PagedResources<Passenger> pagedResources = getRestTemplate()
                    .getForObject(passengerURL(), PassengerPagedResources.class);
            return pagedResources.getContent();
        }
    
        private String customerURL() {
            String url;
            if (useRibbon) {
                ServiceInstance instance = loadBalancer.choose("CUSTOMER");
                url = String.format("http://%s:%s/customer/", instance.getHost(), instance.getPort());
            } else {
                url = String.format("http://%s:%s/customer/", customerServiceHost, customerServicePort);
            }
            log.trace("Customer: URL {} ", url);
            return url;
    
        }
    
        /**
         * Get one passenger 
         */
        public Passenger getOne(long id) {
            return restTemplate.getForObject(passengerURL() + id,
                    Passenger.class);
        }
    
}