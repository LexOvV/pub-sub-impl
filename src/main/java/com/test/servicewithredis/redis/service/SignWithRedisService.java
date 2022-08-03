package com.test.servicewithredis.redis.service;

import com.test.servicewithredis.service.SignService;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Primary
public class SignWithRedisService implements SignService {

    @Override
    public ResponseEntity<Object> getSign(Object objToSign) {
        return ResponseEntity.ok().build();
    }
}
