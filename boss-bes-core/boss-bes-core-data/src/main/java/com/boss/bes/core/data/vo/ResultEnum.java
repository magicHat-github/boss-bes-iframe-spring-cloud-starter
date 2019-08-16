package com.boss.bes.core.data.vo;

/**
 * 业务结果枚举类，包含应答码和消息
 * 应答码规定为4位，规则如下：
 * 1. 业务执行成功时，使用 0000
 * 2. 出现系统级错误，使用 1xxx
 *      网关服务错误：10xx
 *      认证服务错误：11xx
 *      日志服务错误：12xx
 *      CDN服务错误：13xx
 * 3. 出现业务错误，使用 2xxx
 *      基础数据服务错误：20xx + 21xx
 *      租凭服务错误：22xx + 23xx
 *      用户和权限服务错误：24xx + 25xx
 *      试卷服务错误：26xx + 27xx
 *      考试服务错误：28xx + 29xx
 *  现在只是初步模拟实现，还需要后续的修改
 *
 * @author 何家伟
 * @date 2019-08-09 23:05
 * @version 1.0
 */
public enum ResultEnum {
    /**
     * 方法执行成功时的枚举情况
     */
    SUCCESS("0000", "请求成功"),
    /**
     * 默认错误
     */
    DEFAULT_ERROR("-1", "请求失败"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR("1000", "系统错误"),
    /**
     * 业务错误
     */
    BUSINESS_ERROR("2000", "参数错误"),
    /**
     * 从请求体拿token内参数错误，或者从redis拿值错误
     */
    PARAMS_TOKEN_ERROR("1001", "获取自动填值参数错误");

    private String code;
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
