/**
 * 供其他方法使用的生成响应报文的工具类,所有公共方法的返回值类型都是 CommonResponse
 * 分为两种使用情况：
 *  1. 未发生异常，调用buildSuccess(data)方法，应传入希望返还给前端的JSON对象
 *  2. 发生异常，调用buildError(enum)方法，应传入包含对应异常信息的枚举对象
 *
 * @author 何家伟
 * @date 2019-08-09 23:06
 * @version 1.0
 */
public class ResponseUtil {
    /**
     * 在能够返回正确的报文的时候调用这个方法
     * @param data json对象
     * @param <T> json对象类型
     * @return 业务正常时的自定义响应报文
     */
    public static <T> CommonResponse buildSuccess(T data) {
        return new CommonResponse<T>(ResultEnum.SUCCESS, data);
    }

    public static CommonResponse buildSuccess() {
        return new CommonResponse(ResultEnum.SUCCESS);
    }

    public static CommonResponse buildSuccess(String msg) {
        return new CommonResponse(ResultEnum.SUCCESS.getCode(), msg);
    }

    public static CommonResponse buildSuccess(String code, String msg) {
        return new CommonResponse(code, msg);
    }

    public static <T> CommonResponse buildSuccess(String code, String msg, T data) {
        return new CommonResponse<T>(code, msg, data);
    }

    public static CommonResponse buildSuccess(ResultEnum resultEnum) {
        return new CommonResponse(resultEnum);
    }

    /**
     * 在出现异常时调用这个方法，应该传入异常的对应枚举
     *
     * @param resultEnum 包含错误码和错误信息的枚举类
     * @return 在发生异常时的枚举类
     */
    public static CommonResponse buildError(ResultEnum resultEnum) {
        return new CommonResponse(resultEnum);
    }

    public static <T> CommonResponse buildError(T data) {
        return new CommonResponse<T>(ResultEnum.DEFAULT_ERROR, data);
    }

    public static CommonResponse buildError() {
        return new CommonResponse(ResultEnum.DEFAULT_ERROR);
    }

    public static CommonResponse buildError(String msg) {
        return new CommonResponse(ResultEnum.DEFAULT_ERROR.getCode(), msg);
    }

    public static CommonResponse buildError(String code, String msg) {
        return new CommonResponse(code, msg);
    }

    public static <T> CommonResponse buildError(String code, String msg, T data) {
        return new CommonResponse<T>(code, msg, data);
    }

}
