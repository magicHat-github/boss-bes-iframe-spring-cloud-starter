package vo;

import vo.head.CommonRequestHead;

import javax.validation.Valid;

/**
 * 约定的请求报文
 * 在Controller层的方法中，传入时需指定body应该属于的DTO类型，同时增加Valid注解启用head和DTO的参数校验
 * 示例：
 *  public CommonResponse(@RequestBody @Valid CommonRequest<DataDTO> commonRequest) {
 *      // 使用时只要直接使用getBody()方法就好了
 *      DataDTO dataDTO = commonRequest.getBody();
 *      ...
 *  }
 *
 * @author 何家伟
 * @date 2019-08-14 23:02
 * @version 2.0
 */
public class CommonRequest<T> {
    /**
     * head
     * 即从前端传来的请求报文头
     */
    @Valid
    private CommonRequestHead head;

    /**
     * body
     * 即从前端传来的json数据
     */
    @Valid
    private T body;

    public CommonRequestHead getHead() {
        return head;
    }

    public void setHead(CommonRequestHead head) {
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
        return "CommonRequest{" +
                "head='" + head + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
