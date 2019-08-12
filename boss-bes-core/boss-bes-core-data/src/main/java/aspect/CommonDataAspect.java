package aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 切面类
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
    //@Pointcut(POINT_CUT)
    @Pointcut("@annotation(annotation.CommonDataAnnotation)")
    public void controllerLog() {
    }//签名，可以理解成这个切入点的一个名称

    @Around("controllerLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        /*
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            //获取返回的数据
            Object result = invocation.proceed();
            // 随意处理结果吧，例如把result改成hello world
            if (obj instanceof String) {
                result = "hello world";
            }
            return result;  //此时接口返回的就是hello world
        }

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String method = request.getMethod();
        String uri = request.getRequestURI();
        String paraString = JSON.toJSONString(pjp.getArgs());
        logger.info("***************************************************");
        logger.info("请求开始, URI: {}, method: {}, params: {}", uri, method, paraString);

         */

        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        logger.info("请求结束，controller的返回值是 " + JSON.toJSONString(result));
        return result;
    }
}
