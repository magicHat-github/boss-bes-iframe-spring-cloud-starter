package com.boss.bes.core.service.autoFillField.aop.annotation;

import com.boss.bes.core.service.autoFillField.aop.pojo.enums.MethodType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Lynch
 * @date 2019/8/11 -23:00
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFillEntityFieldAop {
	MethodType methodType() default MethodType.UNKNOWN;
}
