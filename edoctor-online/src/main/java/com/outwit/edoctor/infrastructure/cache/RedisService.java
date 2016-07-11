package com.outwit.edoctor.infrastructure.cache;

public interface RedisService {

    boolean isCached(String key);
    void cacheIt(String key,String value);
}
