package com.test.servicewithredis.integrated.redis.model;

import org.springframework.data.redis.listener.ChannelTopic;

public class ChannelTopicAdapter {

    private ChannelTopic channelTopic;

    public ChannelTopicAdapter() {
    }

    public ChannelTopicAdapter(String name) {
        this.channelTopic = new ChannelTopic(name);
    }

    @Override
    public String toString() {
        return channelTopic.toString();
    }

    public ChannelTopic getChannelTopic() {
        return channelTopic;
    }

    public void setChannelTopic(String name) {
        this.channelTopic = new ChannelTopic(name);
    }
}
