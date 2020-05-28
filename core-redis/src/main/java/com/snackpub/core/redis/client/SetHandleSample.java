package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * set 是集合，和我们数学中的集合概念相似，对集合的操作有添加删除元素，有对多个集合
 * 求交并差等操作，操作中 key 理解为集合的名字。
 * Redis 的 set 是 string 类型的无序集合。set 元素最大可以包含(2 的 32 次方)个元素。
 * set 的是通过 hash table 实现的，所以添加、删除和查找的复杂度都是 O(1)。hash table 会随
 * 着添加或者删除自动的调整大小。需要注意的是调整 hash table 大小时候需要同步（获取写
 * 锁）会阻塞其他读写操作，可能不久后就会改用跳表（skip list）来实现，跳表已经在 sorted
 * set 中使用了。关于 set 集合类型除了基本的添加删除操作，其他有用的操作还包含集合的
 * 取并集(union)，交集(intersection)，差集(difference)。通过这些操作可以很容易的实现 sns
 * 中的好友推荐和 blog 的 tag 功能。下面详细介绍 set 相关命令:
 *
 * @author snackpub
 * @date 2020/4/28
 * @date 2020/5/27
 * @date 2020/5/28
 */
public class SetHandleSample extends BaseTest {


    @Test
    public void testAdd() {
        // 向名称为 key 的 set 中添加元素   sAdd
        redisTemplate.opsForSet().add("testSet", "a", "b", "c", "d", "d");
        // 获取集合长度
        long size = redisTemplate.opsForSet().size("testSet");
        Assert.assertEquals(4L, size);
    }


    @Test
    public void testGet() {
        // 根据 sMembers 来查看 testSet 中的所有元素
        Set<String> testSet = redisTemplate.opsForSet().members("testSet");
        System.out.println(testSet);
    }


    @Test
    public void testDel() {
        // srem  删除名称为 key 的 set 中的元素
        redisTemplate.opsForSet().remove("testSet", "d");
        Set<String> testSet = redisTemplate.opsForSet().members("testSet");
        // testSet.stream().findFirst().get()
        assert testSet != null;
        Assert.assertEquals("c", testSet.toArray()[testSet.size() - 1]);
    }

    @Test
    public void testSmove() {
        // smove 从第一个 key 对应的 set 中移除 member 并添加到第二个对应 set 中
        boolean bool = redisTemplate.opsForSet().move("testSet", "c", "testSetSmove");
        Assert.assertTrue(bool);
        Set<String> testSetSmove = redisTemplate.opsForSet().members("testSetSmove");
        assert testSetSmove != null;
        Optional<String> first = testSetSmove.stream().findFirst();
        Assert.assertTrue(first.isPresent());
        System.out.println(first.get());

    }

    @Test
    public void testSpop() {
        // 随机返回并删除名称为 key 的 set 中一个元素   spop
        redisTemplate.opsForSet().add("testSet", Stream.of("d", "e", "f").collect(Collectors.toList()));

        Object spopValue = redisTemplate.opsForSet().pop("testSet");
        System.out.println("remove Value: " + spopValue);
    }


    @Test
    public void testSdiff() {
        // 返回所有给定 key 与第一个 key 的差集   sdiff
//        redisTemplate.opsForSet().add("testSet2", "a", "d", "e", "f");
//
//        Set<String> difference = redisTemplate.opsForSet().difference("testSet2", "testSet");
//
//        assert difference != null;
//        // testSet2 中d,e,f 是 testSet 没有的，而 b,c是testSet的，所以只打印 d,e,f,
//        difference.forEach(System.out::println);

        // 我们也可以将 testSet2 和 testSet 换个顺序来看一下结果:
        Set<String> difference2 = redisTemplate.opsForSet().difference("testSet", "testSet2");

        // testSet: a b c g
        // testSet2: a b c d f e
        // print: g
        assert difference2 != null;
        difference2.forEach(System.out::println);

    }


    @Test
    public void testSdiffStore() {
        // 返回所有给定 key 与第一个 key 的差集，并将结果存为另一个 key   sdiffstore
        Long andStore = redisTemplate.opsForSet().differenceAndStore("testSet", "testSet2", "testSet3");
        System.out.println(andStore);
        Set<String> testSet3 = redisTemplate.opsForSet().members("testSet3");
        assert testSet3 != null;
        testSet3.forEach(System.out::println);

    }

    @Test
    public void testSinter() {
        // 返回所有给定 key 的交集   page: sinter
        Set<String> intersect = redisTemplate.opsForSet().intersect("testSet", "testSet2");
        assert intersect != null;
        intersect.forEach(System.out::println);
    }

    @Test
    public void testSinterStore() {
        // 返回所有给定 key 的交集，并将结果存为另一个 key  page: sinterstore
        Long aLong = redisTemplate.opsForSet().intersectAndStore("testSet", "testSet2", "testSet3");
        System.out.println("testSet3 Size: " + aLong);
        Set<String> testSet3 = redisTemplate.opsForSet().members("testSet3");
        assert testSet3 != null;
        testSet3.forEach(System.out::println);
    }

    @Test
    public void testIntersectAndStore() {
        // IntersectAndStore 获取2个变量交集后保存到最后一个参数上
        redisTemplate.opsForSet().intersectAndStore("testSet", "testSet2", "testSet4");
        Set<String> intersect = redisTemplate.opsForSet().members("testSet4");
        assert intersect != null;
        intersect.forEach(System.out::println);
    }

    @Test
    public void testUnion() {
        // sunion 联合多个key组成集合, 不重复
        Set testSet = redisTemplate.opsForSet().union("testSet", "testSet2");
        assert testSet != null;
        for (Object o : testSet) {
            System.out.println(o);
        }
    }

    @Test
    public void testUnionAndStore() {
        // unionAndStore 获取2个变量合集后保存到最后一个参数上
        redisTemplate.opsForSet().unionAndStore("testSet", "testSet2", "testSet3");
        Set<String> testSet3 = redisTemplate.opsForSet().members("testSet3");
        assert testSet3 != null;
        testSet3.forEach(System.out::println);
    }


    @Test
    public void testScard() {
        // scard 返回名称为 key 的 set 的元素个数
        Long len = redisTemplate.opsForSet().size("testSet3");
        System.out.println(len);

    }

    @Test
    public void testSismember() {
        // sIsMember 测试 member 是否是名称为 key 的 set 的元素
        Boolean member = redisTemplate.opsForSet().isMember("testSetSmove", "c");
        Assert.assertTrue(member);
    }

    // srandmember

}
