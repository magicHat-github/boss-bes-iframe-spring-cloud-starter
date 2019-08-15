package com.boss.bes.core.data.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * 切面类
 * 还未完善，需要后续增加加密、解密功能
 *
 * @author 何家伟
 * @version 1.0
 * @date 2019-08-12 14:37
 */
@Aspect
@Component
public class CommonDataAspect {
    private final Logger logger = LoggerFactory.getLogger(CommonDataAspect.class);
    /**
     * 设置切点
     */
    @Pointcut("@annotation(com.boss.bes.core.data.annotation.CommonDataAnnotation)")
    public void cutPoint() {
    }//签名，可以理解成这个切入点的一个名称

    @Around("cutPoint()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        Field[] fields = args[0].getClass().getDeclaredFields();
        System.out.println("请求开始，请求报文：");
        for (Field field: fields) {
            field.setAccessible(true);
            System.out.println(field.getName());
        }
        System.out.println(point.getTarget());
        System.out.println("=========================================");

        // result的值就是被拦截方法的返回值
        Object result = point.proceed();
        logger.info("请求结束，响应报文： " + JSON.toJSONString(result));
        return result;
    }
}
