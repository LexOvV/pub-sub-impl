package com.test.servicewithredis.service;

import com.test.servicewithredis.integrated.redis.model.ChannelTopicAdapter;

import java.util.Set;

public interface TopicService {

    ChannelTopicAdapter addNewTopic(String name);

    Set<String> getAll();
}
