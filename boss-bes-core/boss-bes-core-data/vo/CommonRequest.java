/**
 * 约定的请求报文
 *
 * @author 何家伟
 * @date 2019-08-09 23:02
 * @version 1.0
 */
public class CommonRequest<T> {
    /**
     * 应用版本
     */
    private String version;
    /**
     * token
     */
    private String token;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 设备id
     */
    private String deviceId;
    /**
     * 设备类型
     */
    private Integer deviceType;

    /**
     * body
     * 即从前端传来的json数据
     */
    private T data;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
