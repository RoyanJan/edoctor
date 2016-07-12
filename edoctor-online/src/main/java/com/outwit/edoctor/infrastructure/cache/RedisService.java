package com.outwit.edoctor.infrastructure.cache;

public interface RedisService {

    boolean isCached(String key);

    void cacheIt(String key, String value, int timeout);

    String getValue(final String key);
}
