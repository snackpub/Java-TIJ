package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import org.junit.Test;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

/**
 * sorted set 是 set 的一个升级版本，它在 set 的基础上增加了一个顺序属性，这一属性在添加
 * 修改元素的时候可以指定，每次指定后，zset 会自动重新按新的值调整顺序。可以理解为有
 * 两列的 mysql 表，一列存 value，一列存顺序。操作中 key 理解为 zset 的名字。
 * 和 set 一样 sorted set 也是 string 类型元素的集合，不同的是每个元素都会关联一个 double
 * 类型的 score。sorted set 的实现是 skip list 和 hash table 的混合体
 * 当元素被添加到集合中时，一个元素到 score 的映射被添加到 hash table 中，所以给定一个
 * 元素获取 score 的开销是 O(1),另一个 score 到元素的映射被添加到 skip list，并按照 score 排
 * 序，所以就可以有序的获取集合中的元素。添加，删除操作开销都是 O(log(N))和 skip list 的
 * 开销一致,redis 的 skip list 实现用的是双向链表,这样就可以逆序从尾部取元素。sorted set 最
 * 经常的使用方式应该是作为索引来使用.我们可以把要排序的字段作为 score 存储，对象的 id
 * 当元素存储。下面是 sorted set 相关命令
 *
 * @author snackpub
 * @date 2020/4/28
 * @date 2020/4/29 22:34
 * @date 2020/5/28 21:53
 */
public class ZsetHandleSample extends BaseTest {


    @Test
    public void testAdd() {
        //  向名称为 key 的 zset 中添加元素 member，score 用于排序。如果该元素已经存在，则根据
        //score 更新该元素的顺序

        redisTemplate.opsForZSet().add("Zset", "testZset1", 9.45);
        redisTemplate.opsForZSet().add("Zset", "testZset2", 9.46);
        // 不管score大小，后者都会替换前者
        redisTemplate.opsForZSet().add("Zset", "testZset3", 9.50);
        redisTemplate.opsForZSet().add("Zset", "testZset3", 9.49);

        redisTemplate.opsForZSet().add("Zset", "testZset6", 9.48);

        // zrange 通过索引之间返回有序集合指定区间的大小
        Set<String> zset = redisTemplate.opsForZSet().range("Zset", 0, 10);
        assert zset != null;
        // 输出排序后的值
        zset.forEach(System.out::println);
    }


    @Test
    public void testGet() {
        // zrange
        Set<String> zset = redisTemplate.opsForZSet().range("Zset", 0, 1);
        assert zset != null;
        zset.forEach(System.out::println);
    }


    @Test
    public void testZincrby() {
        // zincrby 如果在名称为 key 的 zset 中已经存在元素 member，则该元素的 score 增加 increment；否则
        //向集合中添加该元素，其 score 的值为 increment
        Double incrementScore = redisTemplate.opsForZSet().incrementScore("Zset", "testZset1", 1);
        // 返回该元素的score 增量值
        System.out.println(incrementScore);
    }

    @Test
    public void testzrank() {
        // 返回名称为 key 的 zset 中 member 元素的排名(按 score 从小到大排序)即下标
        Long descIndex = redisTemplate.opsForZSet().rank("Zset", "testZset1");
        System.out.println(descIndex);

        // 返回名称为 key 的 zset 中 member 元素的排名(按 score 从大到小排序)即下标
        Long ascIndex = redisTemplate.opsForZSet().reverseRank("Zset", "testZset1");
        System.out.println(ascIndex);
    }

    @Test
    public void testZrevrange() {
        // 返回名称为 key 的 zset（按 score 从大到小排序）中的 index 从 start 到 end 的所有元素 zRevRange
        Set<String> zset = redisTemplate.opsForZSet().reverseRange("Zset", 0, -1);
        assert zset != null;
        for (String s : zset) {
            System.out.println(s);
        }

        // zrangebyscore 返回集合中 score 在给定区间的元素 zRangeByScore  升序
        Set<String> zset1 = redisTemplate.opsForZSet().rangeByScore("Zset", 0, 100);
        assert zset1 != null;
        zset1.forEach(System.out::println);


        // zRangeByScoreWithScores 从已排序的集合中获取{@link元组}s集合，其中score介于{@code min}和{@code max}之间
        Set<ZSetOperations.TypedTuple> zset2 = redisTemplate.opsForZSet().rangeByScoreWithScores("Zset", 0, 100);
        assert zset2 != null;
        zset2.forEach(n -> {
            System.out.println(n.getScore() + " " + n.getValue());
        });
    }

    @Test
    public void testDel() {
        // zrem 删除名称为 key 的 zset 中的元素 member
        redisTemplate.opsForZSet().remove("Zset", "testZset");
        Set<String> range = redisTemplate.opsForZSet().range("Zset", 0, -1);
        assert range != null;
        range.forEach(System.out::println);
    }

    @Test
    public void testZremrangebyrank() {
        // zRemRangeByScore 删除集合中排名在给定区间的元素
        redisTemplate.opsForZSet().removeRangeByScore("Zset", 0, 2);

        Set<ZSetOperations.TypedTuple> zset2 = redisTemplate.opsForZSet().rangeByScoreWithScores("Zset", 0, 100);
        assert zset2 != null;
        zset2.forEach(n -> System.out.println(n.getScore() + " " + n.getValue()));
    }

    @Test
    public void testGetScore() {
        Set zset = redisTemplate.opsForZSet().rangeByScore("Zset", 9.0, 10.0);
        zset.forEach(System.out::println);

//        Set zset1 = redisTemplate.opsForZSet().rangeWithScores("Zset", 0, 3);
//        zset1.forEach(System.out::println);
    }

    @Test
    public void testZscan() {
        // 使用scan命令对redis中指定key进行扫描;匹配获取键值对，ScanOptions.NONE为获取全部键值对；
        Cursor<ZSetOperations.TypedTuple<Object>> cursor = redisTemplate.opsForZSet().scan("Zset", ScanOptions.NONE);
        while (cursor.hasNext()) {
            ZSetOperations.TypedTuple<Object> typedTuple = cursor.next();
            System.out.println(typedTuple.getValue() + " " + typedTuple.getScore());
        }
//        System.out.println("-------------");
//        ScanOptions.ScanOptionsBuilder scanOption = ScanOptions.scanOptions().match("t*");
//        Cursor<ZSetOperations.TypedTuple> tupleCursor = redisTemplate.opsForZSet().scan("Zset", scanOption.build());
//        while (tupleCursor.hasNext()) {
//            ZSetOperations.TypedTuple next = tupleCursor.next();
//            System.out.println(next.getValue() + " " + next.getScore());
//        }
    }

    // zremrangebyscore  zremrangebyrank  zscore zcount      zcard

}
