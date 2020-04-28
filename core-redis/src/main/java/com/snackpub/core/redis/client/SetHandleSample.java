package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

/**
 * 操作 Set
 * <p>
 * R集合很类似于 Java 中的 Set
 * 无序、不重复
 *
 * @author snackpub
 * @date 2020/4/28
 */
public class SetHandleSample extends BaseTest {


    @Test
    public void testAdd() {
        redisTemplate.opsForSet().add("testSet2", "a", "b", "c", "d");
        // 获取集合长度
        long size = redisTemplate.opsForSet().size("testSet");
        Assert.assertEquals(3L, size);
    }


    @Test
    public void testGet() {
        Set<String> testSet = redisTemplate.opsForSet().members("testSet");
        System.out.println(testSet);
    }


    @Test
    public void testDel() {
        redisTemplate.opsForSet().remove("testSet", "b", "c");
        Set<String> testSet = redisTemplate.opsForSet().members("testSet");
        // testSet.stream().findFirst().get()
        Assert.assertEquals("a", testSet.toArray()[0]);
    }

    @Test
    public void testUnion() {
        // union 联合多个key组成集合
        Set testSet = redisTemplate.opsForSet().union("testSet", "testSet2");
        Iterator iterator = testSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testUnionAndStore() {
        // unionAndStore 获取2个变量合集后保存到最后一个参数上
        redisTemplate.opsForSet().unionAndStore("testSet", "testSet2","testSet3");
        Set<String> testSet3 = redisTemplate.opsForSet().members("testSet3");
        testSet3.forEach(System.out::println);
    }


    @Test
    public void testIntersectAndStore() {
        // IntersectAndStore 获取2个变量交集后保存到最后一个参数上
        redisTemplate.opsForSet().intersectAndStore("testSet", "testSet2","testSet4");
        Set<String> intersect = redisTemplate.opsForSet().members("testSet4");
        intersect.forEach(System.out::println);
    }



}
