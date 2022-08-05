package com.test.servicewithredis.redis.subscriber.bpp;

import com.test.servicewithredis.redis.subscriber.RedisChanelTopics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.redis.listener.ChannelTopic;

@Slf4j
public class ChannelTopicBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAssignableFrom(ChannelTopic.class)) {
            boolean isSuccessful = RedisChanelTopics.addNewTopic((ChannelTopic) bean);
            if (!isSuccessful) {
                log.info("Topic already exists");
                System.out.println("Topic already exists");
            }
        }
        return bean;
    }
}
