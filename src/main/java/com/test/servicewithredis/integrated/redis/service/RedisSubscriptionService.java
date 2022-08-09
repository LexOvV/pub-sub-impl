package com.test.servicewithredis.integrated.redis.service;

import com.test.servicewithredis.integrated.redis.dto.SubscriberDto;
import com.test.servicewithredis.integrated.redis.model.RedisChanelTopics;
import com.test.servicewithredis.integrated.redis.subscriber.RedisMessageSubscriber;
import com.test.servicewithredis.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class RedisSubscriptionService implements SubscriptionService {

    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisChanelTopics redisChanelTopics;

    @Override
    public void addNewSubscriber(SubscriberDto subscriberDto) {
        RedisMessageSubscriber messageSubscriber = new RedisMessageSubscriber(subscriberDto.getSubscriberName());
        ChannelTopic topic = redisChanelTopics.getChanelTopicByName(subscriberDto.getTopicToSubscribe());
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(messageSubscriber);

        redisMessageListenerContainer.addMessageListener(messageListenerAdapter, topic);
    }
}
