package com.bes.common.logging.aspect;

import com.bes.common.logging.annotation.ApiLog;
import com.bes.common.logging.util.ApiLogDateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 函数日志的切面
 * @author longquanxiao
 * @date 2019/8/12
 */
@Component
@Aspect
public class ApiLogAop {
    /**
     * 日志输出对象
     */
    private Logger logger = LoggerFactory.getLogger(ApiLogAop.class);
    /**
     * 时间工具对象
     */
    @Resource
    private ApiLogDateUtil apiLogDateUtil = null;
    /**
     * 切点为我的注解,只要有这个注解的都可以切
     */
    @Pointcut("@annotation(com.bes.common.logging.annotation.ApiLog)")
    public void pointCut(){}

    /**
     * 环绕通知记录日志
     * @param joinPoint 切点
     * @param logAnnotation 日志注解对象
     * @return 函数的执行结果
     * @throws Throwable 抛出产生的异常
     */
    @Around(value = "pointCut()&&@annotation(logAnnotation)",argNames = "joinPoint,logAnnotation")
    public Object around(ProceedingJoinPoint joinPoint, ApiLog logAnnotation) throws Throwable{
        // 获得注解上的值
        String annotationValue = logAnnotation.value();
        // 调用方法前的日志
        String className = joinPoint.getTarget().getClass().getName();
        String method = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("执行{}方法所在的类{}",method,className);
        logger.info("{}方法的输入参数:",method);
        // 记录参数
        for (int i = 0; i<args.length; i++){
            logger.info("parameter={},value={}",i,args[i].toString());
        }
        String startRunTime = apiLogDateUtil.getStringTime();
        String log = "方法:"+method+"实际开始执行时间:"+startRunTime;
        logger.info(log);
        /*
         * 开始的时间戳
         */
        long startTimeTamp = System.currentTimeMillis();
        // 函数执行后返回的对象
        Object ret = joinPoint.proceed();
        // 结束时间戳
        long endTimeTamp = System.currentTimeMillis();
        /*
         * 计算运行时间
         */
        long grad = endTimeTamp - startTimeTamp;
        logger.info("执行耗时:{}ms",grad);
        /*
         * 执行结束时间
         */
        logger.info("执行结束时间:{}", apiLogDateUtil.getStringTime());
        logger.info("执行{}函数的的返回结果{}",method,ret);
        return ret;
    }
}
