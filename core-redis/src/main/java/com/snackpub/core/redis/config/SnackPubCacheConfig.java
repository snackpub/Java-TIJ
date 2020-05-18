package com.snackpub.core.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

/**
 * 启用Spring Cache
 *
 * @author snackpub
 * @date 2020/5/15
 */
@Slf4j
@Component
@EnableCaching
public class SnackPubCacheConfig {

    public SnackPubCacheConfig() {
        log.info("SnackPubCacheConfig init...");
    }

}
