
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;


// Config for message processor 
@Configuration
public class MessageProcessor {
    private Logger logger = LoggerFactory.getLogger(MessageProcessor.class);
    private MessageRepository repository;

    @Autowired
    public void setRepository(MessageRepository repository) {
        this.repository = repository;
    }

    // Integration FLow 
    @Bean
    public IntegrationFlow processMessage() {
        return IntegrationFlows.from("amqpInboundChannel")
                .<com.computerbooth.test.Message, Message>transform(Message::new)
                .<Message>handle((payload, headers) -> repository.save(payload))
                .handle(message -> logger.info("Processed message: {}", message.getPayload()))
                .get();
    }
}