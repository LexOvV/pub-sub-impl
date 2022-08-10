package com.test.servicewithredis.integrated.redis.model;

import org.springframework.data.redis.listener.ChannelTopic;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChannelTopicAdapter that = (ChannelTopicAdapter) o;
        return Objects.equals(channelTopic, that.channelTopic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channelTopic);
    }
}
