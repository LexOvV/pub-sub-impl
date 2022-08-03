package com.test.servicewithredis.service;

import org.springframework.http.ResponseEntity;

public interface SignService {

    ResponseEntity<Object> getSign(Object objToSign);
}
