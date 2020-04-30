package com.snackpub.core.redis.lock;

import com.snackpub.core.redis.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * redis 分布式锁测试
 *
 * @author snackpub
 * @date 2020/4/28
 */
@Slf4j
public class LockClientTest extends BaseTest {
      // 为啥要用lua脚本呢？
      // 因为一大坨复杂的业务逻辑，可以通过封装在lua脚本中发送给redis，保证这段复杂业务逻辑执行的原子性。
//    "if (redis.call('exists', KEYS[1]) ==0) then" +
//    "redis. call ('hset', KEYS[1], ARGV[2], 1);" +
//    "redis.call ('pexpire', KEYS[1], ARGV[1]);" +
//    "return nil; " +
//    "end; " +
//    "if (redis. call('hexists', KEYS[1], ARGV[2]) ==1) then " +
//    "redis.call ('hincrby', KEYS[1], ARGV[2], 1); " +
//    "redis.call ('pexpire', KEYS[1], ARGV[1]); " +
//    "return nil; " +
//    "end; " +
//    "return redis. call ('pttl', KEYS[1]);"

    // 如何加锁呢？很简单，用下面的命令：
    // hset myLock
    //    8743c9c0-0795-4907-87fd-6c719a6b4586:1 1
    // 通过这个命令设置一个hash数据结构，这行命令执行后，会出现一个类似下面的数据结构：
        // hset myLock: {"8743c9c0-0795-4907-87fd-6c719a6b4586:1": 1}
        // 上述就代表“8743c9c0-0795-4907-87fd-6c719a6b4586:1”这个客户端对“myLock”这个锁key完成了加锁
        // 接着会执行“pexpire myLock 30000”命令，设置myLock这个锁key的生存时间是30秒
        // 到此为止，ok，加锁完成了

    // 锁互斥机制

    // executeLock 执行完后对mylock成功加锁，此时执行 executeLock2 对mylock执行了同样的一段lua脚本，
    // 尝试设置值，会怎么样呢？
    // 第一个if判断会执行“exists myLock”，发现myLock这个锁key已经存在了。
    // 接着第二个if判断，判断一下，myLock锁key的hash数据结构中，是否包含客户端2的ID，但是明显不是的，因为那里包含的是客户端1的ID
    // 所以，客户端2会获取到pttl myLock返回的一个数字，这个数字代表了myLock这个锁key的剩余生存时间。比如还剩15000毫秒的生存时间。
    // 此时客户端2会进入一个while循环，不停的尝试加锁。

    // watch dog 自动延期机制（看门狗）
        // 客户端1加锁的锁key默认生存时间才30秒，如果超过了30秒，客户端1还想一直持有这把锁，怎么办呢？
        // 简单！只要客户端1一旦加锁成功，就会启动一个watch dog看门狗，他是一个后台线程，
        // 会每隔10秒检查一下，如果客户端1还持有锁key，那么就会不断的延长锁key的生存时间。

    // 可重入加锁机制
        // 那如果客户端1都已经持有了这把锁了，结果可重入的加锁会怎么样呢？
        // 第一个if判断肯定不成立，“exists myLock”会显示锁key已经存在了。
        // 第二个if判断会成立，因为myLock的hash数据结构中包含的那个ID，就是客户端1的那个ID，也就是“8743c9c0-0795-4907-87fd-6c719a6b4586:1”
        // 此时就会执行可重入加锁的逻辑，他会用：
        //  incrby myLock
        //      8743c9c0-0795-4907-87fd-6c71a6b4586:1 1
        // 通过这个命令，对客户端1的加锁次数，累加1
        // 此时myLock数据结构变为下面这样：
        //      myLock: {"8743c9c0-0795-4907-87fd-6c719a6b4586:1": 2}
        // 大家看到了吧，那个myLock的hash数据结构中的那个客户端ID，就对应着加锁的次数

    // 释放锁机制
        // 如果执行lock.unlock()，就可以释放分布式锁，此时的业务逻辑也是非常简单的。
        // 其实说白了，就是每次都对myLock数据结构中的那个加锁次数减1。
        // 如果发现加锁次数是0了，说明这个客户端已经不再持有锁了，此时就会用：
        //“del myLock”命令，从redis里删除这个key
        // 然后呢，另外的客户端2就可以尝试完成加锁了
        // 这就是所谓的分布式锁的开源Redisson框架的实现机制

    // 上述分布式锁的缺点
        // 上面方案最大的问题，如果你对redis  master实例，写入了myLock这种锁key的value，此时会异步赋值给对应的master slave实例
        // 但是这个过程中一旦发master 宕机，准备切换，redis slave变为redis master
        // 接着就会导致，客户端2来尝试加锁的时候，在新的redis master上完成了加锁，而客户端1也以为自己成功加了锁
        // 此时就会导致多个客户端对一个分布式锁完成了加锁
        // 这时系统在业务语义上一定会出现问题，导致各种脏数据的产生

    // 所以这个就是redis cluster，或者是redis master-slave架构的主从异步复制导致的redis分布式锁最大的缺陷：
    // 在redis master实例宕机的时候，可能导致多个客户端同时完成加锁。

    // redis 加锁分类
        // INCR
                //加锁的思路是， key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作进行加一。
                //然后其它用户在执行 INCR 操作进行加一时，如果返回的数大于 1 ，说明这个锁正在被使用当中
        // SETNX
            // SETNX 加锁思路：
                // 如果key不存在，将key设置为value
                // 如果key已存在，则SETNX不作任何动作
        // SET
            // 上面两种方法都有一个问题，会发现，都需要设置 key 过期。那么为什么要设置key过期呢？如果请求执行因为某些原因意外退出了，导致创建了锁但是没有删除锁，那么这个锁将一直存在，以至于以后缓存再也得不到更新。于是乎我们需要给锁加一个过期时间以防不测。
            // 但是借助 Expire 来设置就不是原子性操作了。所以还可以通过事务来确保原子性，但是还是有些问题，所以官方就引用了另外一个，使用 SET 命令本身已经从版本 2.6.12 开始包含了设置过期时间的功能

    @Test
    public void executeLock() {
        DistributedLock distributedLock = new DistributedLock(stringRedisTemplate);
        distributedLock.getLock("mylock", "snackpub", 1L, TimeUnit.MINUTES);
    }

    @Test
    public void executeLock2() {
        DistributedLock distributedLock = new DistributedLock(stringRedisTemplate);
        distributedLock.getLock("mylock", "snackpub2", 1L, TimeUnit.MINUTES);
    }

    @Test
    public void releaseLock() {
        DistributedLock distributedLock = new DistributedLock(stringRedisTemplate);
        boolean lockStat = distributedLock.releaseLock("key", "zs");
        System.out.println(lockStat);
    }

    @Test
    public void getStr() {
        String name = stringRedisTemplate.opsForValue().get("key");
        System.out.println(name);
        stringRedisTemplate.opsForValue().set("key", "zs");
    }


}
