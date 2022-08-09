package com.test.servicewithredis.integrated.redis.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

@Slf4j
public class RedisMessagePublisher implements MessagePublisher {

    private StringRedisTemplate redisTemplate;

    public RedisMessagePublisher(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisMessagePublisher() {
    }

    @Override
    public void publish(ChannelTopic topic, Object message) {
        try {
            redisTemplate.convertAndSend(topic.getTopic(), message);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("The message wasn't publish", e.getMessage());
        }
    }
}
