package com.project.flightbooking.repository;

import com.project.flightbooking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // name, address, city, phoneNo

    List<User> findByName(String name);

    User findByUserId(String id);



}