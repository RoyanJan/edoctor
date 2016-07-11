package com.outwit.edoctor.infrastructure.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service("redisService")
public class RedisServiceImpl implements RedisService {


    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean isCached(String key){
        return redisTemplate.hasKey(key);
    }

    public void cacheIt(String key,String value){
        redisTemplate.opsForList().leftPush(key,value);
    }
}
