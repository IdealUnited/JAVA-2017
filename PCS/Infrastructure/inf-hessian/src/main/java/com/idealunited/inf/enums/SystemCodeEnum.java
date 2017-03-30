/**
 * 
 */
package com.idealunited.inf.enums;

/**
 * @author new
 *
 */
public enum SystemCodeEnum {
	
	TXNCORE("103","交易系统","0.0.1"),
	POSS("104","运营系统","0.0.1"),
	PRE("105","前置系统","0.0.1"),
	TASK("901","调度系统","0.0.1")
	
	;
	
	private String code;
	private String desc;
	private String version;
	
	SystemCodeEnum(String code,String desc,String version) {
		this.code = code;
		this.desc = desc;
		this.version = version;
	}

	public String getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}

	public String getVersion() {
		return version;
	}

	public static SystemCodeEnum getSystemCodeEnum(String value) {
		if (value != null) {
			for (SystemCodeEnum nameEnum : values()) {
				if (nameEnum.getCode().equals(value)) {
					return nameEnum;
				}
			}
		}
		return null;
	}
	
	public static boolean isSystemCodeEnum(String value) {
		if (value != null) {
			for (SystemCodeEnum nameEnum : values()) {
				if (nameEnum.getCode().equals(value)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
