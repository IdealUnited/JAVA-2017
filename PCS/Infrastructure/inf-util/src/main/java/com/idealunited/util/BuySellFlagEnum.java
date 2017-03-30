package com.idealunited.util;

public enum BuySellFlagEnum {
	
	BUY("B", "锁定买方金额"),
	SELL("S", "锁定卖方金额");
	
	private final String code;
	private final String description;
	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	BuySellFlagEnum(String code, String description) {
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
	public static BuySellFlagEnum getByCode(String code) {
		for (BuySellFlagEnum status : values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}

}
