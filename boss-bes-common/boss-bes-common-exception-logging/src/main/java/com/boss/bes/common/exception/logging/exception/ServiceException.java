package com.boss.bes.common.exception.logging.exception;

import com.boss.bes.core.data.vo.ResultEnum;

/**
 *  Service 层的异常抛出父类
 *
 * @author fishkk
 * @version 1.0.0
 * @since 2019/8/13
 */
public class ServiceException extends AppException{

    public ServiceException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public ServiceException(String message, String code) {
        super(message, code);
    }
}
