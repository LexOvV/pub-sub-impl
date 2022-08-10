package com.test.servicewithredis.controller;

import com.test.servicewithredis.integrated.redis.model.ChannelTopicAdapter;
import com.test.servicewithredis.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping("/new-topic")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewTopic(@RequestBody String topicName) {
        topicService.addNewTopic(topicName);
    }

    @GetMapping("/all")
    public Set<String> getAllTopics() {
        return topicService.getAll();
    }
}
