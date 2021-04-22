# Redis 

##### Redis Cluster
Redis 主从复制配置和使用都非常的简单。通过主从复制可以允许多个 slave server 拥有和 master server
相同的数据库副本。

***主从复制的特点***  
（1）master 可以拥有多个 slave  
（2）多个 slave 可以连接同一个 master 外，还可以连接到其他 slave  
（3）主从复制不会阻塞 master，在同步数据时，master 可以继续处理 client 请求  
（4）提高系统的伸缩性

***主从复制的过程***  
当配置好 slave 后，slave 与 master 建立连接，然后发送 sync 命令。无论是第一次连接还是
重新连接，master 都会启动一个后台进程，将数据库快照保存到文件中，同时 master 主进
程会开始收集新的写命令并缓存。后台进程完成写文件后，master 就发送文件给 slave，slave
将文件保存到硬盘上，再加载到内存中，接着 master 就会把缓存的命令转发给 slave，后续
master 将收到的写命令发送给 slave。如果 master 同时收到多个 slave 发来的同步连接命令，
master 只会启动一个进程来写数据库镜像，然后发送给所有的 slave。

***如何配置***  
配置 slave 服务器很简单，只需要在 slave 的配置文件中加入如下配置  
```slaveof 192.168.1.1 6379 #指定 master 的 ip 和端口```

下面我们做一个实验来演示如何搭建一个主从环境:    
```
#slaveof <masterip> <masterport> 
slaveof localhost 6379 
 ```  
我们在一台机器上启动主库(端口 6379)，从库(端口 6378)  
启动后主库控制台日志如下:   
```
[7064] 09 Aug 20:13:20 * Slave ask for synchronization  
[7064] 09 Aug 20:13:20 * Starting BGSAVE for SYNC  
[7064] 09 Aug 20:13:20 * Background saving started by pid 7067  
```

启动后从库控制台日志如下: 
```
[7066] 09 Aug 20:13:20 * Connecting to MASTER...  
[7066] 09 Aug 20:13:20 * MASTER <-> SLAVE sync started: SYNC sent  
[7066] 09 Aug 20:13:20 * MASTER <-> SLAVE sync: receiving 10 bytes from master  
[7066] 09 Aug 20:13:20 * MASTER <-> SLAVE sync: Loading DB in memory  
[7066] 09 Aug 20:13:20 * MASTER <-> SLAVE sync: Finished with success  
```


* 由多个Redis服务器组成的分布式网络服务集群  
* 集群之中有多个Master主节点，每一个主节点都可读可写  
* 节点之间会互相通信，两两相连
* Redis集群无中心节点

***分区和槽slot***  
redis cluster中有一个16384（2^4 * 2^10）长度的槽的概念。通过哈希算法再加上取模运算可以将一个值固定地映射到某个区间，区间由连续的slot组成。   
redis cluster采用虚拟槽分区，所有的键根据哈希函数(CRC16[key]&16383)映射到0－16383槽内，共16384个槽位，每个节点维护部分槽及槽所映射的键值数据  
哈希函数: Hash()=CRC16[key]&16383 按位与  
redis用虚拟槽分区原因：解耦数据与节点关系，节点自身维护槽映射关系，分布式存储

***安装ruby环境***   
【Ruby install】    
【redis-trib.rb，此脚本是ruby脚本，它依赖ruby环境】  


***redis cluster 配置文件配置***  
[Windows]  
redis.windows.conf  
[Linux]  
redis.conf
```
port xxxx // 不同的实例端口
[daemonized  yes] # linux 模式下可配置
appendonly yes // 启用追加模式持久性同步策略  
cluster-enabled yes  // 启用redis集群配置  
cluster-config-file nodes-6380.conf // 集群节点配置文件  
cluster-node-timeout 15000 // 节点超时时间  
``` 

start.bat 配置
```
@echo on
title redis-server
color 0a
redis-server.exe redis.windows.conf
@pause
```

配置不正确会运行失败，检查空格跟配置的参数是否正确
```
执行 redis-server.exe redis.windows.conf 报配置文件参数错误
invalid argument during startup:unknown conf file parameter : 
```
其它配置，具体配置可查看文件本身注释，按需而配

***分布式伪集群:*** 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 127.0.0.1:6385   
F:\redis>```ruby redis-trib.rb create --replicas 1 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 127.0.0.1:6385```

***redis-cli设置集群模式***    

使用 -c 来启动集群实例的客户端。不然使用普通客户端set数据报错  
```redis-cli -c -h 127.0.0.1 -p 6381 [-a password]```  

127.0.0.1:6380> ```info replication```   # 查看集群信息  
127.0.0.1:6380> ```cluster nodes```   # 查看集群节点信息  
127.0.0.1:6380> ```cluster info```  # 查看集相关状态  

Redis 集群会把数据存在一个 master 节点，然后在这个 master 和其对应的salve 之间进行数据同步。当读取数据时，也根据一致性哈希算法到对应的 master 节点获取数据。只有当一个master 挂掉之后，才会启动一个对应的 salve 节点，充当 master 
必须要3个或以上的主节点，否则在创建集群时会失败，并且当存活的主节点数小于总节点数的一半时，整个集群就无法提供服务了
>Creating cluster  创建集群  
*** ERROR: Invalid configuration for cluster creation. 错误:集群创建的配置无效。    
*** Redis Cluster requires at least 3 master nodes. Redis集群需要至少3个主节点。   
*** This is not possible with 5 nodes and 1 replicas per node. 这对于每个节点5个节点和1个副本是不可能的。   
*** At least 6 nodes are required. 至少需要6个节点。

1fa2ad206ad3c4b1ce11e15c19da4c8199ea8010 127.0.0.1:6384 slave 90d953db8fbcc0ca999429683d6667b17d11f824  
1fa2ad206ad3c4b1ce11e15c19da4c8199ea8010当前节点的ID标识,slave表示从节点，master标识主节点, 90d953db8fbcc0ca999429683d6667b17d11f824表示指向的master的ID标识
```
127.0.0.1:6380>  cluster nodes
1fa2ad206ad3c4b1ce11e15c19da4c8199ea8010 127.0.0.1:6384 slave 90d953db8fbcc0ca999429683d6667b17d11f824 0 1619057422784 2 connected
0d7279d8c21537213958c8d28b50dd85920a3e51 127.0.0.1:6379 master - 0 1619057426006 1 connected 0-5460
90d953db8fbcc0ca999429683d6667b17d11f824 127.0.0.1:6380 myself,master - 0 0 2 connected 5461-10922
86e5aef1164f1bf0543001326df9eb10c39eb0b2 127.0.0.1:6381 master - 0 1619057428124 3 connected 10923-16383
0cbe56e08adec063e5d44191a9060f3234cc3cd8 127.0.0.1:6382 slave 86e5aef1164f1bf0543001326df9eb10c39eb0b2 0 1619057427041 3 connected
a533dbedebf3107d770ef018c59adc1a00af6ac1 127.0.0.1:6383 slave 0d7279d8c21537213958c8d28b50dd85920a3e51 0 1619057424937 5 connected
```


***集群高可用***

a、一个集群里面有M1、M2、M3三个节点，其中节点 M1包含 0 到 5500号哈希槽，节点M2包含5501 到 11000 号哈希槽，节点M3包含11001 到 16384号哈希槽。如果M2宕掉了，就会导致5501 到 11000 号哈希槽不可用，从而使整个集群不可用。

b、一个集群里面有M1-S1、M2-S2、M3-S3六个主从节点，其中节点 M1包含 0 到 5500号哈希槽，节点M2包含5501 到 11000 号哈希槽，节点M3包含11001 到 16384号哈希槽。如果是M2宕掉，集群便会选举S2为新节点继续服务，整个集群还会正常运行。当M2、S2都宕掉了，这时候集群就不可用了。   
当M2、S2都宕掉，其它集群抛出错误信息  
```  
[95460] 03 Jun 20:21:23.592 * Marking node c517a6f63e1b958f73ee545fb05155a00178e 1e6 as failing (quorum reached). (标记节点c517a6f63e1b958f73ee545fb05155a00178e1e6失败(达到法定人数))  
[95460] 03 Jun 20:21:23.602 # Cluster state changed: fail (集群状态改变:失败)  
其它存活节点客户端在set数据抛出异常：  
127.0.0.1:6382> set snack 'lsjg'
(error) CLUSTERDOWN The cluster is down (错误)群集失败
```  

***开发运维常见的问题***  
   * 集群完整性  
        * 建议把``` cluster-require-full-coverage``` 设置为``` no``` 
   >By default Redis Cluster nodes stop accepting queries if they detect there
    is at least an hash slot uncovered (no available node is serving it).
    This way if the cluster is partially down (for example a range of hash slots
    are no longer covered) all the cluster becomes, eventually, unavailable.
    It automatically returns available as soon as all the slots are covered again. 
    However sometimes you want the subset of the cluster which is working,
    to continue to accept queries for the part of the key space that is still
    covered. In order to do so, just set the cluster-require-full-coverage
    option to no.
    
   * 带宽消耗
   * Pub/Sub广播
   * 集群倾斜
   * 集群读写分离
   * 数据迁移
   * 集群VS单机

***常用数据分布方式***
  * 顺序分布
 
  * 常用数据分布方式之哈希分布
   
  * 一致性哈希分区
   
  * 虚拟槽分区
   
  * 顺序分布与哈希分布的对比

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

3. 虚拟槽分区  
	虚拟槽分区是Redis Cluster采用的分区方式  
	预设虚拟槽，每个槽就相当于一个数字，有一定范围。每个槽映射一个数据子集，一般比节点数大  
	Redis Cluster中预设虚拟槽的范围为0到16383   
	1.把16384槽按照节点数量进行平均分配，由节点进行管理  
	2.对每个key按照CRC16规则进行hash运算  
	3.把hash结果对16383进行取余   
	4.把余数发送给Redis节点   
	5.节点接收到数据，验证是否在自己管理的槽编号的范围  
	    如果在自己管理的槽编号范围内，则把数据保存到数据槽中，然后返回执行结果  
	    如果在自己管理的槽编号范围外，则会把数据发送给正确的节点，由正确的节点来把数据保存在对应的槽中  
	需要注意的是：Redis Cluster的节点之间会共享消息，每个节点都会知道是哪个节点负责哪个范围内的数据槽  
	虚拟槽分布方式中，由于每个节点管理一部分数据槽，数据保存到数据槽中。当节点扩容或者缩容时，对数据槽进行重新分配迁移即可，数据不会丢失。  
	虚拟槽分区特点：   
	使用服务端管理节点，槽，数据：例如Redis Cluster  
	可以对数据打散，又可以保证数据分布均匀  
	
## 常用命令


## 性能
相比需要依赖磁盘记录每个更新的数据库，基于内存的特性无疑给 Redis 带来了非常优秀的
性能。读写操作之间有显著的性能差异。

## 数据模型
**string** 是最简单的类型，一个key对应一个value。
string 类型是二进制安全的。意思是 redis 的 string 可以包含任何数据，比如 jpg 图片 或者序列化的
对象。从内部实现来看 string 可以看作 byte 数组，最大上限是 1G 字节，下面是 string 的定义：
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
            
**sets** 是集合，和我们数学中的集合概念相似，对集合的操作有添加删除元素，有对多个集合
        求交并差等操作，操作中 key 理解为集合的名字。
        Redis 的 set 是 string 类型的无序集合。set 元素最大可以包含(2 的 32 次方)个元素。
        set 的是通过 hash table 实现的，所以添加、删除和查找的复杂度都是 O(1)。hash table 会随
        着添加或者删除自动的调整大小。需要注意的是调整 hash table 大小时候需要同步（获取写
        锁）会阻塞其他读写操作，可能不久后就会改用跳表（skip list）来实现，跳表已经在 sorted 
        set 中使用了。关于 set 集合类型除了基本的添加删除操作，其他有用的操作还包含集合的
        取并集(union)，交集(intersection)，差集(difference)。通过这些操作可以很容易的实现 sns
        中的好友推荐和 blog 的 tag 功能。           
            
**sorted sets** 是 set 的一个升级版本，它在 set 的基础上增加了一个顺序属性，这一属性在添加
                修改元素的时候可以指定，每次指定后，zset 会自动重新按新的值调整顺序。可以理解为有
                两列的 mysql 表，一列存 value，一列存顺序。操作中 key 理解为 zset 的名字。
                和 set 一样 sorted set 也是 string 类型元素的集合，不同的是每个元素都会关联一个 double
                类型的 score。sorted set 的实现是 **skip list** 和 hash table 的混合体。
                当元素被添加到集合中时，一个元素到 score 的映射被添加到 hash table 中，所以给定一个
                元素获取 score 的开销是 O(1),另一个 score 到元素的映射被添加到 skip list，并按照 score 排
                序，所以就可以有序的获取集合中的元素。添加，删除操作开销都是 O(log(N))和 skip list 的
                开销一致,redis 的 skip list 实现用的是双向链表,这样就可以逆序从尾部取元素。sorted set 最
                经常的使用方式应该是作为索引来使用.我们可以把要排序的字段作为 score 存储，对象的 id
                当元素存储。

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
  ```
  	save 900 1
	save 300 10
	save 60 10000
  ```
  * appendonly yes/no,appendonly 配置,指出是否每次更新操作后进行日志记录,如果不开启,可能在断电
  时导致一段时间内的数据丢失.因为redis本身同步数据文件是按上面的save条件进行同步的,所有的数据在
  一段时间内只会存于内存中.
  * appendfsync no/always/everysec, appendfsync配置,no 配置等操作系统进行数据缓存同步到磁盘
   ,always表示每次更新操作后手动调用fsync()将数据写到磁盘,everysec表示每秒同步一次.

## redis 管道

**普通模式**



**pipeline 管道模式**可以在服务端未响应时，客户端可以继续向服务端发送请求，并最终一次性读取所有服务端的响应  
   ***Pipeline*** 是redis提高吞吐量的机制，适用于多key读写场景，会占用大量内存（用pipeline方式打包发送命令，redis必须
  在处理完所有命令前先缓存所有命令的处理结果。打包的越多，缓存消耗的内存也越多）.   
  使用pipeline性能提升的主要原因是 ***TCP*** 连接中减少了“交互往返” 时间。   
  pipeline 期间独占 **链接**,此期间将不能进行非“管道”类型的其他操作，直到 pipeline 关闭；
  如果你的 pipeline 的指令集很庞大，为了不干扰链接中的其他操作，*你可以为 pipeline 操作新建 Client 链接，
  让 pipeline 和其他正常操作分离在2个 client 中*。不过 pipeline 事实上所能容忍的操作个数，
  和 socket-output 缓冲区大小/返回结果的数据尺寸都有很大的关系；
  同时也意味着每个 redis-server 同时所能支撑的 pipeline 链接的个数，也是有限的，
  这将受限于 server 的物理内存或网络接口的缓冲能力。    
  ***Pipeline*** 的实现原理是队列，而队列的原理是先进先出，这样就保证数据的循序性。  
  ***Pipeline*** 的默认的同步的个数为53个，也就是说arges中累加到53条数据时会把数据提交。  
  ***Pipeline*** 不适用于实时性高的场景  
  ***Pipeline***：管道本身就是能够承载流式数据的一个长链路  
  
  
## 使用场景
1. 取最新 N 个数据的操作
    * 比如典型的取你网站的最新文章，通过下面方式，我们可以将最新的 5000 条评论的 ID 放在
      Redis 的 List 集合中，并将超出集合部分从数据库获取。
2. 排行榜应用，取 TOP N 操作
    * 这个需求与上面需求的不同之处在于，前面操作以时间为权重，这个是以某个条件为权重，
      比如按顶的次数排序，这时候就需要我们的 sorted set 出马了，将你要排序的值设置成 sorted 
      set 的 score，将具体的数据设置成相应的 value，每次只需要执行一条 ZADD 命令即可
3. 需要精准设定过期时间的应用
    * 比如你可以把上面说到的 sorted set 的 score 值设置成过期时间的时间戳，那么就可以简单
      地通过过期时间排序，定时清除过期数据了，不仅是清除 Redis 中的过期数据，你完全可以
      把 Redis 里这个过期时间当成是对数据库中数据的索引，用 Redis 来找出哪些数据需要过期
      删除，然后再精准地从数据库中删除相应的记录
4. 计数器应用
    * Redis 的命令都是原子性的，你可以轻松地利用 INCR，DECR 命令来构建计数器系统。
5. Uniq 操作，获取某段时间所有数据排重值
    * 这个使用 Redis 的 set 数据结构最合适了，只需要不断地将数据往 set 中扔就行了，set 意为
      集合，所以会自动排重
6. 实时系统，反垃圾系统
    * 通过上面说到的 set 功能，你可以知道一个终端用户是否进行了某个操作，可以找到其操作
      的集合并进行分析统计对比等。没有做不到，只有想不到
7. Pub/Sub 构建实时消息系统
    * Redis 的 Pub/Sub 系统可以构建实时的消息系统，比如很多用 Pub/Sub 构建的实时聊天系统
      的例子
8. 构建队列系统
    * 使用 list 可以构建队列系统，使用 sorted set 甚至可以构建有优先级的队列系统。
9. 缓存  
    * 这个不必说了，性能优于 Memcached，数据结构更多样化。
## 发布及订阅消息

## 发布及订阅消息
**redis 过期通知**
首先启用  
登陆redis-cli,输入命令：
```config set notify-keyspace-events Ex[KEA]```

订阅的key ```__keyevent@<db>__:expired ``` 这个格式是固定的，db代表的是数据库的编号，由于订阅开启之后这个库的所有key过期时间都会被推送过来，所以最好单独使用一个数据库来进行隔离。可以使用redis来处理定时任务一种思路
```
<!-- 配置监听 -->
<bean class="org.springframework.data.redis.listener.adapter.MessageListenerAdapter" id="messageListener">
	    <constructor-arg>
	        <bean class="cn.itcast.redis.listener.RedisMessageListener"/>
	    </constructor-arg>
	</bean>
	<!-- 监听容器 -->
	<bean class="org.springframework.data.redis.listener.RedisMessageListenerContainer" id="redisContainer">
	    <property name="connectionFactory" ref="connectionFactory"/>
	    <property name="messageListeners">
	    	<map>
	            <entry key-ref="messageListener">
	                <list>
	                	<!-- 此处有坑  需要在Redis服务器命令行执行    config set notify-keyspace-events KEA -->
	                	<!-- __keyevent@0__:expired  配置订阅的主题名称
	                	此名称时redis提供的名称，标识过期key消息通知
	                			0表示db0 根据自己的dbindex选择合适的数字
	                	 -->
	                    <bean class="org.springframework.data.redis.listener.ChannelTopic">
	                        <constructor-arg value="__keyevent@0__:expired"></constructor-arg>
	                    </bean>
	                </list>
	            </entry>
		    </map>
		 </property>
	</bean>
```
