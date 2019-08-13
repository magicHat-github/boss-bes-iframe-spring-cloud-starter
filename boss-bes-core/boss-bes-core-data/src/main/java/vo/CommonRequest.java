package vo;

import vo.head.CommonRequestHead;

import java.util.Map;

/**
 * 约定的请求报文
 * body 部分使用示例如下：
 *   JSONObject jsonObject = new JSONObject(commonRequest.getBody());
 *   DataDTO dataDTO = JSON.toJavaObject(jsonObject, DataDTO.class);
 *
 * @author 何家伟
 * @date 2019-08-09 23:02
 * @version 1.0
 */
public class CommonRequest {
    /**
     * head
     * 即从前端传来的请求报文头
     */
    private CommonRequestHead head;

    /**
     * body
     * 即从前端传来的json数据
     */
    private Map<String, Object> body;

    public CommonRequestHead getHead() {
        return head;
    }

    public void setHead(CommonRequestHead head) {
        this.head = head;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
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
