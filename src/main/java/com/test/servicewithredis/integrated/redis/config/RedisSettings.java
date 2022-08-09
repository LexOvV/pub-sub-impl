package com.test.servicewithredis.integrated.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @host - host for redis
 * @password - password for redis
 * @port - port for redis
 */

@Data
@Component
@Validated
@ConfigurationProperties(prefix = "prefix.redis")
public class RedisSettings {

    @NotNull
    String host;

    @NotNull
    String password;

    @NotNull
    Integer port;
}
