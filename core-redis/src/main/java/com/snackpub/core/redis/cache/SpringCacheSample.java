package com.snackpub.core.redis.cache;

import com.snackpub.core.redis.base.BaseInterface;
import com.snackpub.core.redis.base.BaseTest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import org.springframework.cache.annotation.Cacheable;

/**
 * 集成Spring Cache
 *
 * @author snackpub
 * @date 2020/5/15 12:45
 */
public class SpringCacheSample extends BaseTest implements BaseInterface {


    @Test
    @Override
    public void add() {

    }

    @Override
    public void del() {

    }

    @Test
    @Override
    public void get() {
        User user = new User("Snackpub科技公司","南京理工法学");
        getUser(user);
    }


    @Cacheable(cacheNames = "user:edu", key = "#user.edu")
    public void getUser(User user) {
        System.out.println("缓存成功： " + user);
    }

    @AllArgsConstructor
    @Data
    private class User {
        private String company;
        private String edu;
    }
}
