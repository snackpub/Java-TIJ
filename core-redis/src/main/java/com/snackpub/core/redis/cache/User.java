package com.snackpub.core.redis.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 要缓存的 Java 对象必须实现 Serializable 接口，因为Spring先将对象序列化在存入redis
 *
 * @author snackpub
 * @date 2020/5/18 20:53
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {

    private Long id;
    private String company;
    private String edu;

    public User(String company) {
        this.company = company;
    }
}