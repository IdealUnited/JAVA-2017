package com.idealunited.fi.commons;

public enum PayTypeEnum {
	/** "交易类型" 
	0001-余额，0002-网银，0003-预付费卡,0004-进口跨境人民币支付,0005-出口跨境人民币支付,0006-进口跨境外汇支付,0007-出口跨境外汇支付,0008-国际汇款（跨境人民币）,0009-国际汇款（跨境外汇）;支持多个值，用逗号隔开';
	**/
	REMAINDER("0001","余额", "REMAINDER"),
	BANK("0002","网银", "BANK"),
	PREPAIDCARD("0003","预付费卡", "PREPAIDCARD"),
	CROSSBORDERRMB("0004","进口跨境人民币支付", "CROSSBORDERRMB"),
	EXPORTCROSSBORDERRMB("0005","出口跨境人民币支付", "EXPORTCROSSBORDERRMB"),
	CROSSBORDERFOREIGN("0006","进口跨境外汇支付", "CROSSBORDERFOREIGNEXCHANGE"),
	EXPORTCROSSBORDERFOREIGN("0007","出口跨境外汇支付", "EXPORTCROSSBORDERFOREIGN"),
	INTERNATIONALRMB("0008","国际汇款（跨境人民币)", "INTERNATIONALRMB"),
	INTERNATIONALFOREIGN("0009","国际汇款（跨境外汇）","INTERNATIONALFOREIGN"),
	ALL("ALL","全部","ALL");
	
	private final String code;
	private final String description;
	private final String description_en;
	
	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	PayTypeEnum(String code, String description, String description_en) {
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
	 * 通过枚举<code>description</code>获得枚举
	 * 
	 * @param description
	 * @return
	 */
	public static PayTypeEnum getByDescription(String description) {
		for (PayTypeEnum status : values()) {
			if (status.getDescription().equals(description)) {
				return status;
			}
		}
		return null;
	}
	/**
	 * 通过枚举<code>code</code>获得枚举
	 * 
	 * @param code
	 * @return
	 */
	public static PayTypeEnum getByCode(String code) {
		for (PayTypeEnum status : values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}
}
