package com.test.servicewithredis.redis.service;

import com.test.servicewithredis.redis.repository.RedisRepository;
import com.test.servicewithredis.service.SignService;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@Primary
public class SignWithRedisService implements SignService {
    private RedisRepository<ByteArrayInputStream> redisRepository;

    @Override
    public ResponseEntity<Object> getSign(Object objToSign) {
        return ResponseEntity.ok().build();
    }


}
