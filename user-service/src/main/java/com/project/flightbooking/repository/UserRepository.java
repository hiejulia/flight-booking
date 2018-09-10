package com.project.flightbooking.repository;

import com.project.flightbooking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.vbview.database.model.User;
import org.springframework.data.neo4j.repository.GraphRepository;


public interface UserRepository extends GraphRepository<User> {

}