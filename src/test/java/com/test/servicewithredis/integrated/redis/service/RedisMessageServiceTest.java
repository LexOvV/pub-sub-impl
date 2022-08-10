package com.test.servicewithredis.integrated.redis.service;

import com.test.servicewithredis.integrated.redis.dto.MessageDto;
import com.test.servicewithredis.integrated.redis.model.ChannelTopicAdapter;
import com.test.servicewithredis.integrated.redis.model.RedisChanelTopics;
import com.test.servicewithredis.integrated.redis.publisher.MessagePublisher;
import com.test.servicewithredis.integrated.redis.service.RedisMessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class RedisMessageServiceTest {

    @Mock
    MessagePublisher messagePublisher;
    @Mock
    RedisChanelTopics redisChanelTopics;

    @InjectMocks
    RedisMessageService redisMessageService;

    @Test
    void publishMessage_ShouldPublishMessage_WhenInvokePublishWithLegalArguments() {
        ChannelTopicAdapter topic = Mockito.mock(ChannelTopicAdapter.class);
        String message = "Hello";
        MessageDto messageDto = new MessageDto();
        messageDto.setMessage(message);
        messageDto.setTopic("World");
        assertDoesNotThrow(() -> redisMessageService.publish(messageDto));
        assertDoesNotThrow(() -> redisMessageService.publish(topic, message));
    }

}
