package com.snackpub.core.reflection.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // 仅适用于类
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {

    String name() default "";
}
