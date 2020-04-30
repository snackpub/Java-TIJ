package com.snackpub.core.redis.aop.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 数据源配置
 *
 * @author snackpub
 */

@Data
@ConfigurationProperties(prefix = "snackpub.druid.datasource")
public class DruidDataSourceProperties {

    /**
     * 数据源名称
     */
    private String name;

    /**
     * 数据库连接url
     */
    private String url;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 数据库密码
     */
    private String password;

    /**
     * 驱动
     */
    private String driverClassName;

    /**
     * 连接池初始连接数
     */
    private int initialSize = 5;

    /**
     * 连接池最大连接数
     */
    private int maxActive = 50;

    /**
     * 空闲的最小连接数量, 相当于线程池的最小连接数
     */
    private int minIdle = 5;

    /**
     * 获取连接时最大等待时间,毫秒
     */
    private int maxWait = 60000;

    /**
     * 配置间隔多久才进行一次检测需要关闭的空闲连接，单位是毫秒 ,默认1分钟
     */
    private int timeBetweenEvictionRunsMillis = 60000;

    /**
     * 配置一个连接在池中最小生存的时间，超过该时间的空闲链接将被关闭,默认5分钟
     */
    private int minEvictableIdleTimeMillis = 300000;

    /**
     * 验证链接是否有效的sql
     */
    private String validationQuery = "SELECT 'x'";

    /**
     * 检测连接是否有效的超时时间
     */
    private int validationQueryTimeout = 3000;

    /**
     * 空闲时检测链接是否有效
     */
    private boolean testWhileIdle = true;

    /**
     * 链接被借出时检查是否有效,影响性能,默认关闭
     */
    private boolean testOnBorrow = false;

    /**
     * 当链接返还时检查连接是否有效,影响性能,默认关闭
     */
    private boolean testOnReturn = false;

    /**
     * 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle,
     * 在mysql下建议关闭。
     */
    private boolean poolPreparedStatements = false;

    /**
     * poolPreparedStatements为false的情况,该值不起作用
     */
    private int maxOpenPreparedStatements = 20;
    /**
     * 是否启用数据源的监控,spring-web应用建议打开
     */
    private boolean enableMonitor = true;

    /**
     * 当启用监控后, 是否打印慢sql
     */
    private boolean logSlowSql = true;
    /**
     * 多少毫秒的sql认为是慢sql, 默认1秒
     */
    private int slowSqlMillis = 1000;

    /**
     * 是否合并sql, 同一个PreparedStatements但where条件不同会被认为是一个sql
     */
    private boolean mergeSql = true;

}