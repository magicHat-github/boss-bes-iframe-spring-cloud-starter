package com.boss.bes.core.service.autoFillField.aop.pojo.enums;

/**
 * @author Lynch
 * @date 2019/8/13 -10:11
 */
public enum MethodType {

	/**
	 * 不知道什么操作类型
	 */
	UNKNOWN("unknown"),
	/**
	 * Dao层删除方法
	 */
	DELETE("delete"),
	/**
	 * Dao层查询方法
	 */
	SELECT("select"),
	/**
	 * Dao层更新方法
	 */
	UPDATE("update"),
	/**
	 * Dao层插入方法
	 */
	INSERT("insert");

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	MethodType(String s) {
		this.value = s;
	}
}
