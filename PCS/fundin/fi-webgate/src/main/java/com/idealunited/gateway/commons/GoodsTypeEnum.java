package com.idealunited.gateway.commons;
public enum GoodsTypeEnum {
	
	CLOTHING("00","服装"), 
	GOODS("01","食品"), 
	ELECTRONIC("02","电子产品"),
	OTHER("99","其他");

	
	private final String code;
	private final String description;



	private GoodsTypeEnum(String code, String description) {
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
	public static GoodsTypeEnum getByCode(int code) {
		for (GoodsTypeEnum status : values()) {
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
	public static GoodsTypeEnum getByDescription(String description) {
		for (GoodsTypeEnum status : values()) {
			if (status.getDescription().equals(description)) {
				return status;
			}
		}
		return null;
	}
	
	/**
	 * 判断是否存在
	 * @param code
	 * @return
	 */
	public static boolean isExists(String code) {
		for (GoodsTypeEnum status : values()) {
			if (status.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}
	
}
