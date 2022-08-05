package com.test.servicewithredis.redis.publisher;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class RedisMessagePublisher implements MessagePublisher {

    private StringRedisTemplate redisTemplate;
    private ChannelTopic channelTopic;

    public RedisMessagePublisher(StringRedisTemplate redisTemplate, ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.channelTopic = topic;
    }

    public RedisMessagePublisher() {
    }

    @Override
    public void publish(String message) {
        redisTemplate.convertAndSend(this.channelTopic.getTopic(), message);
    }
}
