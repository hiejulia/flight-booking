package com.project.flightbooking;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

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

    Map<String,String> hobbies;

    @PostConstruct
    public void addNotificationsToHobbies() {
        hobbies = new HashMap<>();
        hobbies.put("music","There is a rock concert going on");
        hobbies.put("movies","There is an action film coming this weekend");
        hobbies.put("football","Watch Manchester vs Arsenal live");
    }

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(NotificationScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final String FIREBASE_SERVER_KEY = "AAAAoeDtrfQ:APA91bHXsvdLTjdCjEIPBPlBGhxjb3uv6URK2f8s8TjP9A1Kpwmm1RkbBKU_xrZWN6olceJEtmP9JMhmB2-yMMznSK0QhlOWoYtetg7j3-lmDdE_8Xkc1FCyp2uzOaiZGPBTwE55JOML";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    @Scheduled(fixedRate = 50000)
    public void reportCurrentTime() {
        log.info("Pushing notifications now at " + dateFormat.format(new Date()));
        User[] usersArray = restTemplate.getForObject("http://172.31.84.105:8080/uaa/findall", User[].class);
        send(usersArray);
    }


    public void send(User[] usersArray) {

        for (User user : usersArray) {

            JSONObject body = new JSONObject();

            try {

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
            System.out.println(body.toString());
            restTemplate.postForObject(FIREBASE_API_URL, request, String.class);
        }
    }
}