package com.boss.bes.core.data.annotation;

import java.lang.annotation.*;

/**
 * 对统一请求/响应报文的注解
 *
 * @author 何家伟
 * @version 1.0
 * @date 2019-08-12 14:00
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CommonDataAnnotation {
}
