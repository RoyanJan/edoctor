package com.outwit.edoctor.infrastructure.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("redisService")
public class RedisServiceImpl implements RedisService {


    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean isCached(final String key) {
        return redisTemplate.hasKey(key);
    }

    public void cacheIt(final String key, final String value, final int timeout) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    public String getValue(final String key){
        return redisTemplate.opsForValue().get(key);
    }
}
