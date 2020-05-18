package com.snackpub.core.redis.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CacheServiceImpl implements CacheService {


    /**
     * 缓存到redis的key为：user::1
     * Cacheable 调用方法时， 返回结果作为value放入缓存
     * <p>
     * unless: 用于否决方法缓存的Spring表达式(SpEL)表达式
     * <p>与 condition 不同，此表达式在方法之后求值
     * 已经被调用，因此可以引用{@code result}。
     * <p>的默认值是{@code ""}，这意味着缓存永远不会被否决。
     *
     * @param user u
     */

    @Override
    @Cacheable(cacheNames = {"user"}, key = "#user.company", unless = "#result!=null")
    public User cacheable(User user) {
        return new User(user.getCompany());
    }

    /**
     * CachePut 调用方法时 方法入参作为value 放入缓存
     *
     * @param user u
     * @return STR
     */
    @Override
    @CachePut(cacheNames = "user", key = "#user.id")
    public String cachePut(User user) {
        log.info("cachePut user successfully");
        return "cachePut user successfully";
    }


    /**
     * cacheEvict 从缓存中删除key 指定的数据
     * cacheNames: 用于缓存清除操作的缓存的名称。名称可以用来确定目标缓存(或缓存)，匹配特定bean定义的限定符值或bean名称
     * key: Spring表达式(SpEL) 用做于键
     * <p>
     * 如下： user::1
     * 其中 user 为cacheNames；1 为 key
     *
     * @param userId key
     */
    @Override
    @CacheEvict(cacheNames = "user", key = "#userId")
    public void cacheEvict(Long userId) {
        log.info("cacheEvict user::" + userId + " success");
    }
}
