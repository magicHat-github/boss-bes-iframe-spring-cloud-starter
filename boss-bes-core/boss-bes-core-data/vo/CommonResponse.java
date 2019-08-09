import java.io.Serializable;

/**
 * 约定的响应报文
 *
 * @author 何家伟
 * @date 2019-08-09 23:02
 * @version 1.0
 */
public class CommonResponse<T> implements Serializable {
    /**
     * head，应用程序版本，不允许为空
     */
    private String version;
    /**
     * head，应答码
     */
    private String code;
    /**
     * head，消息
     */
    private String msg;
    /**
     * head，加密标志，1标记加密，0标记不加密
     */
    private Integer crypt;

    /**
     * body，即JSONObject，需要传出的业务对象
     */
    private T data;

    public CommonResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CommonResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonResponse(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public CommonResponse(ResultEnum resultEnum, T data) {
        this(resultEnum);
        this.data = data;
    }

    public CommonResponse() {
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
