package com.project.flightbooking.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
public class TwitterStreamListener implements StreamListener {

    @Inject
    private Twitter twitter;

    @Inject
    DataService dataService;

    @Inject
    private ThreadPoolTaskExecutor taskExecutor;

    @Value("${taskExecutor.twitterProcessing.enabled}")
    private boolean processingEnabled;

    private BlockingQueue<Tweet> queue = new ArrayBlockingQueue<>(20);

    public void run() {
        List<StreamListener> listeners = new ArrayList<>();
        listeners.add(this);
        twitter.streamingOperations().sample(listeners);
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        if (processingEnabled) {
            for (int i = 0; i < taskExecutor.getMaxPoolSize(); i++) {
                taskExecutor.execute(new TweetProcessor(queue, dataService));
            }

            run();
        }
    }

    @Override
    public void onTweet(Tweet tweet) {

        queue.offer(tweet);
    }

    @Override
    public void onDelete(StreamDeleteEvent deleteEvent) {
    }

    @Override
    public void onLimit(int numberOfLimitedTweets) {
    }

    @Override
    public void onWarning(StreamWarningEvent warningEvent) {
    }
}