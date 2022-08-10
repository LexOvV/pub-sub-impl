package com.test.servicewithredis.integrated.redis.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class MessageDto {

    @NotBlank
    String topic;
    @NotNull
    Object message;
}
