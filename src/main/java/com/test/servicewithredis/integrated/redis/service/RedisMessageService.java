package com.test.servicewithredis.integrated.redis.service;

import com.test.servicewithredis.integrated.redis.dto.MessageDto;
import com.test.servicewithredis.integrated.redis.model.ChannelTopicAdapter;
import com.test.servicewithredis.integrated.redis.model.RedisChanelTopics;
import com.test.servicewithredis.integrated.redis.publisher.MessagePublisher;
import com.test.servicewithredis.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class RedisMessageService implements MessageService {

    private final MessagePublisher messagePublisher;
    private final RedisChanelTopics redisChanelTopics;

    @Override
    public void publish(ChannelTopicAdapter topic, Object message) {
        messagePublisher.publish(topic, message);
    }

    @Override
    public void publish(MessageDto messageDto) {
        ChannelTopicAdapter topic = redisChanelTopics.getChanelTopicByName(messageDto.getTopic());
        messagePublisher.publish(topic, messageDto.getMessage());
    }
}
