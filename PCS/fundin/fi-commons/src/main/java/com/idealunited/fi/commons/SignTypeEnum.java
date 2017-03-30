/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * @author chaoyue
 *
 */
public enum SignTypeEnum {

	RSA("1","RSA"),
	MD5("2","MD5")
	
	;
	
	private String code;
	private String desc;
	
	private SignTypeEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static boolean isExists(String code) {
		for (SignTypeEnum item : SignTypeEnum.values()) {
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
