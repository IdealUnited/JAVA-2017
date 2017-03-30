package com.idealunited.util;

public enum ExchangeStatusEnum {

	SUCCESS("T", "成功"),
	FAILURE("F", "失败"),
	GOING("O","进行中"),
	//CUTTED("O","待交割"),
	UNHANDLE("U","未处理"),
	APPLYED("A","已申请");

	
	private final String code;
	private final String description;

	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	ExchangeStatusEnum(String code, String description) {
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
	public static ExchangeStatusEnum getByCode(String code) {
		for (ExchangeStatusEnum status : values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}

}
