package com.idealunited.fi.commons;

public enum RefundTypeEnum {

	/** 1---全额退款 */
	FULL_REFUND(1, "原路退回"),

	
	/** 2---部分退款 */
	PART_REFUND(2, "部分退款"),
	
	
	/** 2---按比例退款 */
	RATE_REFUND(3, "按比例退款");
	 
	
	

	private final int code;
	private final String description;

	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	RefundTypeEnum(int code, String description) {
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
	 * @return Returns the code.
	 */
	public String getStringCode() {
		return String.valueOf(code);
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
	public static RefundTypeEnum getByCode(int code) {
		for (RefundTypeEnum refundType : values()) {
			if (refundType.getCode() == code) {
				return refundType;
			}
		}
		return null;
	}
}
