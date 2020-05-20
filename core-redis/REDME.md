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



