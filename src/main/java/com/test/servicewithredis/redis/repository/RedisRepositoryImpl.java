package com.test.servicewithredis.redis.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.UUID;

@Repository
public class RedisRepositoryImpl extends RedisRepository{
    private static String KEY = "1111";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    protected RedisRepositoryImpl(StringRedisTemplate redisTemplate) {
        super(redisTemplate);
    }

    private void init(RedisTemplate<String, Object> redisTemplate) {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    protected String getCollectionName() {
        return null;
    }

    @Override
    protected Duration getTimeToLive() {
        return null;
    }

    @Override
    void add(String key, Object value) {
        hashOperations.put(KEY, key, value);
    }
}
