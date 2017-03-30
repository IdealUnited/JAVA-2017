package com.idealunited.util;

public enum ReqSourceEnum {

	GATEWAY(0, "网关"),
	CROSS_BATCH_PAY(1,"跨境批付"),
	SHARING(2,"分账"),
	FUNDOUT_REFUND( 3, "出款退票" );
	
	private final int code;
	private final String description;

	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	ReqSourceEnum(int code, String description) {
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
	public static ReqSourceEnum getByCode(int code) {
		for (ReqSourceEnum status : values()) {
			if (status.getCode() == code) {
				return status;
			}
		}
		return null;
	}

}
