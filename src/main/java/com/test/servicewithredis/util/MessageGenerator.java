package com.test.servicewithredis.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class MessageGenerator {

    private final int size = 200000;

    public byte[] generateByteArray() throws NoSuchAlgorithmException {
        byte[] arr = new byte[size];
        SecureRandom.getInstanceStrong().nextBytes(arr);
        return arr;
    }
}
