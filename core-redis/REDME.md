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


