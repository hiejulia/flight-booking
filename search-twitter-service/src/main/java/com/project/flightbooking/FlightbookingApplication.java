package com.project.flightbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableZuulProxy
@EnableHystrix
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@EnableSolrRepositories(basePackages = "com.project.flightbooking.search.repository", multicoreSupport = true)
@ComponentScan
public class FlightbookingApplication {


	public static void main(String[] args) {
		SpringApplication.run(FlightbookingApplication.class, args);
	}

	@Bean
	public SolrClient solrClient() {
		HttpSolrClient client = new HttpSolrClient("http://localhost:8983/solr");
		return  client;
	}

	@Bean
	public SolrTemplate solrTemplate(SolrClient client) throws Exception {
		return new SolrTemplate(client);
	}

}
