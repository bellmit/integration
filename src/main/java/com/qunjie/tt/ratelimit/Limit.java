package com.qunjie.tt.ratelimit;

import java.lang.annotation.*;

/**
 * 限流注解
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    // 默认每秒放入桶中的token
    double limitNum() default 10;

    String name() default "";
}

