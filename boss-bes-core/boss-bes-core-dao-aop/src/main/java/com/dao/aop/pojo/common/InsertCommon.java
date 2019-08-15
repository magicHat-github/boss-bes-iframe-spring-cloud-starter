package com.dao.aop.pojo.common;

import java.util.Date;

/**
 * @author Lynch
 * @date 2019/8/15 -13:46
 */
public class InsertCommon {

	private Long orgId;
	private Long companyId;
	private Long createdBy;
	private Date createdTime;
	private Long updatedBy;
	private Date updatedTime;
	private Long version;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public InsertCommon() {
	}

	public InsertCommon(Long orgId, Long companyId, Long createdBy, Date createdTime, Long updatedBy, Date updatedTime, Long version) {
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
