package com.test.servicewithredis.controller;

import com.test.servicewithredis.integrated.redis.dto.MessageDto;
import com.test.servicewithredis.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/new-message")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewMessageToTopic(@RequestBody MessageDto messageDto) {
        messageService.publish(messageDto);
    }
}
