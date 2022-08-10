package com.test.servicewithredis.integrated.redis.publisher;

import com.test.servicewithredis.integrated.redis.model.ChannelTopicAdapter;
import com.test.servicewithredis.integrated.redis.model.RedisChanelTopics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.NoSuchElementException;

@Slf4j
public class RedisMessagePublisher implements MessagePublisher {

    private StringRedisTemplate redisTemplate;
    private RedisChanelTopics redisChanelTopics;

    public RedisMessagePublisher(StringRedisTemplate redisTemplate,
                                 RedisChanelTopics redisChanelTopics) {
        this.redisTemplate = redisTemplate;
        this.redisChanelTopics = redisChanelTopics;
    }

    public RedisMessagePublisher() {
    }

    @Override
    public void publish(ChannelTopicAdapter topic, Object message) {
        if (!redisChanelTopics.contains(topic)) {
            throw new NoSuchElementException("Topic doesn't exist");
        }
        try {
            redisTemplate.convertAndSend(topic.getChannelTopic().getTopic(), message);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("The message wasn't publish", e.getMessage());
        }
    }
}
