package com.snackpub.core.redis.cache;

import com.snackpub.core.redis.base.BaseInterface;
import com.snackpub.core.redis.base.BaseTest;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisCallback;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

/**
 * 集成Spring Cache
 *
 * @author snackpub
 * @date 2020/5/15 12:45
 */
public class SpringCacheSample extends BaseTest implements BaseInterface {

    @Resource
    private CacheService cacheService;

    @Test
    @Override
    public void add() {

    }

    @Override
    @Test
    public void del() {
        cacheService.cacheEvict(2L);
    }


    /**
     * 测试缓存
     */
    @SneakyThrows
    @Test
    @Override
    public void get() {
        User user = new User(1L, "Snackpub科技公司", "南京理工法学院");
        cacheService.cacheable(user);

        // 取出缓存
        byte[] execute = (byte[]) redisTemplate.execute((RedisCallback) connection ->
                connection.get("user::Snackpub科技公司".getBytes()));

        assert execute != null;

        // 反序列化
        ByteArrayInputStream inputStream = new ByteArrayInputStream(execute);
        ObjectInputStream ois = new ObjectInputStream(inputStream);
        Object obj = ois.readObject();
        if (obj instanceof User) {
            User u = (User) obj;
            System.out.println(u.getCompany());
        }


        // 该代码不会生效
        // getUser(user);
    }

    @SneakyThrows
    @Test
    public void cachePut() {
        User user = new User(2L, null, "哈佛大学");
        // 测试condition 属性
        cacheService.cachePut(user);

//        byte[] bytes = (byte[]) redisTemplate.execute((RedisCallback) connection ->
//                connection.get(("user::" + user.getId()).getBytes())
//        );
//
//        // 反序列化
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
//        ObjectInput ois = new ObjectInputStream(inputStream);
//        Object obj = ois.readObject();
//        if (obj instanceof User) {
//            User u = (User) obj;
//            System.out.println(u);
//        }
    }


    // 内部调用本类的方法，或者外部调用其它类的带有缓存注解的方法，缓存不会生效，写在接口中的实现类可以生效
    @Cacheable(cacheNames = "user:company", key = "#user.company")
    public void getUser(User user) {
        System.out.println("SpringCacheSample.getUser-> execute");
    }


}
