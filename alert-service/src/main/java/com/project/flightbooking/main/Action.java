package com.project.flightbooking.main;


import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.Gson;
import io.github.oemdaro.kafka.configuring.AppConfig;
import io.github.oemdaro.kafka.consumer.ConsumeLoop;

/**
 * Action
 */
public class Action extends ConsumeLoop {
    private ExecutorService executor;

    private static final Logger LOGGER = LogManager.getLogger();

    Action(KafkaConsumer<String, String> consumer) {
        super(consumer, Arrays.asList(AppConfig.KAFKA_TOPICS.split(",")));
        executor = Executors.newFixedThreadPool(AppConfig.WORKER_POOL); // create number of worker on job
    }

    public void process(ConsumerRecord<String, String> record) {
        LOGGER.debug(String.format("Topic = %s, Offset = %d, Key = %s, Value = %s", record.topic(), record.offset(),
                record.key(), record.value()));

        Gson gson = new Gson();
        if (record.topic().equalsIgnoreCase("mqtt.data")) {
            Data data = gson.fromJson(record.value(), Data.class);
            Runnable worker = new Worker(record, data);
            executor.execute(worker);
            return;
        }

        LOGGER.warn("Unhandled Topic: " + record.topic());
    }
}