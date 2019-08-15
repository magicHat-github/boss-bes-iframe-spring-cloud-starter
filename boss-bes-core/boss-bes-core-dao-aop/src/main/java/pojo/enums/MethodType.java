package pojo.enums;

/**
 * @author Lynch
 * @date 2019/8/13 -10:11
 */
public enum MethodType {

	UNKNOWN("unknown"),
	DELETE("delete"),
	SELECT("select"),
	UPDATE("update"),
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
