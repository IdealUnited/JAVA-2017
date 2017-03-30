package com.idealunited.fi.commons;

public enum ExchangeReqSourceEnum {
	//请求来源,0:网关 1:跨境批付  2   境外分账 3 退汇购汇
	GATEWAY("0","网关"),
	CROSS_BATCH_PAYMENT("1","跨境批付"),
	CROSS_BATCH_SHARE("2","境外分账"),
	REFUND("3","退汇");
	
	 ExchangeReqSourceEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	private String code;
	private String desc;
}
