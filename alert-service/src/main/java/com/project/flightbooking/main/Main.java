package com.project.flightbooking.main;


import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.oemdaro.kafka.configuring.AppConfig;

/**
 * Main
 */
public class Main extends Thread {
    private static final Logger LOGGER = LogManager.getLogger();

    private Main(String[] args) {
        String configFile;
        configFile = System.getProperty("app.configurationFile");
        if (configFile == null) {
            configFile = ClassLoader.getSystemResource("app.properties").getPath();
        }

        AppConfig appConfig = new AppConfig();
        appConfig.initConfig(configFile);

        Properties props = new Properties();
        props.put("bootstrap.servers", AppConfig.KAFKA_PEERS);
        props.put("group.id", AppConfig.KAFKA_GROUP_ID);
        props.put("enable.auto.commit", AppConfig.KAFKA_ENABLE_AUTO_COMMIT);
        props.put("auto.commit.interval.ms", AppConfig.KAFKA_AUTO_COMMIT_INTERVAL_MS);
        props.put("session.timeout.ms", AppConfig.KAFKA_SESSION_TIMEOUT_MS);
        props.put("key.deserializer", AppConfig.KAFKA_KEY_DESERIALIZER);
        props.put("value.deserializer", AppConfig.KAFKA_VALUE_DESERIALIZER);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, AppConfig.KAFKA_MAX_POLL_RECORDS);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        Action action = new Action(consumer);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.debug("Graceful shutting down");
            try {
                action.shutdown();
            } catch (InterruptedException e) {
                LOGGER.error("InterruptedException ", e);
            }

        }));

        Thread thread = new Thread(action);
        thread.start();
    }

    public static void main(String[] args) {
        new Main(args);
    }
}