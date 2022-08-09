package com.test.servicewithredis.service;

import com.test.servicewithredis.integrated.redis.dto.MessageDto;
import org.springframework.data.redis.listener.ChannelTopic;

public interface MessageService {

    void publish(ChannelTopic topic, Object message);

    void publish(MessageDto messageDto);
}
