package com.test.servicewithredis.integrated.redis.config.bpp;

import com.test.servicewithredis.integrated.redis.model.ChannelTopicAdapter;
import com.test.servicewithredis.integrated.redis.model.RedisChanelTopics;
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
        if (bean.getClass().isAssignableFrom(ChannelTopicAdapter.class)) {
            boolean isSuccessful = RedisChanelTopics.addNewTopic((ChannelTopicAdapter) bean);
            if (!isSuccessful) {
                log.info("Topic already exists");
                System.out.println("Topic already exists");
            }
        }
        return bean;
    }
}
