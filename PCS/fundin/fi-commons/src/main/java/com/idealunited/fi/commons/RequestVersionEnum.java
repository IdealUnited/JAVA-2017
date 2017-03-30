package com.idealunited.fi.commons;

public enum RequestVersionEnum {
	
	/** "网关历史版本枚举" **/
	ONLINE_1_0("1.0", "在线收单1.0版本"),
	QUICKPAY_1_0_0("1.0.0","快捷支付1.0.0版")
	;

	private final String code;
	private final String description;

	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	RequestVersionEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 通过枚举<code>code</code>获得枚举
	 * 
	 * @param code
	 * @return
	 */
	public static RequestVersionEnum getByCode(String code) {
		for (RequestVersionEnum status : values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}

	/**
	 * 通过枚举<code>description</code>获得枚举
	 * 
	 * @param code
	 * @return
	 */
	public static RequestVersionEnum getByDescription(String description) {
		for (RequestVersionEnum status : values()) {
			if (status.getDescription().equals(description)) {
				return status;
			}
		}
		return null;
	}
}
