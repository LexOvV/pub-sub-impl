package com.test.servicewithredis.service;

import com.test.servicewithredis.integrated.redis.dto.SubscriberDto;

public interface SubscriptionService {

    void addNewSubscriber(SubscriberDto subscriberDto);
}
