package com.boss.bes.core.data.vo.head;

/**
 * 响应报文头的实体类
 *
 * @author 何家伟
 * @version 1.0
 * @date 2019-08-12 13:33
 */
public class CommonResponseHead {
    /**
     * head
     * 应用程序版本，不允许为空
     */
    private String version;
    /**
     * head
     * 应答码
     */
    private String code;
    /**
     * head
     * 消息
     */
    private String msg;
    /**
     * head
     * 加密标志，1标记加密，0标记不加密
     */
    private Integer crypt;

    public CommonResponseHead() {}

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public Integer getCrypt() {
        return crypt;
    }

    public void setCrypt(Integer crypt) {
        this.crypt = crypt;
    }

    @Override
    public String toString() {
        return "CommonResponseHead{" +
                "version='" + version + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", crypt=" + crypt +
                '}';
    }
}
