package com.project.flightbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableWebMvc
public class FlightbookingApplication extends WebMvcConfigurerAdapter{


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public SwaggerController swaggerController(final LoadBalancerClient loadBalancerClient, final DiscoveryClient discoveryClient) {
		return new SwaggerController(loadBalancerClient, discoveryClient);
	}

	public static void main(String[] args) {
		SpringApplication.run(FlightbookingApplication.class, args);
	}
}
