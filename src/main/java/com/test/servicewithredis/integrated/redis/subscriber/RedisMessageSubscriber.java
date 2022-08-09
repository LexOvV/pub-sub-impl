package com.test.servicewithredis.integrated.redis.subscriber;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.ArrayList;
import java.util.List;

public class RedisMessageSubscriber implements MessageListener {

    public String subscriberName;
    public List<String> messageList = new ArrayList<>();

    public RedisMessageSubscriber(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public RedisMessageSubscriber() {
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        messageList.add(message.toString());
        System.out.println("Message received: " + message.toString());
    }
}
