package com.project.flightbooking.service;

import org.neo4j.driver.v1.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pandora.clone.models.Song;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.v1.Values.parameters;


@Component
@Service
public class DiscountPriceFlightService implements InitializingBean {

    private Session session;

    // Redistemplate
    private Jedis jedis;

    @Value("${neo4j.password}")
    private String neo4jPassword;

    @Value("${neo4j.username}")
    private String neo4jUsername;

    @Value("${neo4j.server}")
    private String neo4jServer;

    @Value("${redis.server}")
    private String redisServer;

    @Autowired
    public DiscountPriceFlightService() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Driver driver = GraphDatabase.driver(neo4jServer, AuthTokens.basic(neo4jUsername, neo4jPassword));
        this.session = driver.session();
        this.jedis = new Jedis(redisServer);
    }

    // start discount price
    public byte[] startDiscountPrice(String filePath){
        try{
            File file = new File(filePath);
            byte[] ret = Files.readAllBytes(file.toPath());

            return ret;
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    // Get list of discounted flights
    public List<String> getDiscountedFlights(){
        StatementResult result = this.session.run("match (d:discountedFlight) return distince s.discountedPrice as discountedPrice");

        List<String> discountedPrice = new ArrayList<>();
        while(result.hasNext()){
            Record r = result.next();


        }
        return discountedPrice;
    }


    // Get discounted prices by flight no
    public Price getDiscountedPriceByFlightNo(String flightNo){
        String flightId = this.jedis.spop(flightNo +"-discountedprice"+discountedPrice);

        // validation
        if(flightId == null){

        }

        int id = Integer.parseInt(flightId);
        // Statement result
        StatementResult result = this.session.run("match (d:)")
    }





    // Retrieve token
    public String retrieveToken(HttpServletRequest request, HttpServletResponse response, String tokenHeader){

    }






}
