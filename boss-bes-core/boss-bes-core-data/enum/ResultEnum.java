/**
 * 业务结果枚举类，包含应答码和消息
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
     * 方法执行失败时的信息
     */
    DATABASE_ERROR("1111", "后端处理请求错误"),
    /**
     * 参数存在错误时的信息
     */
    DATA_ERROR("2222", "参数错误"),
    /**
     * 未知的新异常
     */
    ERROR("4444", "出现了未定义的新异常");

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
