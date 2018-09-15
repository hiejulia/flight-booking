package com.project.flightbooking.main;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.oemdaro.kafka.configuring.AppConfig;

public class Worker implements Runnable {
    private ConsumerRecord<String, String> record;
    private Object model;

    private static final Logger LOGGER = LogManager.getLogger();

    Worker(ConsumerRecord<String, String> record, Object model) {
        this.record = record;
        this.model = model;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (Exception e) {
            // e.printStackTrace();
            LOGGER.error("Unexpected error ", e);
        }
    }

    private void process() {
        if (model.getClass() == Data.class) {
            LOGGER.debug(String.format("Process data. Topic = %s, Offset = %d", record.topic(), record.offset()));
            Data data = (Data) model;
            Double t = Double.parseDouble(data.getAlertTopic());
            Double h = Double.parseDouble(data.getAlertMessage());
            Twilio.init(AppConfig.TWILIO_ACCOUNT_SID, AppConfig.TWILIO_AUTH_TOKEN);

            if (t > AppConfig.TEMPERATURE_THRESHOLD) {
                LOGGER.debug("Alert user");
                MessageCreator messageCreator = Message.creator(new PhoneNumber(AppConfig.TWILIO_TO),
                        new PhoneNumber(AppConfig.TWILIO_FROM),
                        String.format("WARNING! Temerature too high! Temperature: %s", data.getTemperature()));
                Message message = messageCreator.create();
                LOGGER.debug(String.format("Message has been delivered. Message SID: %s", message.getSid()));
            }

            if (h > AppConfig.HUMIDITY_THRESHOLD) {
                LOGGER.debug("Alert user");
                MessageCreator messageCreator = Message.creator(new PhoneNumber(AppConfig.TWILIO_TO),
                        new PhoneNumber(AppConfig.TWILIO_FROM),
                        String.format("WARNING! Humidity too high! Humidity: %s", data.getHumidity()));
                Message message = messageCreator.create();
                LOGGER.debug(String.format("Message has been delivered. Message SID: %s", message.getSid()));
            }

            return;
        }
    }
}