package com.project.flightbooking.config;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppConfig {
    private static Properties AppProperties;
    public static int WORKER_POOL = 100;


    // Find your Account Sid and Token at twilio.com/user/account
    public static String TWILIO_ACCOUNT_SID = "";
    public static String TWILIO_AUTH_TOKEN = "";
    public static String TWILIO_FROM = "";
    public static String TWILIO_TO = "";

    public static String KAFKA_PEERS = "localhost:9092";
    public static String KAFKA_TOPICS = "mqtt.data";
    public static String KAFKA_GROUP_ID = "alert-consumer";
    public static String KAFKA_ENABLE_AUTO_COMMIT = "false";
    public static String KAFKA_AUTO_COMMIT_INTERVAL_MS = "1000";
    public static String KAFKA_SESSION_TIMEOUT_MS = "30000";
    public static String KAFKA_KEY_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
    public static String KAFKA_VALUE_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
    public static int KAFKA_MAX_POLL_RECORDS = 80;

    private static final Logger LOGGER = LogManager.getLogger();

    public AppConfig() {
    }

    public void initConfig(String appConfig) {
        LOGGER.debug(String.format("Loading configuration from \"%s\"", appConfig));
        AppProperties = new Properties();

        try {
            FileInputStream in = new FileInputStream(appConfig);
            AppProperties.load(in);
            in.close();

            WORKER_POOL = Integer.parseInt(getProperty("app.worker.pool"));


            TWILIO_ACCOUNT_SID = getProperty("twilio.account.sid");
            TWILIO_AUTH_TOKEN = getProperty("twilio.auth.token");
            TWILIO_FROM = getProperty("twilio.from");
            TWILIO_TO = getProperty("twilio.to");

            KAFKA_PEERS = getProperty("kafka.peers");
            KAFKA_TOPICS = getProperty("kafka.topics");
            KAFKA_GROUP_ID = getProperty("kafka.group.id");
            KAFKA_ENABLE_AUTO_COMMIT = getProperty("kafka.enable.auto.commit");
            KAFKA_AUTO_COMMIT_INTERVAL_MS = getProperty("kafka.auto.commit.interval.ms");
            KAFKA_SESSION_TIMEOUT_MS = getProperty("kafka.session.timeout.ms");
            KAFKA_KEY_DESERIALIZER = getProperty("kafka.key.deserializer");
            KAFKA_VALUE_DESERIALIZER = getProperty("kafka.value.deserializer");
            KAFKA_MAX_POLL_RECORDS = Integer.parseInt(getProperty("kafka.max.pool.records"));
        } catch (Exception e) {
            LOGGER.error("Invalid configuration file");
            e.printStackTrace();
            System.exit(-1);
        }

        if (AppProperties == null) {
            LOGGER.error("Invalid Configuration: properties are not set");
            System.exit(-1);
        } else {
            LOGGER.debug("Configuration successfully loaded");
        }
    }

    private static String getProperty(String property) {
        try {
            return AppProperties.getProperty(property).trim();
        } catch (Exception e) {
            LOGGER.error("Cannot read config file", e);
            // e.printStackTrace();
            return "";
        }
    }
}