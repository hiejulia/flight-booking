
import com.privalia.poc.eventbus.basket.core.DomainEvent;
import com.privalia.poc.eventbus.basket.event.UserCreated;
import com.privalia.poc.eventbus.basket.event.UserUpdated;
import com.privalia.poc.eventbus.basket.helper.EventDeserializer;
import com.privalia.poc.eventbus.basket.service.UserCreatedOrUpdatedHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class EventBusConsumer {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(EventBusConsumer.class);

    @Autowired
    private UserCreatedOrUpdatedHandler userCreatedOrUpdatedHandler;

    /**
     * RabbitMQ consumer - called whenever an event arrives
     *
     * @param rawEvent the raw data of the event
     * @throws EventBusConsumerException when an erroro occurred
     */
    @RabbitListener(queues = "#{eventBusQueue.name}")
    public void consume(String rawEvent) throws EventBusConsumerException{

        LOGGER.info(
                "Event Bus Consumer - Consuming an event." +
                "\n\tRaw data=\"" + rawEvent + "\""
        );

        DomainEvent event = null;
        try {
            event = decodeEvent(rawEvent);
        } catch (IOException exc) {
            LOGGER.error("Event Bus Consumer - Error occurred decoding the event received!", exc);
            return;
        }

        String eventName = event.eventName();
        if (eventName.equals(UserCreated.bindingName())) {

            // Handle UserCreated event
            UserCreated userCreatedEvent = new UserCreated(event);
            userCreatedOrUpdatedHandler.handle(userCreatedEvent);
        } else if (eventName.equals(UserCreated.bindingName())) {

            // Handle UserUpdated event
            UserUpdated userUpdatedEvent = new UserUpdated(event);
            userCreatedOrUpdatedHandler.handle(userUpdatedEvent);
        } else {

            // Error: Unrecognised event
            LOGGER.error("Event Bus Consumer - Unrecognised event: \"" + eventName + "\"!");
            return;
        }
    }

    /**
     * Decodes a received event
     *
     * @param rawEvent a string containing the raw event data
     * @return a RawEvent object with the event data
     * @throws IOException when error deserializing
     */
    private DomainEvent decodeEvent(String rawEvent) throws IOException {
        return EventDeserializer.deserialize(rawEvent);
    }
}