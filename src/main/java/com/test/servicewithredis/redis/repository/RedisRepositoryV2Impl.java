package com.test.servicewithredis.redis.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.UUID;

public class RedisRepositoryV2Impl<T> implements RedisRepositoryV2<T> {
    private static final String KEY = "1111";

    private StringRedisTemplate redisTemplate;
    private HashOperations hashOperations;

    @PostConstruct
    public void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void add(T t) {
        hashOperations.put(KEY, UUID.randomUUID().toString(), t);
    }

    @Override
    public T get(String id) {
        return (T) hashOperations.get(KEY, id);
    }
}
