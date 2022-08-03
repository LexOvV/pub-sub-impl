package com.test.servicewithredis.redis.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

abstract class RedisRepository<T> {

    private static final ObjectMapper mapper;
    private final Class<T> type;
    protected final StringRedisTemplate redisTemplate;


    static {
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.registerModule(new JavaTimeModule());
        mapper.setSerializationInclusion(NON_ABSENT);
        mapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
    }

    protected RedisRepository(StringRedisTemplate redisTemplate) {
        type = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), RedisRepository.class);
        this.redisTemplate = redisTemplate;
    }

    /**
     * Generates redis key.
     *
     * @param idForKey identifier for key.
     *
     * @return Generated key.
     */
    protected String generateKey(Optional<String> idForKey) {
        return idForKey.map(id -> String.format("%s_%s", getCollectionName(), id))
                .orElseGet(this::getCollectionName);
    }

    /**
     * Gets collection name.
     *
     * @return Collection name.
     */
    protected abstract String getCollectionName();

    /**
     * Gets time to live value for key.
     *
     * @return Time to live value.
     */
    protected abstract Duration getTimeToLive();

    /**s
     * Find object for the given key.
     *
     * @param key Redis key.
     *
     * @return Found object if exists.
     */
    protected Optional<T> findByKey(String key) {
        String resultValue = redisTemplate.opsForValue().get(key);
        try {
            return Optional.ofNullable(mapper.readValue(resultValue, type));
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves new object to redis.
     *
     * @param key Redis key.
     * @param t Object to save.
     */
    protected void save(String key, T t) {
        try {
            String objectAsJson = mapper.writeValueAsString(t);
            redisTemplate.opsForValue().set(key, objectAsJson);
            redisTemplate.expire(key, getTimeToLive());
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
