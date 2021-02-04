package com.snackpub.core.reflection.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 仅适用于属性
@Retention(RetentionPolicy.RUNTIME) // 注解将由编译器记录在类文件中，并在运行时由VM保留，因此可以反射地读取它们。
public @interface Constraints {
    boolean primaryKey() default false;

    boolean allowNull() default true;

    boolean unique() default false;
}
