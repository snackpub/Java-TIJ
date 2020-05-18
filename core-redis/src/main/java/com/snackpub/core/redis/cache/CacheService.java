package com.snackpub.core.redis.cache;


public interface CacheService {


    User cacheable(User user);

    String cachePut(User user);

    void cacheEvict(Long key);

}
