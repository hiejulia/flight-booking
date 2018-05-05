package com.project.flightbooking;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.project.flightbooking.dto.Passenger;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class NotificationScheduler {

    @Autowired
    private RestTemplate restTemplate; // Rest Template

    private static final Logger log = LoggerFactory.getLogger(NotificationScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final String FIREBASE_SERVER_KEY = "AAAAoeDtrfQ:APA91bHXsvdLTjdCjEIPBPlBGhxjb3uv6URK2f8s8TjP9A1Kpwmm1RkbBKU_xrZWN6olceJEtmP9JMhmB2-yMMznSK0QhlOWoYtetg7j3-lmDdE_8Xkc1FCyp2uzOaiZGPBTwE55JOML";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    // Report current time :
    @Scheduled(fixedRate = 50000)
    public void reportCurrentTime() {

        Passenger[] usersArray = restTemplate.getForObject("http://172.31.84.105:8080/uaa/findall", User[].class); // find all user
        send(usersArray);// send
    }

    // send report
    public void send(Passenger[] usersArray) {

        for (Passenger user : usersArray) {

            JSONObject body = new JSONObject();

            try {
                // each passenger

                body.put("to", "/topics/"+user.getHobby());
                body.put("priority", "high");

                JSONObject notification = new JSONObject();
                notification.put("title", "Hi "+user.getUsername()+", "+hobbies.get(user.getHobby()));
                notification.put("body", "Hi "+user.getUsername()+", "+hobbies.get(user.getHobby()));
                body.put("notification", notification);

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "key=" + FIREBASE_SERVER_KEY);
            headers.set("Content-Type", "application/json");

            HttpEntity<String> request = new HttpEntity<>(body.toString(),headers);
            // post
            restTemplate.postForObject(FIREBASE_API_URL, request, String.class);
        }
    }
}