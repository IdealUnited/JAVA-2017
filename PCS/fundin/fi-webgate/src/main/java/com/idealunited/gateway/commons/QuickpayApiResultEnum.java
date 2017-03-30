package com.idealunited.gateway.commons;
public enum QuickpayApiResultEnum {
	
	//预支付响应结果
	SEND_SUCCESS("S000","发送成功"),
	SEND_FAILED("S001","发送失败"),
	
	//支付响应结果
	PAYMENT_APPLYED("0000","处理中"),
	PAYMENT_SUCCESS("0001","支付成功"),
	PAYMENT_FAILED("0002","支付失败");
	
	private final String code;
	private final String description;



	private QuickpayApiResultEnum(String code, String description) {
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
	public static QuickpayApiResultEnum getByCode(String code) {
		for (QuickpayApiResultEnum status : values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}

}
