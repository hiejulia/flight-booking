
import demo.domain.Payment;
import demo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Send result of processPaymentOrder to Flight-Booking-Payment-Service
 */
@Service
@Slf4j
public class PaymentService{
    private static final String PAYMENT_STATUS_SUCCESS = "Success";

    private RestTemplate restTemplate = new RestTemplate();



    @Value("${com.flight-booking.flight.payment}")
    private String bookingPayment;

    // Process payment order 
    public void processPaymentOrder(Payment payment) {
        payment.setPaymentStatus(PAYMENT_STATUS_SUCCESS);

        // send updated paymentStatus to flight-booking-payment-service
        this.restTemplate.put(bookingPayment + "/api/payment", payment);
    }
}