package com.bes.common.logging.annotation;

import java.lang.annotation.*;

/**
 * 函数日志的注解类
 * @author longquanxiao
 * @date 2019/8/12
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodLog {
    /**
     * 日志的存储位置
     * @return 返回存储路径
     */
    String value() default "default";
}
