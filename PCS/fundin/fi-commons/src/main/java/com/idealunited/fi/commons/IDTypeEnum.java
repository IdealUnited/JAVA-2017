/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * @author kangzhiguang
 *
 */
public enum IDTypeEnum {

	ID("01","身份证"),
	PASSPORT("02","身份证"),
	OTHER("03","其他")
	;
	
	private String type;
	private String desc;
	
	private IDTypeEnum(String type,String desc){
		this.type = type;
		this.desc = desc;
	}
	
	public static boolean isExist(String code) {
		for (IDTypeEnum status : values()) {
			if (status.getType().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}
	
}
