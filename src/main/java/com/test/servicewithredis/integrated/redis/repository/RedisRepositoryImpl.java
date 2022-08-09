package com.test.servicewithredis.integrated.redis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Repository
public class RedisRepositoryImpl<T> implements RedisRepository<T> {

    private static final String KEY = "1111";

    private StringRedisTemplate redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public RedisRepositoryImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

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
