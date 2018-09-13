package com.project.flightbooking.config.redis;


import com.perfectial.study.service.redisqueue.ProcessingBidFromQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisQueueMessageSubscriber implements MessageListener {

    private final ProcessingBidFromQueueService savingBidFromQueueService;

    @Autowired
    public RedisQueueMessageSubscriber(ProcessingBidFromQueueService trafficSavingBusiness) {
        super();
        this.savingBidFromQueueService = trafficSavingBusiness;
    }

    @Override
    public void onMessage(final Message message, final byte[] pattern) {
        savingBidFromQueueService.processAllBidsFromQueue();
    }
}