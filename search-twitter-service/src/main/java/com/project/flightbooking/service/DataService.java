package com.project.flightbooking.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.thinksky.search.Feign.TweetClient;
import com.thinksky.search.repository.TweetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
@Slf4j
public class DataService {
    private static final String HYSTRIX_FALL_BACK = "getFallbackIndexedData";
    private static final String HYSTRIX_COMMAND_KEY = "indexedData";
    private static final String HYSTRIX_GROUP_KEY = "search-service";

    //TODO: Should read it from properties file.
    private long currentPageIndex = 1;

    @Inject
    private ThreadPoolTaskExecutor taskExecutor;

    @Inject
    private SolrTemplate solrTemplate;

    @Inject
    private TweetRepository tweetRepository;

    @Inject
    private TweetClient tweetClient;

    @HystrixCommand(fallbackMethod = HYSTRIX_FALL_BACK,
            commandKey = HYSTRIX_COMMAND_KEY,
            groupKey = HYSTRIX_GROUP_KEY)
    @Scheduled(fixedDelay = 60000)
    public void indexedData() {

        log.info("SEARCH-SERVICE: Data indexer is starting to work.");

        currentPageIndex = getCurrentPageIndex();

        log.info("Current page for pushing is {}", currentPageIndex);

        long totalPage = tweetClient.getTweets().getMetadata().getTotalPages();

        for (long i = currentPageIndex; i <= totalPage; i++) {
            Runnable task = new PusherProcessor(tweetRepository, tweetClient, i);
            taskExecutor.execute(task);

            currentPageIndex = i++;

            saveCurrentPageIndex(i);
        }
    }

    //Fallback method for Indexing data.
    public void getFallbackIndexedData(Throwable e) {
        log.info("Hystrix fallback for indexedData, error={}", e.getMessage());
        saveCurrentPageIndex(currentPageIndex);
    }

    private long getCurrentPageIndex() {
        Properties prop = new Properties();
        String propFileName = "cache.properties";

        //InputStream stream = new ClassPathResource(propFileName).getInputStream();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            try {
                prop.load(inputStream);

                return Long.parseLong(prop.getProperty("currentPage"));
            } catch (IOException e) {
                e.printStackTrace();

            }
        }

        return 1;
    }

    private void saveCurrentPageIndex(long currentPageIndex) {
        Properties prop = new Properties();
        String propFileName = "cache.properties";

        //InputStream stream = new ClassPathResource(propFileName).getInputStream();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.setProperty("currentPage", String.valueOf(currentPageIndex));

        }
    }
}