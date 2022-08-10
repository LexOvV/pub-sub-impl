package com.test.servicewithredis.integrated.redis.service;

import com.test.servicewithredis.integrated.redis.model.ChannelTopicAdapter;
import com.test.servicewithredis.integrated.redis.model.RedisChanelTopics;
import com.test.servicewithredis.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class RedisTopicService implements TopicService {

    private final RedisChanelTopics redisChanelTopics;
    private final ApplicationContext applicationContext;

    @Override
    public ChannelTopicAdapter addNewTopic(String name) {
        if (redisChanelTopics.contains(name)) {
            throw new IllegalArgumentException("Topic already exists");
        }
        ChannelTopicAdapter topicAdapter = (ChannelTopicAdapter) applicationContext.getBean(name);
        return topicAdapter;
//        return new ChannelTopicAdapter(name);
    }

    @Override
    public Set<String> getAll() {
        return redisChanelTopics.getChannelTopics().stream()
                .map(t -> t.getChannelTopic().toString())
                .collect(Collectors.toSet());
    }
}
