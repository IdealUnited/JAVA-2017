package com.idealunited.util;

public enum CharsetTypeEnum {

	/** 1---UTF-8 */
	UTF8(1, "UTF-8"),

	/** 2---GBK */
	GBK(2, "GBK");

	private final int code;
	private final String description;

	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	CharsetTypeEnum(int code, String description) {
		this.code = code;
		this.description = description;
	}

	/**
	 * @return Returns the code.
	 */
	public int getCode() {
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
	public static CharsetTypeEnum getByCode(int code) {
		for (CharsetTypeEnum status : values()) {
			if (status.getCode() == code) {
				return status;
			}
		}
		return null;
	}
}
