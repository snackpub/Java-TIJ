package com.snackpub.core.redis.aop.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ControllerWebLog {

    /**
     * 接口名称
     */
    String name();

    /**
     * 日志是否入库
     */
    boolean intoDb() default false;

}
