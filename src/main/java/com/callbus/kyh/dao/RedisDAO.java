package com.callbus.kyh.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RedisDAO {

    private final StringRedisTemplate redisTemplate;

    @Value("${expire.client.certNum}")
    private int clientCertNumberExpireSecond; // 1일

    public void saveCertNumber(String phoneNumber, String certNumber) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(phoneNumber, certNumber);
        redisTemplate.expire(phoneNumber, clientCertNumberExpireSecond, TimeUnit.SECONDS);
    }


    public String getCertNumber(String phoneNumber) {
        return redisTemplate.opsForValue().get(phoneNumber);
    }

    public void initCertNumberExpireTime(String phoneNumber) {
        redisTemplate.expire(phoneNumber, clientCertNumberExpireSecond, TimeUnit.SECONDS);
    }
}
