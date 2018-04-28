package com.project.flightbooking.service;


import com.project.flightbooking.domain.User;
import com.project.flightbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findByName(String name){
        return userRepository.findByName(name);
    }

    public User findByUserId(String id){
        return userRepository.findByUserId(id);
    }

    public User addNewUser(User user){
        userRepository.save(user);
        return user;
    }


}
