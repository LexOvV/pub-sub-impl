package com.test.servicewithredis.integrated.redis.config;

import com.test.servicewithredis.integrated.redis.model.ChannelTopicAdapter;
import com.test.servicewithredis.integrated.redis.model.RedisChanelTopics;
import com.test.servicewithredis.integrated.redis.publisher.MessagePublisher;
import com.test.servicewithredis.integrated.redis.publisher.RedisMessagePublisher;
import com.test.servicewithredis.integrated.redis.subscriber.RedisMessageSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
@ComponentScan(value = "com.test.servicewithredis")
@RequiredArgsConstructor
public class RedisConfig {

    private final RedisSettings redisSettings;

    @Bean
    public RedisConnectionFactory connectionFactory() {

        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration(redisSettings.getHost(), redisSettings.getPort());
        redisStandaloneConfiguration.setPassword(redisSettings.getPassword());

        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean(name = "topic")
    @Scope(value = "prototype")
    public ChannelTopicAdapter getTopic(String name) {
        ChannelTopicAdapter topic = new ChannelTopicAdapter(name);
        System.out.println("=======" + topic.getChannelTopic().toString() + " created");
        boolean b = RedisChanelTopics.addNewTopic(topic);
        System.out.println(b);
        return topic;
    }

    @Bean
    @Scope(value = "prototype")
    public MessageListenerAdapter messageSubscriber(@Value(value = "subscriber") RedisMessageSubscriber subscriber) {
        return new MessageListenerAdapter(subscriber);
    }

    @Bean
    public RedisChanelTopics redisChanelTopics() {
        return new RedisChanelTopics();
    }

    @Bean
    public RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        return container;
    }

    @Bean
    public MessagePublisher redisPublisher() {
        return new RedisMessagePublisher(redisTemplate(connectionFactory()), redisChanelTopics());
    }

    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory);

        return template;
    }
}
