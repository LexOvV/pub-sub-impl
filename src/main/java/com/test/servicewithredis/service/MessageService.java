package com.test.servicewithredis.service;

import com.test.servicewithredis.integrated.redis.dto.MessageDto;
import com.test.servicewithredis.integrated.redis.model.ChannelTopicAdapter;
import org.springframework.data.redis.listener.ChannelTopic;

public interface MessageService {

    void publish(ChannelTopicAdapter topic, Object message);

    void publish(MessageDto messageDto);
}
