package pojo.common;

import org.aspectj.apache.bcel.generic.FieldGenOrMethodGen;

import java.util.Date;

/**
 * @author Lynch
 * @date 2019/8/15 -13:45
 */
public class UpdateCommon {

	private long orgId;
	private long companyId;
	private long updateBy;
	private Date updateTime;
	private long version;

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public UpdateCommon() {
	}

	public UpdateCommon(long orgId, long companyId, long updateBy, Date updateTime, long version) {
		this.orgId = orgId;
		this.companyId = companyId;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
		this.version = version;
	}

	@Override
	public String toString() {
		return "UpdateCommon{" +
				"orgId=" + orgId +
				", companyId=" + companyId +
				", updateBy=" + updateBy +
				", updateTime=" + updateTime +
				", version=" + version +
				'}';
	}
}
