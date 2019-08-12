package vo;

/**
 * 约定的请求报文
 *
 * @author 何家伟
 * @date 2019-08-09 23:02
 * @version 1.0
 */
public class CommonRequest<T> {
    private String head;

    /**
     * body
     * 即从前端传来的json数据
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
