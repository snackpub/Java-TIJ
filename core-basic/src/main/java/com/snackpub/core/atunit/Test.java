//: net/mindview/atunit/Test.java
// The @Test tag.
package com.snackpub.core.atunit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义一个注解@Test，与其他任何接口一样，注解也将会编译成class文件.
 * 没有元素的注解称为: 标记注解
 *
 * @date 2021/10/03
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // 用来定义那个级别可用，SOURCE,CLASS,RUNTIME.
public @interface Test {} ///:~
