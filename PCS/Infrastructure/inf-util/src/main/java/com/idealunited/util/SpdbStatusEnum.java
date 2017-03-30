package com.idealunited.util;

public enum SpdbStatusEnum {
	
	AUDIT("A", "待审核"),
	FOR_HANDLING("0","待经办"),
	PENDING_REVIEW("1","待复核"),
	SUCCESS("4","完成"),
	FAIL("8","拒绝"),
	UNDO("9","撤销");
	
	private final String code;
	private final String description;

	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	SpdbStatusEnum(String code, String description) {
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
	public static SpdbStatusEnum getByCode(String code) {
		for (SpdbStatusEnum status : values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}

}
