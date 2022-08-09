package com.test.servicewithredis.controller;

import com.test.servicewithredis.integrated.redis.dto.SubscriberDto;
import com.test.servicewithredis.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubscriberController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/new-subscriber")
    public void addNewSubscriber(@RequestBody SubscriberDto subscriberDto) {
        subscriptionService.addNewSubscriber(subscriberDto);
    }
}
