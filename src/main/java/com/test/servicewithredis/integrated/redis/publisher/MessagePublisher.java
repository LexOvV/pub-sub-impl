package com.test.servicewithredis.integrated.redis.publisher;

import com.test.servicewithredis.integrated.redis.model.ChannelTopicAdapter;

public interface MessagePublisher {

    void publish(ChannelTopicAdapter topic, Object message);

}
