/**
 * 
 */
package com.idealunited.inf.enums;

/**
 * @author Steven Lee
 *
 */
public enum SystemCodeEnum {
	
	WEBGATE("101", "收单前置","0.0.1"),
	GATEWAY("102", "收银台","0.0.1"),
	TXNCORE("103","交易系统","0.0.1"),
	CHANNEL("104","渠道系统","0.0.1"),
	PRE("105","前置系统","0.0.1"),
	TESTBANK("106","银行模拟系统","0.0.1"),
	REPAIR("108","补单系统","0.0.1"),
	EXCHANGE("110","结购汇系统","0.0.1"),
	EPORTCORE("109","电子口岸核心系统","0.0.1"),

	FOBRIDGE("201", "出款桥接系统","0.0.1"),
	REMCORE("203","付汇核心系统","0.0.1"),
	
	MABRIDGE("402", "会员账户桥接系统","0.0.1"),
	
	PEBRIDGE("502", "记账引擎桥接系统","0.0.1"),
	
	POSS("801","支撑系统","0.0.1"),
	WEBSITE("802","商户后台系统","0.0.1"),
	
	TASK("901","调度系统","0.0.1"),;
	
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
