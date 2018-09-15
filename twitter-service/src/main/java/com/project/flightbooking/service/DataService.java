package com.project.flightbooking.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.thinksky.twitter.model.Entities;
import com.thinksky.twitter.model.Tweet;
import com.thinksky.twitter.model.TwitterProfile;
import com.thinksky.twitter.repository.EntitiesRepository;
import com.thinksky.twitter.repository.TweetRepository;
import com.thinksky.twitter.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
@Slf4j
public class DataService {

    private static final String HYSTRIX_FALL_BACK = "saveTweetFallback";
    private static final String HYSTRIX_COMMAND_KEY = "save-tweet";
    private static final String HYSTRIX_GROUP_KEY = "search-service";
    int index = 1;

    @Inject
    private TweetRepository tweetRepository;

    @Inject
    private EntitiesRepository entitiesRepository;

    @Inject
    private UserRepository twitterProfileRepository;

    @Inject
    private MongoTemplate mongoTemplate;

    @HystrixCommand(fallbackMethod = HYSTRIX_FALL_BACK,
            commandKey = HYSTRIX_COMMAND_KEY,
            groupKey = HYSTRIX_GROUP_KEY)
    public void saveTweet(org.springframework.social.twitter.api.Tweet tweet) {

//        try {
        Tweet mTweet = covertTweet(tweet);

        tweetRepository.save(mTweet);
        entitiesRepository.save(mTweet.getEntities());
        twitterProfileRepository.save(mTweet.getUser());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private Tweet covertTweet(org.springframework.social.twitter.api.Tweet tweet) {

        Mapper mapper = new DozerBeanMapper();

        Tweet tweetNew = mapper.map(tweet, Tweet.class);
        tweetNew.setId(index++);

        Entities entities = mapper.map(tweet.getEntities(), Entities.class);
//        entities.setId(index++);
        tweetNew.setEntities(entities);

        TwitterProfile user = mapper.map(tweet.getUser(), TwitterProfile.class);
        user.setId(index++);
        tweetNew.setUser(user);

        return tweetNew;
    }

    private void saveTweetFallback(org.springframework.social.twitter.api.Tweet tweet,
                                   Throwable e) {
        log.info("Hystrix fallback for saveTweet, error={}, cause={}", e.getMessage(), e.getCause());
    }
}