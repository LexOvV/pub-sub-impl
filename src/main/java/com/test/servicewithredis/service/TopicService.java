package com.test.servicewithredis.service;

import com.test.servicewithredis.integrated.redis.model.ChannelTopicAdapter;
import org.springframework.data.redis.listener.ChannelTopic;

import java.util.Set;

public interface TopicService {

    ChannelTopicAdapter addNewTopic(String name);

    Set<String> getAll();
}
