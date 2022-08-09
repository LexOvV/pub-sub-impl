package com.test.servicewithredis.integrated.redis.service;

import com.test.servicewithredis.service.TopicService;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RedisTopicService implements TopicService {

    @Override
    public ChannelTopic addNewTopic(String name) {
        return new ChannelTopic(name);
    }
}
