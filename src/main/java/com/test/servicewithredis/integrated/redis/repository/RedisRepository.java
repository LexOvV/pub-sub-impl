package com.test.servicewithredis.integrated.redis.repository;

public interface RedisRepository<T> {
    void add(T t);

    T get(String id);
}
