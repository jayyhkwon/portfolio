package com.callbus.kyh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {

    @Autowired
    private StringRedisTemplate redisTemplate;



}
