package com.test.servicewithredis.integrated.redis.model;

import com.test.servicewithredis.integrated.redis.config.bpp.ChannelTopicBeanPostProcessor;
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
 */

public final class RedisChanelTopics {

    private volatile static Set<ChannelTopicAdapter> channelTopics = new HashSet<>();

    /**
     * Add new Topic by {@link String String} name.
     *
     * @return true if such topic doesn't exist and successfully added. false if didn't.
     * @throws ValidationException if topic name is empty or contains only whitespaces.
     */
    public static synchronized boolean addNewTopic(@NotBlank String name) {
        ChannelTopicAdapter newTopic = new ChannelTopicAdapter(name);
        return channelTopics.add(newTopic);
    }

    /**
     * Add new Topic by {@link ChannelTopic ChannelTopic} class.
     * Object topic must not be null and topic name must not be empty.
     *
     * @return true if such topic doesn't exist and successfully added. false if didn't.
     * @throws ValidationException      if topic is null.
     * @throws IllegalArgumentException if topic name is empty or contains only whitespaces.
     */
    public static synchronized boolean addNewTopic(@NotNull ChannelTopicAdapter topic) {
        if (topic.getChannelTopic().getTopic().isBlank()) {
            throw new IllegalArgumentException("Topic name must not be blank");
        }
        ChannelTopicAdapter newTopic = new ChannelTopicAdapter(topic.toString());
        return channelTopics.add(newTopic);
    }

    /**
     * Check if {@link ChannelTopic ChannelTopic} exists in the Set.
     *
     * @return true if exists
     */
    public boolean contains(ChannelTopicAdapter topic) {
        return channelTopics.stream()
                .anyMatch(t -> t.toString().equals(topic.toString()));
    }

    /**
     * Check if {@link ChannelTopic ChannelTopic} exists in the Set of topics.
     *
     * @return true if exists
     */
    public boolean contains(String topicName) {
        return channelTopics.stream()
                .anyMatch(t -> t.toString().equals(topicName));
    }

    /**
     * The method allows getting a copy of existing {@link ChannelTopic ChannelTopic} by name.
     *
     * @return a copy of existing {@link ChannelTopic ChannelTopic}.
     * @throws NoSuchElementException if topic doesn't exist.
     * @throws ValidationException    if topic name is empty or contains only whitespaces.
     */
    public ChannelTopicAdapter getChanelTopicByName(@NotBlank String name) {
        ChannelTopicAdapter copyOfTopic = new ChannelTopicAdapter(channelTopics.stream()
                .filter(t -> t.getChannelTopic().getTopic().equals(name))
                .findFirst()
                .map(o -> o.getChannelTopic().toString())
                .orElseThrow());
        return copyOfTopic;
    }

    /**
     * The method allows getting {@link Set Set} copies of existing topics.
     *
     * @return {@link Set Set<ChannelTopic>} copies of topics.
     */
    public Set<ChannelTopicAdapter> getChannelTopics() {
        Set<ChannelTopicAdapter> copyOfTopics = new HashSet<>();
        channelTopics.forEach(topic -> copyOfTopics.add(
                new ChannelTopicAdapter(topic.getChannelTopic().toString()))
        );
        return copyOfTopics;
    }
}
