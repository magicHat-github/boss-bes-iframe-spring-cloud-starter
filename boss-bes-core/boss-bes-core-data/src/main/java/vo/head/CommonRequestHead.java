package vo.head;

import javax.validation.constraints.NotBlank;

/**
 * 约定的请求报文头
 *
 * @author 何家伟
 * @version 2.0
 * @date 2019-08-14 22:33
 */
public class CommonRequestHead {
    /**
     * 应用版本
     */
    @NotBlank(message = "request的Head中版本信息不允许为空")
    private String version;
    /**
     * token
     */
    @NotBlank(message = "request的Head中token不允许为空")
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
     * 加密方式
     */
    private Integer crypt;

    public Integer getCrypt() {
        return crypt;
    }

    public void setCrypt(Integer crypt) {
        this.crypt = crypt;
    }

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

    @Override
    public String toString() {
        return "CommonRequestHead{" +
                "version='" + version + '\'' +
                ", token='" + token + '\'' +
                ", businessType='" + businessType + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", deviceType=" + deviceType +
                '}';
    }
}
