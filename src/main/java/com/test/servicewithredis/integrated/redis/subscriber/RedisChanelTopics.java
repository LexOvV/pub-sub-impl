package com.test.servicewithredis.integrated.redis.subscriber;

import com.test.servicewithredis.integrated.redis.subscriber.bpp.ChannelTopicBeanPostProcessor;
import org.springframework.data.redis.listener.ChannelTopic;

import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * RedisChannelTopic class contains {@link Set Set} of existing topics. <br>
 * Every new topic puts in the Set of channelTopics on creation
 * using {@link ChannelTopicBeanPostProcessor#postProcessAfterInitialization(Object, String) ChannelTopicBeanPostProcessor}
 * */

public final class RedisChanelTopics {

    private volatile static Set<ChannelTopic> channelTopics = new HashSet<>();

    /**
     *  Add new Topic by {@link String String} name.
     *
     * @return true if such topic doesn't exist and successfully added. false if didn't.
     *
     * @throws ValidationException if topic name is empty or contains only whitespaces.
     * */
    public static synchronized boolean addNewTopic(@NotBlank String name) {
        ChannelTopic newTopic = new ChannelTopic(name);
        return channelTopics.add(newTopic);
    }

    /**
     *  Add new Topic by {@link ChannelTopic ChannelTopic} class.
     *  Object topic must not be null and topic name must not be empty.
     *
     * @return true if such topic doesn't exist and successfully added. false if didn't.
     *
     * @throws ValidationException if topic is null.
     * @throws IllegalArgumentException if topic name is empty or contains only whitespaces.
     * */
    public static synchronized boolean addNewTopic(@NotNull ChannelTopic topic) {
        if (topic.getTopic().isBlank()) {
            throw new IllegalArgumentException("Topic name must not be blank");
        }
        ChannelTopic newTopic = new ChannelTopic(topic.toString());
        return channelTopics.add(newTopic);
    }

    /**
     *   The method allows getting {@link Set Set} copies of existing topics.
     *
     * @return {@link Set Set<ChannelTopic>} copies of topics.
     * */
    public Set<ChannelTopic> getChannelTopics() {
        Set<ChannelTopic> copyOfTopics = new HashSet<>();
        channelTopics.forEach(topic -> copyOfTopics.add(new ChannelTopic(topic.toString())));
        return copyOfTopics;
    }

    /**
     *   The method allows getting a copy of existing {@link ChannelTopic ChannelTopic} by name.
     *
     * @return a copy of existing {@link ChannelTopic ChannelTopic}.
     *
     * @throws NoSuchElementException if topic doesn't exist.
     * @throws ValidationException if topic name is empty or contains only whitespaces.
     * */
    public ChannelTopic getChanelTopicByName(@NotBlank String name) {
        ChannelTopic copyOfTopic = new ChannelTopic(channelTopics.stream()
                .filter(t -> t.toString().equals(name))
                .findFirst()
                .map(ChannelTopic::toString)
                .orElseThrow());
        return copyOfTopic;
    }
}
