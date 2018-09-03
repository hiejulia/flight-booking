
import eu.dreamix.ms.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Sender
 */
public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    // Autowired : KafkaTemplate 
    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public void send(String topic, User payload) {
        kafkaTemplate.send(topic, payload);// send 
    }
}