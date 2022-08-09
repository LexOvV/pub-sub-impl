package com.test.servicewithredis.integrated.redis.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SubscriberDto {

    @NotBlank
    String subscriberName;
    @NotBlank
    String topicToSubscribe;
}
