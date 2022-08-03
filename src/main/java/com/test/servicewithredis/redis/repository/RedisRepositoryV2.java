package com.test.servicewithredis.redis.repository;

public interface RedisRepositoryV2 <T> {
    void add(T t);

    T get(String id);
}
