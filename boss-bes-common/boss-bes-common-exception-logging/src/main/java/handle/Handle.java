package handle;


import annotion.ExceptionHandle;
import exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import util.ResponseUtil;
import vo.CommonResponse;
import vo.ResultEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * ExceptionHandel
 * 全局异常处理类
 *
 * @author fishkk
 * @version 1.0
 * @since 2019/8/13
 */
@ControllerAdvice
public class Handle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
      * 全局异常处理
      * 自定义异常  表单验证异常 和 未定义系统异常的处理
      * 转换成Result对象返回
      * @author fishkk
      * @since  2019/7/28
      * @param  e 被捕获的异常
      * @return  Result
      */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResponse handle(Exception e){
        if (e instanceof AppException) {
            // 系统内部异常
            AppException exception = (AppException) e;
            if (exception.getResultEnum() != null){
                return ResponseUtil.buildError(exception.getResultEnum());
            }
            return ResponseUtil.buildError(exception.getCode(),exception.getMessage());
        }
        if(e instanceof BindException){
            // @Valid表单验证不通过
            BindException bindException = (BindException)e;
            List<ObjectError> errors = bindException.getAllErrors();
            List<String> errorMessages = new ArrayList();
            for (ObjectError objectError : errors){
                errorMessages.add(objectError.getDefaultMessage());
            }

            return ResponseUtil.buildError("1000",errorMessages.toString());
        } else {
            logger.error("!!!系统异常!!!", e);
            return ResponseUtil.buildError(ResultEnum.SYSTEM_ERROR);
        }
    }

}
