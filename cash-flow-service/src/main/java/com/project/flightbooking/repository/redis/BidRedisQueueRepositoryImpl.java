package com.project.flightbooking.repository.redis;


import com.perfectial.study.dto.BidDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class BidRedisQueueRepositoryImpl implements BidRedisQueueRepository<String, BidDTO> {

    // Redis template
    private final RedisTemplate<String, BidDTO> redisTemplate;

    public BidRedisQueueRepositoryImpl(RedisTemplate<String, BidDTO> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public void push(String key, BidDTO value, boolean right) {
        if (right) {
            redisTemplate.opsForList().rightPush(key, value);
        } else {
            redisTemplate.opsForList().leftPush(key, value);
        }
    }

    @Override
    public void multiAdd(String key, Collection<BidDTO> values, boolean right) {
        for (BidDTO value : values) {
            push(key, value, right);
        }
    }

    @Override
    public Collection<BidDTO> get(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    @Override
    public BidDTO pop(String key, boolean right) {
        BidDTO value;
        if (right) {
            value = redisTemplate.opsForList().rightPop(key);
        } else {
            value = redisTemplate.opsForList().leftPop(key);
        }
        return value;
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void trim(String key, int start, int end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    @Override
    public Long size(String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public Collection<BidDTO> popAll(String key) {
        Collection<BidDTO> bids = new ArrayList<>();
        BidDTO bid = pop(key, false);
        while (bid != null) {
            bids.add(bid);
            bid = pop(key, false);
        }
        return bids;
    }
}