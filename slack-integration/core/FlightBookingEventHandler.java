
@Component
public class FlightBookingEventHanler{

    @Autowired
    private Slack slack;

    // function 

    public void handleFlightBookEvent(FlightBookingEvent e){
        String passedMessage = "";

        slack.postMessge(passedMessage);
    '

    }

}