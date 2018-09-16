public class EventBusPublisher {
    
        /** Logger */
        private static final Logger LOGGER = LoggerFactory.getLogger(EventBusPublisher.class);
    
        @Autowired
        private RabbitTemplate template;
    
        @Autowired
        private DirectExchange eventBusExchange;
    
        /**
         * Publishes an event to the event bus
         *
         * @param  event the event data
         * @throws EventBusPublisherException when an error occurred publishing
         */
        public void publish(DomainEvent event) throws EventBusPublisherException {
    
            String exchange = eventBusExchange.getName();
            String eventKey = event.eventName();
            String eventData = EventSerializer.serialize(event);
    
            LOGGER.info(
                    "Event Bus Publisher - Publishing an event:" +
                    "\n\tExchange=\"" + exchange + "\"" +
                    "\n\tKey=\"" + eventKey + "\"" +
                    "\n\tData=\"" + eventData + "\""
            );
    
            try {
                template.convertAndSend(exchange, eventKey, eventData);
            } catch (AmqpException exc) {
                String errorMsg = "Event Bus Publisher - Error occurred publishing an event!";
                LOGGER.debug(errorMsg, exc);
                throw new EventBusPublisherException(errorMsg, exc);
            }
        }
    }