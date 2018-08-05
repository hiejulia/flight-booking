package com.project.flightbooking.config;

import com.cassiomolin.example.gateway.filter.CustomLocationRewriteFilter;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * Zuyl conf g
 * 
 */
@Configuration
@EnableZuulProxy
public class ZuulProxyConfiguration {

    @Bean
    public CustomLocationRewriteFilter locationRewriteFilter() {
        return new CustomLocationRewriteFilter();
    }
}