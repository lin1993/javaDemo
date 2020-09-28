package com.rei.javaDemo.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAspect {

    // 给默认值
    String value() default "";
    // 如果有两个参数都有默认值，那么使用的时候无需声明用哪个的时候，默认会取value
    String path() default "";
}
