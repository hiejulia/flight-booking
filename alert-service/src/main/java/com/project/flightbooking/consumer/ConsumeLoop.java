package com.project.flightbooking.consumer;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ConsumeLoop implements Runnable {
    private final KafkaConsumer<String, String> consumer;
    private final List<String> topics;
    private final CountDownLatch shutdownLatch;

    private static final Logger LOGGER = LogManager.getLogger();

    public ConsumeLoop(KafkaConsumer<String, String> consumer, List<String> topics) {
        this.consumer = consumer;
        this.topics = topics;
        this.shutdownLatch = new CountDownLatch(1);
    }

    public abstract void process(ConsumerRecord<String, String> record);

    private void doCommitSync(ConsumerRecord<String, String> record) {
        try {
            // Commit Kafka at exact location for record, and only this record.
            final TopicPartition recordTopicPartition = new TopicPartition(record.topic(), record.partition());
            final Map<TopicPartition, OffsetAndMetadata> commitMap = Collections.singletonMap(recordTopicPartition,
                    new OffsetAndMetadata(record.offset() + 1));
            consumer.commitSync(commitMap);
        } catch (WakeupException e) {
            // we're shutting down, but finish the commit first and then
            // re-throw the exception so that the main loop can exit
            doCommitSync(record);
            throw e;
        } catch (CommitFailedException ex) {
            LOGGER.error("Failed to commit sync to log", ex);
        }
    }

    public void run() {
        try {
            consumer.subscribe(topics);

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                records.forEach(record -> {
                    process(record);
                    doCommitSync(record);
                });
            }
        } catch (WakeupException e) {
            // ignore, we're closing
        } catch (Exception ex) {
            LOGGER.error("Unexpected error", ex);
        } finally {
            LOGGER.debug("Closing consumer");
            consumer.close();
            shutdownLatch.countDown();
        }
    }

    public void shutdown() throws InterruptedException {
        consumer.wakeup();
        shutdownLatch.await();
    }
}