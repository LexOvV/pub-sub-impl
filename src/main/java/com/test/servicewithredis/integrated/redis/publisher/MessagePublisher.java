package com.test.servicewithredis.integrated.redis.publisher;

import com.test.servicewithredis.integrated.redis.model.ChannelTopicAdapter;
import org.springframework.data.redis.listener.ChannelTopic;

public interface MessagePublisher {

    void publish(ChannelTopicAdapter topic, Object message);

}
