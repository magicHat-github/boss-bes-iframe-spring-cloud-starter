package com.boss.bes.core.service.autoFillField.aop.pojo.common;

import java.util.Date;

/**
 * @author Lynch
 * @date 2019/8/15 -13:45
 */
public class UpdateCommon {

	private Long orgId;
	private Long companyId;
	private Long updatedBy;
	private Date updatedTime;

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

	public UpdateCommon() {
	}

	public UpdateCommon(Long orgId, Long companyId, Long updatedBy, Date updatedTime) {
		this.orgId = orgId;
		this.companyId = companyId;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
	}
}
