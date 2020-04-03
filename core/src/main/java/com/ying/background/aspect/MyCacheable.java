package com.ying.background.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yingsy on 2019/5/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MyCacheable {

    String value() default "";

    String key() default "";

    String nextKey() default "";

    long expireTime() default 1800;//30分钟
}
