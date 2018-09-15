package com.project.flightbooking.feign;


import com.thinksky.search.model.Tweet;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient("twitter-service")
public interface TweetClient {

    @RequestMapping(value = "/tweet/{id}", method = GET)
    public Tweet getTweet(@PathVariable("id") int id);

    @RequestMapping(value = "/tweet?page={pageIndex}", method = GET)
    public PagedResources<Tweet> getTweetsByPage(@PathVariable("pageIndex") long id);

    @RequestMapping(value = "/tweet", method = GET)
    public PagedResources<Tweet> getTweets();
}