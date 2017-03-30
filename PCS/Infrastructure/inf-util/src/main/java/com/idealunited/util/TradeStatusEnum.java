package com.idealunited.util;



public enum TradeStatusEnum {
	AUTHING(1, "预授权进行中"),
	
	AUTH_SUCESS(2, "预授权成功"),
	
	WITHDRAWING(3, "请款中"),
	
	WITHDRAW_SUCESS(4, "请款成功"),
	
	WITHDRAW_FAILED(5, "请款失败"),
	
	HANDMADE_SUCESS(6, "手工处理成功"),
    
	CHECK_REPAIR_FAILED(7, "核对补单失败");
	private final int code;
	private final String description;

	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	TradeStatusEnum(int code, String description) {
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
	public static TradeStatusEnum getByCode(int code) {
		for (TradeStatusEnum status : values()) {
			if (status.getCode() == code) {
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
	public static TradeStatusEnum getByDescription(String description) {
		for (TradeStatusEnum status : values()) {
			if (status.getDescription().equals(description)) {
				return status;
			}
		}
		return null;
	}
}
