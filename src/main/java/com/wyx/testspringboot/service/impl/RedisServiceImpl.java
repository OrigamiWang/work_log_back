package com.wyx.testspringboot.service.impl;

import com.wyx.testspringboot.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: testSpringBoot
 * @BelongsPackage: com.wyx.testspringboot.service.impl
 * @Author: Origami
 * @Date: 2022/9/4 13:09
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean expire(String key, long expire) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, expire, TimeUnit.SECONDS));
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }


    /**
     * 从redis中获取key对应的过期时间;
     * 如果该值有过期时间，就返回相应的过期时间，并且从设置的时间开始递减;因此，当返回值大于0时，表示没有过期
     * 如果该值没有设置过期时间，就返回-1;
     * 如果没有该值或者该值过期了，就返回-2;
     *
     * @param key
     * @return
     */
    @Override
    public Long isExpire(String key) {
        return redisTemplate.opsForValue().getOperations().getExpire(key);
    }
}
