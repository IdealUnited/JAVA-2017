/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * 	支付结果
 * @author XFLIANG
 *
 */
public enum PayResultEnum {
	
	SUCCESS("00","支付成功"),
	REPEAT("01","重复支付"),
	FAIL("02","支付失败"),
	WAIT("03","支付中");
	
	private String code;
	private String desc;
	
	private PayResultEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static boolean isExists(String code) {
		for (PayResultEnum item : PayResultEnum.values()) {
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
