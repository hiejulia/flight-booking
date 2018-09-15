package com.project.flightbooking.service;


import com.thinksky.search.Feign.TweetClient;
import com.thinksky.search.model.Tweet;
import com.thinksky.search.repository.TweetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.PagedResources;

@Slf4j
public class PusherProcessor implements Runnable {

    private long pageIndex;
    private TweetRepository tweetRepository;
    private TweetClient tweetClient;

    public PusherProcessor(TweetRepository tweetRepository, TweetClient tweetClient, long pageIndex) {

        this.pageIndex = pageIndex;
        this.tweetRepository = tweetRepository;
        this.tweetClient = tweetClient;
    }

    @Override
    public void run() {
        try {
            PagedResources<Tweet> tweetsResource = tweetClient.getTweetsByPage(pageIndex);

            tweetsResource.iterator().forEachRemaining(tweet -> {
                tweetRepository.save(tweet);
                log.info("SEARCH-SERVICE push: " + tweet.getText());
            });


            log.info(String.format("Total Page#: [%d] \n" +
                            "Size of Page: %d \n" +
                            "Number of Page: %d \n",
                    tweetsResource.getMetadata().getTotalPages(),
                    tweetsResource.getMetadata().getSize(),
                    tweetsResource.getMetadata().getNumber()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}