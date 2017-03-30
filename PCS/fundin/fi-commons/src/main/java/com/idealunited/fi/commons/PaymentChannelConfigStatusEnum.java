/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * @author chaoyue
 *
 */
public enum PaymentChannelConfigStatusEnum {

	INIT("0","待审核"),
	PASS("1","审核通过"),
	REFUSE("2","审核拒绝")
	;
	
	private String code;
	private String desc;
	
	private PaymentChannelConfigStatusEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static boolean isExists(String code) {
		for (PaymentChannelConfigStatusEnum item : PaymentChannelConfigStatusEnum.values()) {
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
