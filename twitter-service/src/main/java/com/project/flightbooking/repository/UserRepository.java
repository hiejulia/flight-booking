package com.project.flightbooking.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// User
@RepositoryRestResource(path = "user")
public interface UserRepository extends MongoRepository<TwitterProfile, Long> {
}

