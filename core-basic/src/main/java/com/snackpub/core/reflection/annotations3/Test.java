package com.snackpub.core.reflection.annotations3;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 保留策略： 注解要保留到什么时候： runtime 运行时，只在源码中 source，.class 文件中
@Target(ElementType.METHOD) // 应用目标：class / method / field
public @interface Test {

    boolean ignore() default false;


}
