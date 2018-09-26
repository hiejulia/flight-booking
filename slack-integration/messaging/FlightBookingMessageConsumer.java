@Component
public class FlightBookingMessageConsumer {

    @Autowired 
    private ObjectMapper objectMapper:


    @Autowired
    private FlightBookingEventHandler FlightBookingEventHandler;

    @Override 
    public void onMessage(Message message){
        try {

            FlightBookingEvent flightBookEvent = objectMapper.readValue(message.body(),FlightBookingEvent.class);
            FlightBookingEventHandler.handleFligthBooking(flightBookEvent);
        } catch(Exception e ){
            // log.error
            e.printStackTrace();

        }
    }


}