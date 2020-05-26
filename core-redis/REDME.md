# Redis cluster

##### 1. 搭建Redis Cluster


##### 2. redis cluster 配置文件配置
```
appendonly yes // 启用追加模式持久性同步策略  
cluster-enabled yes  // 启用redis集群配置  
cluster-config-file nodes-6380.conf // 集群节点配置文件  
cluster-node-timeout 15000 // 节点超时时间  
``` 

##### 3. 开发运维常见的问题
    2.1 集群完整性
        建议把cluster-require-full-coverage设置为no
   >By default Redis Cluster nodes stop accepting queries if they detect there
    is at least an hash slot uncovered (no available node is serving it).
    This way if the cluster is partially down (for example a range of hash slots
    are no longer covered) all the cluster becomes, eventually, unavailable.
    It automatically returns available as soon as all the slots are covered again. 
    However sometimes you want the subset of the cluster which is working,
    to continue to accept queries for the part of the key space that is still
    covered. In order to do so, just set the cluster-require-full-coverage
    option to no.
    
    2.2 带宽消耗
    2.3 Pub/Sub广播
    2.4 集群倾斜
    2.5 集群读写分离
    2.6 数据迁移
    2.7 集群VS单机


##### 4. redis cluster 总结


## redis 性能测试
> 语法：redis-benchmark [option] [option value]

*注意：该命令是在 redis 的目录下执行的，而不是 redis 客户端的内部指令*

**以下实例同时执行 10000 个请求来检测性能**
```
    $redis-benchmark -n 10000 -q
    
    PING_INLINE: 49019.61 requests per second
    PING_BULK: 49261.09 requests per second
    SET: 41666.67 requests per second
    GET: 47846.89 requests per second
    INCR: 40983.61 requests per second
    LPUSH: 37174.72 requests per second
    LPOP: 39062.50 requests per second
    SADD: 50251.26 requests per second
    SPOP: 43290.04 requests per second
    LPUSH (needed to benchmark LRANGE): 36764.70 requests per second
    LRANGE_100 (first 100 elements): 27100.27 requests per second
    LRANGE_300 (first 300 elements): 11668.61 requests per second
    LRANGE_500 (first 450 elements): 8503.40 requests per second
    LRANGE_600 (first 600 elements): 7127.58 requests per second
    MSET (10 keys): 30211.48 requests per second
```
redis 性能测试工具可选参数如下所示：  

| 选项 | 描述 | 默认值 |  
| :----:|:-----:|:----:|
| -h | 指定服务器主机 | 127.0.0.1 |
| -p | 指定服务器端口 | 6379 |
| -s | 指定服务器 socket | |
| -c | 指定并发连接数	 |50|
| -n | 指定请求数 | 10000 |
| -d | 以字节的形式指定 SET/GET 值的数据大小 |2 |
| -k | 1=keep alive 0=reconnect | 1 |
| -r | SET/GET/INCR 使用随机 key, SADD 使用随机值 | |
| -P | 通过管道传输 <numreq> 请求	 |1|
| -q |强制退出 redis。仅显示 query/sec 值 |  |
| --csv | 以 CSV 格式输出 | |
| -l | 生成循环，永久执行测试||
| -t | 仅运行以逗号分隔的测试命令列表。||
| -I | Idle 模式。仅打开 N 个 idle 连接并等待。||

## redis sharded 分区
1. 何为redis分区？

2. 分区的优势

3. 分区的不足

***
## 分区类型
1. 范围分区

2. 哈希分区

## 常用命令


## 数据模型
**string** 是最简单的类型，一个key对应一个value。
string 类型是二进制安全的。意思是 redis 的 string 可以包含任何数据，比如 jpg 图片 或者序列化的
对象。从内部是西安来看 string 可以看作 byte 数组，最大上限是 1G 字节，下面是 string 的定义：
```
struct sdshdr {
    long len;
    long free;
    char buf[];
};
```
len 是 buf 数组的长度。  
free 是数组中剩余可用字节数，由此可以理解为什么string 类型是二进制安全的了，因为它本质上就是个byte数组。  
buf 是个 char 数组，用于存储实际的字符串内容，其实 char 跟 c# 中的byte是等价的，都是一个字节。  
另外 string 类型可以被部分命令按 int 处理.比如 incr 等命令，如果只用 string 类型，redis 就
可以被看作加上持久化特性的 memcached。当然 redis 对 string 类型的操作比 memcached 还 是多很多的，具体操作方法如下：

**hashes**  是一个 string 类型的 field 和 value 的映射表.它的添加、删除操作都是 O(1)（平均）。
           hash 特别适合用于存储对象。相较于将对象的每个字段存成单个 string 类型。将一个对象存
           储在 hash 类型中会占用更少的内存，并且可以更方便的存取整个对象。省内存的原因是新
           建一个 hash 对象时开始是用 zipmap（又称为 small hash）来存储的。这个 zipmap 其实并不
           是 hash table，但是 zipmap 相比正常的 hash 实现可以节省不少 hash 本身需要的一些元数据
           存储开销。尽管 zipmap 的添加，删除，查找都是 O(n)，但是由于一般对象的 field 数量都不
           太多。所以使用 zipmap 也是很快的,也就是说添加删除平均还是 O(1)。如果 field 或者 value
           的大小超出一定限制后，Redis 会在内部自动将 zipmap 替换成正常的 hash 实现. 这个限制可
           以在配置文件中指定
           hash-max-zipmap-entries 64 #配置字段最多 64 个
           hash-max-zipmap-value 512 #配置 value 最大为 512 字节

**lists**   是一个链表结构，主要功能是 push、pop、获取一个范围的所有值等等，操作中 key 理
            解为链表的名字。
            Redis 的 list 类型其实就是一个每个子元素都是 string 类型的双向链表。链表的最大长度是(2
            的 32 次方)。我们可以通过 push,pop 操作从链表的头部或者尾部添加删除元素。这使得 list
            既可以用作栈，也可以用作队列。
            有意思的是 list 的 pop 操作还有阻塞版本的，当我们[lr]pop 一个 list 对象时，如果 list 是空，
            或者不存在，会立即返回 nil。但是阻塞版本的 b[lr]pop 可以则可以阻塞，当然可以加超时时
            间，超时后也会返回 nil。为什么要阻塞版本的 pop 呢，主要是为了避免轮询。举个简单的
            例子如果我们用 list 来实现一个工作队列。执行任务的 thread 可以调用阻塞版本的 pop 去获
            取任务这样就可以避免轮询去检查是否有任务存在。当任务来时候工作线程可以立即返回，
            也可以避免轮询带来的延迟。


## 数据结构
redis 的外围由一个键/值映射的字段构成。与其它非关系型数据库主要不同在于：redis中值的类型不仅限于字符串，
还支持如下抽象数据类型：
 * 字符串列表
 * 无序不重复的字符串集合
 * 有序不重复的字符串集合
 * 键值都为字符串的哈希表      

值的类型决定值本身支持的操作。redis支持不同无序，有序的列表，无序有序的集合间的交集，并集等高级服务器端原子操作


## 存储
redis 使用了两种文件格式：*全量数据*和*增量请求*。
* 全量数据格式是把内存中的数据写入磁盘，便于下次读取文件进行加载。
* 增量请求文件则是把内存中的数据序列化为操作请求，用于读取文件进行replay得到数据，序列化的操作包括
SET,RPUSH,SADD,ZADD.
* Redis的存储分为内存存储，磁盘存储和log文件三部分，配置文件中有三个参数对其进行配置。
  * save seconds updates，save配置，指出在多长时间内，有多少次更新操作，就将数据同步到数据文件。
这个可以多个条件配合，比如默认配置文件中的设置，就设置了三个条件.
  * appendonly yes/no,appendonly 配置,指出是否每次更新操作后进行日志记录,如果不开启,可能在断电
  时导致一段时间内的数据丢失.因为redis本身同步数据文件是按上面的save条件进行同步的,所有的数据在
  一段时间内只会存于内存中.
  * appendfsync no/always/everysec, appendfsync配置,no 配置等操作系统进行数据缓存同步到磁盘
   ,always表示每次更新操作后手动调用fsync()将数据写到磁盘,everysec表示每秒同步一次.



