/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * @author chaoyue
 *
 */
public enum RevokeTypeEnum {

	REFUND("1","交易撤销"),
	REVOKE("2","授权撤销"),
	;
	
	private String code;
	private String desc;
	
	private RevokeTypeEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static boolean isExists(String code) {
		for (RevokeTypeEnum item : RevokeTypeEnum.values()) {
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
