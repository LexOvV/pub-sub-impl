package com.test.servicewithredis.service;

import com.test.servicewithredis.integrated.redis.dto.MessageDto;
import com.test.servicewithredis.integrated.redis.model.ChannelTopicAdapter;

public interface MessageService {

    void publish(ChannelTopicAdapter topic, Object message);

    void publish(MessageDto messageDto);
}
