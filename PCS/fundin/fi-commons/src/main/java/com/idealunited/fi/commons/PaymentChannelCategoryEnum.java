/**
 * 
 */
package com.idealunited.fi.commons;


/**
 * @author chaoyue
 *
 */
public enum PaymentChannelCategoryEnum {

	ALL("ALL","全支付"),
	ACCT("ACCT","账户支付"),
	B2C("B2C","网银B2C"),
	B2B("B2B","网银B2B"),
	LARGE_B2C("LARGE_B2C","网银B2C大额"),
	LARGE_DEBIT("LARGE_DEBIT","借记卡大额"),
	LARGE_CREDIT("LARGE_CREDIT","贷记卡大额"),
	QUICK_DEBIT("QUICK_DEBIT","借记卡快捷"),
	QUICK_CREDIT("QUICK_CREDIT","贷记卡快捷"),
	VISA("VISA","VISA"),
	EXCHANGE("EXCHANGE","外汇"),
	OFFLINE("OFFLINE","线下"),
	;
	
	private String code;
	private String desc;
	
	private PaymentChannelCategoryEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static boolean isExists(String code) {

		for (PaymentChannelCategoryEnum tmpEnum : PaymentChannelCategoryEnum
				.values()) {
			if (tmpEnum.getCode().equals(code)) {
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
