package com.boss.bes.common.exception.logging.aspect;

import com.boss.bes.common.exception.logging.exception.AppException;
import com.boss.bes.common.exception.logging.handle.Handle;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * 注解的切面用于统一异常处理
 * @author fishkk
 * @version 1.0.0
 * @since 2019/8/12
 */
@Aspect
@Component
public class ExceptionAspect {

    @Resource
    private Handle handle;

    private Logger logger = LoggerFactory.getLogger(ExceptionAspect.class);

    @Pointcut("@annotation(com.boss.bes.common.exception.logging.annotion.ExceptionHandle)")
    public  void serviceAspect() { }

    /**
      *  在控制层方法抛出异常后调用全局异常处理函数
      *  并记录异常错误信息
      * @author fishkk
      * @since  2019/8/12
      * @param  ex 抛出的异常形参
      */
    @AfterThrowing(throwing = "ex",pointcut = "serviceAspect()")
    public void afterThrowing(AppException ex){
        logger.error("Error found: ", ex);
        handle.handle(ex);
    }
}


