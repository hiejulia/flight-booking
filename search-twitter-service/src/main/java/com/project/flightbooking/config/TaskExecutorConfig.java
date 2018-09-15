package com.project.flightbooking.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@Configuration
public class TaskExecutorConfig {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor(final @Value("${taskExecutor.corePoolSize}") int corePoolSize,
                                               final @Value("${taskExecutor.maxPoolSize}") int maxPoolSize,
                                               final @Value("${taskExecutor.queueCapacity}") int queueCapacity) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        return taskExecutor;
    }
}