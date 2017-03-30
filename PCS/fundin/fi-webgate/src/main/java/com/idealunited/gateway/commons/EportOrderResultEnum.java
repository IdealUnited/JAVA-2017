package com.idealunited.gateway.commons;
public enum EportOrderResultEnum {
	
	INIT("0","未受理"),
	APPLYED("1","已受理"),
	FAILED("2","处理失败"),
	SUCCESS("3","处理成功"),
	OVER_TIME("4","超时");
	
	private final String code;
	private final String description;



	private EportOrderResultEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * 通过枚举<code>code</code>获得枚举
	 * 
	 * @param code
	 * @return
	 */
	public static EportOrderResultEnum getByCode(int code) {
		for (EportOrderResultEnum status : values()) {
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
	public static EportOrderResultEnum getByDescription(String description) {
		for (EportOrderResultEnum status : values()) {
			if (status.getDescription().equals(description)) {
				return status;
			}
		}
		return null;
	}
	
}
