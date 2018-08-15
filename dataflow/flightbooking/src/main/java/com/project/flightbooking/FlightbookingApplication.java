package com.project.flightbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;


/**
 * Add for getting passengers flight experience 
 * 
 * 
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class FlightbookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightbookingApplication.class, args);
	}
	// https://cwiki.apache.org/confluence/display/Hive/HiveServer2+Clients#HiveServer2Clients-JDBC
	/**
	 * 
	 * Config Apache Hive 
	 * 
	 */
	@Configuration
	@Profile("default")
	static class LocalConfiguration {
		Logger logger = LoggerFactory.getLogger(LocalConfiguration.class);

	    @Value("${consumerkey}")
	    private String consumerKey;

	    @Value("${consumersecret}")
	    private String consumerSecret;
	    
	    @Value("${accesstoken}")
	    private String accessToken;
	    
	    @Value("${accesstokensecret}")
	    private String accessTokenSecret;
			
		// API key for Twitter 
		@Bean
		public Twitter twitter() {
			Twitter twitter = null;
			
			try {
				twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
			} catch (Exception e) {
				logger.error("Error:", e);
			}
			
			return twitter;
		}

		// Config Apache Hive database - datasource 
	    @Value("${hiveuri}")
	    private String databaseUri;
	    
	    @Value("${hiveusername}")
	    private String username;
	    
		@Bean
		public DataSource dataSource() {
			
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setUrl(databaseUri);
			dataSource.setDriverClassName("org.apache.hive.jdbc.HiveDriver");
			dataSource.setUsername(username);
			logger.error("Initialized Hive");
			
			return dataSource;
		}
	}
}
