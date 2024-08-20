package com.mac.datasource.annotation;

import java.lang.annotation.*;

/**
 * @author: mac
 * @date: 2024/8/19
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DB {
    String value();
}

