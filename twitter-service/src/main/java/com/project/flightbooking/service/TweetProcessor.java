package com.project.flightbooking.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.Tweet;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class TweetProcessor implements Runnable {

    DataService dataService;

    private static final Pattern HASHTAG_PATTERN = Pattern.compile("#\\w+");

    private final BlockingQueue<Tweet> queue;

    public TweetProcessor() {
        queue = new ArrayBlockingQueue<>(20);
    }

    public TweetProcessor(BlockingQueue<Tweet> queue, DataService dataService) {
        this.queue = queue;
        this.dataService = dataService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Tweet tweet = queue.take();
                processTweet(tweet);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processTweet(Tweet tweet) {
        String lang = tweet.getLanguageCode();
        //String text = tweet.getText();
        // filter non-English tweets:
        if (!"en".equals(lang)) {
            return;
        }

        //Set<String> hashTags = hashTagsFromTweet(text);

        // filter tweets without hashtags:
//        if (hashTags.isEmpty()) {
//            return;
//        }

        log.info("\n Tweet: " + tweet.getText() + "\n");

        dataService.saveTweet(tweet);

    }

    private static Set<String> hashTagsFromTweet(String text) {
        Set<String> hashTags = new HashSet<>();
        Matcher matcher = HASHTAG_PATTERN.matcher(text);
        while (matcher.find()) {
            String handle = matcher.group();
            // removing '#' prefix
            hashTags.add(handle.substring(1));
        }
        return hashTags;
    }
}