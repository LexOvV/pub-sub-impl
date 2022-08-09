package com.test.servicewithredis.service;

import org.springframework.data.redis.listener.ChannelTopic;

public interface TopicService {

    ChannelTopic addNewTopic(String name);
}
