package com.project.flightbooking;

import com.netflix.ribbon.proxy.annotation.Http;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class FlightbookingApplication {
	private static final HttpMethod[] SUPPORTED_MULTIPART_METHODS = { HttpMethod.POST, HttpMethod.PUT };
	public static void main(String[] args) {
		SpringApplication.run(FlightbookingApplication.class, args);
	}

	// CORS enable config
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		config.setAllowedMethods(Arrays.stream(Http.HttpMethod.values()).map(Http.HttpMethod::name).collect(Collectors.toList()));
		source.registerCorsConfiguration("/**", config);
		return CorsFilter(source);
	}


	// Multipart config

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver() {
			@Override
			public boolean isMultipart(HttpServletRequest request) {
				boolean methodMatches = Arrays.stream(SUPPORTED_MULTIPART_METHODS)
						.anyMatch(method -> method.matches(request.getMethod()));
				String contentType = request.getContentType();
				return methodMatches && (contentType != null && contentType.toLowerCase().startsWith("multipart/"));
			}
		};
}
