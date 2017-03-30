/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * @author chaoyue
 *
 */
public enum PaymentTypeEnum {

	CHARGE("1","充值"),
	
	PAYMENT("2","支付"),
	
	DIRECT("3","直连"),
	PREAUTH("4","预授权"),
	QUICK_PAY("5","快捷支付"),
	
	;
	
	private String code;
	private String desc;
	
	private PaymentTypeEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static boolean isExists(String code) {
		for (PaymentTypeEnum item : PaymentTypeEnum.values()) {
			if (item.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	
}
