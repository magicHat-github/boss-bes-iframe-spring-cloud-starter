package vo.head;

/**
 * 供后续迭代中使用，当前版本中没有使用到
 *
 * @author 何家伟
 * @version 1.0
 * @date 2019-08-12 13:33
 */
public class CommonRequestHead {
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
