package com.project.flightbooking.repository;


import com.project.flightbooking.model.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tweet")
public interface TweetRepository extends MongoRepository<Tweet, Long> {

}