package com.test.servicewithredis.controller;

import com.test.servicewithredis.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    @GetMapping
    public ResponseEntity<Object> getSign(@RequestBody Object objToSign) {
        return signService.getSign(objToSign);
    }
}
