package com.idealunited.gateway.commons;
public enum EportApplayRespStatusEnum {

	PROCESSING("1", "报关已受理"),
	SUCCESS("2", "报关成功"),
	FAILED("3", "报关失败");
	
	private final String code;
	private final String description;



	private EportApplayRespStatusEnum(String code, String description) {
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
	public static EportApplayRespStatusEnum getByCode(int code) {
		for (EportApplayRespStatusEnum status : values()) {
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
	public static EportApplayRespStatusEnum getByDescription(String description) {
		for (EportApplayRespStatusEnum status : values()) {
			if (status.getDescription().equals(description)) {
				return status;
			}
		}
		return null;
	}
	
}
