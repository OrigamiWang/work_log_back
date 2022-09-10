package com.wyx.testspringboot.service;

/**
 * @BelongsProject: testSpringBoot
 * @BelongsPackage: com.wyx.testspringboot.service
 * @Author: Origami
 * @Date: 2022/9/4 13:07
 */
public interface RedisService {

    void set(String key, String value);

    String get(String key);

    boolean expire(String key, long expire);

    void remove(String key);

     /**
      * @param delta 自增步长
      */
    Long increment(String key, long delta);

    Long isExpire(String key);

}
