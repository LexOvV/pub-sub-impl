package com.test.servicewithredis.redis.config;

import com.test.servicewithredis.redis.publisher.MessagePublisher;
import com.test.servicewithredis.redis.publisher.RedisMessagePublisher;
import com.test.servicewithredis.redis.subscriber.RedisChanelTopics;
import com.test.servicewithredis.redis.subscriber.RedisMessageSubscriber;
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
import org.springframework.data.redis.listener.ChannelTopic;
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

    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory);

        return template;
    }

    @Bean
    public MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RedisMessageSubscriber());
    }

    @Bean
    public RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.addMessageListener(messageListener(), topic("def"));
        return container;
    }

    @Bean
    public MessagePublisher redisPublisher() {
        return new RedisMessagePublisher(redisTemplate(connectionFactory()), topic("def"));
    }

    @Bean
    public RedisChanelTopics redisChanelTopics() {
        return new RedisChanelTopics();
    }

    @Bean
    @Scope(value = "prototype")
    public ChannelTopic topic(@Value(value = "name") String name) {
        return new ChannelTopic(name);
    }
}
