package com.test.servicewithredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceWithRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceWithRedisApplication.class, args);
    }

}
