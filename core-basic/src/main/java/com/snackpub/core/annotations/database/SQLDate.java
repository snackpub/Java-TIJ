package com.snackpub.core.annotations.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SQLDate {
    String name() default "";

    Constraints constraints() default @Constraints;

    // 字段是否是必须的
    boolean exist() default true;

    String format() default "YYYY-mm-dd";
}
