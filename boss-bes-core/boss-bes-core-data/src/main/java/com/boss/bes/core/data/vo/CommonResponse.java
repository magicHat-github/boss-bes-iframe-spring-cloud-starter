package com.boss.bes.core.data.vo;

import com.boss.bes.core.data.vo.head.CommonResponseHead;
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
     * head
     * 包含了规定的响应报文头
     */
    private CommonResponseHead head;

    /**
     * body
     * 即JSONObject，需要传出的业务对象
     */
    private T body;

    public CommonResponse(String code, String msg) {
        this();
        this.head.setCode(code);
        this.head.setMsg(msg);
    }

    public CommonResponse(String code, String msg, T body) {
        this(code, msg);
        this.body = body;
    }

    public CommonResponse(ResultEnum resultEnum) {
        this(resultEnum.getCode(), resultEnum.getMsg());
    }

    public CommonResponse(ResultEnum resultEnum, T body) {
        this(resultEnum);
        this.body = body;
    }

    public CommonResponse() {
        head = new CommonResponseHead();
    }

    public CommonResponseHead getHead() {
        return head;
    }

    public void setHead(CommonResponseHead head) {
        this.head = head;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }
}
