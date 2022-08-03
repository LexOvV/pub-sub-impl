package com.test.servicewithredis.redis.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {
    private final RedisSettings redisSettings;

    @Bean
    RedisConnectionFactory connectionFactory() {

        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration(redisSettings.getHost(), redisSettings.getPort());
        redisStandaloneConfiguration.setPassword(redisSettings.getPassword());

        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory);

        return template;
    }
}
