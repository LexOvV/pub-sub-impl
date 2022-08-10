package com.test.servicewithredis.integrated.redis.service;

import com.test.servicewithredis.integrated.redis.model.ChannelTopicAdapter;
import com.test.servicewithredis.integrated.redis.model.RedisChanelTopics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import java.util.Set;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RedisTopicServiceTest {

    private static RedisChanelTopics redisChanelTopics = new RedisChanelTopics();
    private final String illegalArgumentExceptionText = "Topic already exists";

    @Mock
    ApplicationContext applicationContext;

    @InjectMocks
    RedisTopicService redisTopicService;

    @BeforeEach
    void init() {
        redisTopicService = new RedisTopicService(redisChanelTopics, applicationContext);
    }

    @Test
    void addNewTopic_ShouldThrowIllegaArgumentException_WhenTopicAlreadyExists() {

        String name = "existingTopic";

        RedisChanelTopics.addNewTopic(name);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> redisTopicService.addNewTopic(name)
        );
        assertThat(exception.getMessage(), is(equalTo(illegalArgumentExceptionText)));
    }

    @Test
    void addNewTopic_ShouldReturnNewTopic_WhenInvokedWithNewName() {
        String name = "topic";
        ChannelTopicAdapter expectedTopic = new ChannelTopicAdapter("topic");

        when(applicationContext.getBean(anyString())).thenReturn(expectedTopic);

        ChannelTopicAdapter actualTopic = redisTopicService.addNewTopic(name);

        assertThat(expectedTopic, is(equalTo(actualTopic)));
    }

    @Test
    void getAll_ShouldReturnSetOfTopics_WhenMethodInvoked() {
        RedisChanelTopics.addNewTopic("topic1");
        RedisChanelTopics.addNewTopic("topic2");
        RedisChanelTopics.addNewTopic("topic3");

        Set<String> expectedTopicsNames = Set.of("topic1", "topic2", "topic3");

        Set<String> actualTopicNames = redisTopicService.getAll();

        assertThat(actualTopicNames, is( not( empty() ) ));
        assertThat(actualTopicNames, hasSize(expectedTopicsNames.size()));
        assertThat(actualTopicNames, containsInAnyOrder(expectedTopicsNames.toArray()));
    }
}
