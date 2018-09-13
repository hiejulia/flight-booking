package com.project.flightbooking.service.redis;


import com.perfectial.study.dto.BidDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.perfectial.study.repository.redisqueue.BidRedisQueueRepository;

@Service
public class SubscribeRedisQueueServiceImpl implements SubscribeRedisQueueService{

    @Value("${redisqueue.name}")
    String queueName;

    private BidRedisQueueRepository<String, BidDTO> bidQueue;

    public SubscribeRedisQueueServiceImpl(BidRedisQueueRepository<String, BidDTO> bidQueue) {
        super();
        this.bidQueue = bidQueue;
    }

    @Override
    public Collection<BidDTO> leftPopAllQueue() {
        return bidQueue.popAll(queueName);
    }

    @Override
    public BidDTO leftPop() {
        return bidQueue.pop(queueName, false);

    }

    @Override
    public Collection<BidDTO> getQueue() {
        return bidQueue.get(queueName);
    }
}