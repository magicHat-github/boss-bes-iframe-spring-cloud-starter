package com.boss.bes.common.exception.logging.handle;


import com.boss.bes.common.exception.logging.exception.AppException;
import com.boss.bes.core.data.util.ResponseUtil;
import com.boss.bes.core.data.vo.CommonResponse;
import com.boss.bes.core.data.vo.ResultEnum;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * ExceptionHandel
 * 全局异常处理类
 * @author fishkk
 * @version 1.0
 * @since 2019/8/13
 */
@ControllerAdvice
public class Handle {
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
            List<String> errorMessages = new ArrayList<>();
            for (ObjectError objectError : errors){
                errorMessages.add(objectError.getDefaultMessage());
            }

            return ResponseUtil.buildError("1000",errorMessages.toString());
        } else {
            return ResponseUtil.buildError(ResultEnum.SYSTEM_ERROR);
        }
    }

}
