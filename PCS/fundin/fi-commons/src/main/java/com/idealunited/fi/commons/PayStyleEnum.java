package com.idealunited.fi.commons;

public enum PayStyleEnum {
	/** "支付方式" **/
	
	ALL("1","全部", "ALL"),
	
	ACCT("2","账户支付", "ACCT"),
	
	BANK("3","网银支付", "BANK"),
	
	EASYCARD("4","易生卡支付", "EASYCARD");
	
	private final String code;
	private final String description;
	private final String description_en;
	
	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	PayStyleEnum(String code, String description, String description_en) {
		this.code = code;
		this.description = description;
		this.description_en = description_en;
	}
	
	public String getCode() {
		return code;
	}


	public String getDescription() {
		return description;
	}
	
	public String getDescription_en() {
		return description_en;
	}

	/**
	 * 通过枚举<code>code</code>获得枚举
	 * 
	 * @param code
	 * @return
	 */
	public static PayStyleEnum getByCode(String code) {
		for (PayStyleEnum status : values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}
}
