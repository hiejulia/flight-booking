package com.project.flightbooking.repository.redis;

import java.util.Collection;


public interface BidRedisQueueRepository<K,V> {

    void push(K key, V value, boolean right);

    void multiAdd(K key, Collection<V> values, boolean right);

    Collection<V> get(K key);

    V pop(K key, boolean right);

    Collection<V> popAll(K key);

    void delete(K key);

    void trim(K key, int start, int end);

    Long size(K key);

}