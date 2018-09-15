package com.project.flightbooking.controller;


import com.thinksky.search.model.Tweet;
import com.thinksky.search.repository.TweetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@RestController
@RequestMapping("/")
public class SearchController {


    @Inject
    private TweetRepository tweetRepository;

    @RequestMapping(value = "/search2/{text}", method = GET)
    public ResponseEntity<List<Tweet>> Search(@PathVariable("text") String text) {

        List<Tweet> tweetSearch = tweetRepository.findByText(text);

        return new ResponseEntity<List<Tweet>>(tweetSearch, ACCEPTED);
    }
}