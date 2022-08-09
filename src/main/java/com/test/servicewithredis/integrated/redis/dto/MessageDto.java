package com.test.servicewithredis.integrated.redis.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDto {

    String topic;
    Object message;
}
