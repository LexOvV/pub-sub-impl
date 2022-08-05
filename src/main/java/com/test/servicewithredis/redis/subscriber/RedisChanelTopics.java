package com.test.servicewithredis.redis.subscriber;

import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public final class RedisChanelTopics {

    private static Set<ChannelTopic> channelTopics = new HashSet<>();

    public static boolean addNewTopic(String name) {
        ChannelTopic topic = new ChannelTopic(name);
        return channelTopics.add(topic);
    }

    public static boolean addNewTopic(ChannelTopic topic) {
        return channelTopics.add(topic);
    }

    public Set<ChannelTopic> getChannelTopics() {
        return Set.copyOf(channelTopics);
    }

    public ChannelTopic getChanelTopicByName(String name) {
        return channelTopics.stream()
                .filter(t -> t.toString().equals(name))
                .findFirst()
                .orElseThrow();
    }
}
