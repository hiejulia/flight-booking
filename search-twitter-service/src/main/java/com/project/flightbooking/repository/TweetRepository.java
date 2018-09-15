package com.project.flightbooking.repository;



import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

@RepositoryRestResource(path = "search")
public interface TweetRepository extends SolrCrudRepository<Tweet, Long> {

    // Find by text
    List<Tweet> findByText(@Param("text") String text);
}