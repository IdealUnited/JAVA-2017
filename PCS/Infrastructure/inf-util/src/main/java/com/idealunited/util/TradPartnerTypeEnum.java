package com.idealunited.util;

public enum TradPartnerTypeEnum {

	COMPANY("C","01","对公用户"),
	PERSONAL("D","02","对私" );
	
	private final String code;
	private final String codeNum;
	private final String description;

	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	TradPartnerTypeEnum(String code,String codeNum, String description) {
		this.code = code;
		this.codeNum=codeNum;
		this.description = description;
	}

	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @return Returns the code.
	 */
	public String getCodeNum() {
		return codeNum;
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
	public static TradPartnerTypeEnum getByCode(String code) {
		for (TradPartnerTypeEnum status : values()) {
			if (status.getCode().equals(code) ) {
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
	public static TradPartnerTypeEnum getByDescription(String description) {
		for (TradPartnerTypeEnum status : values()) {
			if (status.getDescription().equals(description)) {
				return status;
			}
		}
		return null;
	}
}
