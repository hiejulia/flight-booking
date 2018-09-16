
import com.privalia.poc.eventbus.basket.event.UserCreated;
import com.privalia.poc.eventbus.basket.event.UserUpdated;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EventBusConfig {
    
    /** Exchange name read from application.yml */
    @Value("${eventbus.objects.exchange}")
    private String exchangeName;

    /** Queue name read from application.yml */
    @Value("${eventbus.objects.queue}")
    private String queueName;

    /** Declaration of the exchange object */
    @Bean
    public DirectExchange eventBusExchange() {
        return new DirectExchange(exchangeName, true, false);
    }

    /** Declaration of the queue object */
    @Bean
    public Queue eventBusQueue() {
        return new Queue(queueName, true, false, false);
    }

    /** Bind exchange to the queue with the event names as keys  */
    private static class EventBusBindingsConfig {
        @Bean
        public Binding eventBusBindingUserCreated(DirectExchange eventBusExchange, Queue eventBusQueue) {
            return BindingBuilder.bind(eventBusQueue).to(eventBusExchange).with((UserCreated.bindingName()));
        }

        @Bean
        public Binding eventBusBindingUserUpdated(DirectExchange eventBusExchange, Queue eventBusQueue) {
            return BindingBuilder.bind(eventBusQueue).to(eventBusExchange).with((UserUpdated.bindingName()));
        }
    }

    /** The event publisher object */
    @Bean
    public EventBusPublisher eventBusPublisher() {
        return new EventBusPublisher();
    }

    /** The event consumer obects (workers) */
    private static class EventBusConsumerConfig {
        @Bean
        public EventBusConsumer eventBusConsumerWorker1() {
            return new EventBusConsumer();
        }

        @Bean
        public EventBusConsumer eventBusConsumerWorker2() {
            return new EventBusConsumer();
        }
    }
}