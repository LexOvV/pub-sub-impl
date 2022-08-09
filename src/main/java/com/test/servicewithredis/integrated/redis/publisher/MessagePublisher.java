package com.test.servicewithredis.integrated.redis.publisher;

import org.springframework.data.redis.listener.ChannelTopic;

public interface MessagePublisher {

    void publish(ChannelTopic topic, Object message);

}
