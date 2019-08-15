package pojo.common;

import java.util.Date;

/**
 * @author Lynch
 * @date 2019/8/15 -13:46
 */
public class InsertCommon {

	private long orgId;
	private long companyId;
	private long createdBy;
	private Date createdTime;
	private long updatedBy;
	private Date updatedTime;
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

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public InsertCommon() {
	}

	public InsertCommon(long orgId, long companyId, long createdBy, Date createdTime, long updatedBy, Date updatedTime, long version) {
		this.orgId = orgId;
		this.companyId = companyId;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
		this.version = version;
	}

	@Override
	public String toString() {
		return "InsertCommon{" +
				"orgId=" + orgId +
				", companyId=" + companyId +
				", createdBy=" + createdBy +
				", createdTime=" + createdTime +
				", updatedBy=" + updatedBy +
				", updatedTime=" + updatedTime +
				", version=" + version +
				'}';
	}
}
