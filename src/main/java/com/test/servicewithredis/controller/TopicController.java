package com.test.servicewithredis.controller;

import com.test.servicewithredis.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping("/new-topic")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewTopic(@RequestBody String topicName) {
        topicService.addNewTopic(topicName);
    }
}
