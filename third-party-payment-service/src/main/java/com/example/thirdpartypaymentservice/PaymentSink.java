
import demo.domain.Payment;
import demo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;


// 
@EnableBinding(Sink.class)
@Slf4j
public class PaymentSink {


    // Payment service 
    @Autowired
    private PaymentService paymentService;

    // consume msg from RabbitMQ
    @ServiceActivator(inputChannel = Sink.INPUT)
    public void receivePaymentOrderFromMQ(Payment paymentOrder) {
        log.info("PaymentOrder input in third party payment " + paymentOrder.toString());

        // Send result of processPaymentOrder to Flight booking payment service
        paymentService.processPaymentOrder(paymentOrder);
    }
}