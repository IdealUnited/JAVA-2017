/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * @author KANGZHIGUANG
 *
 */
public enum ShareFlagEnum {

	NO_SHARE("0","不分账"),
	SHARE("1","分账")	;
	
	private String type;
	private String desc;
	
	private ShareFlagEnum(String type,String desc){
		this.type = type;
		this.desc = desc;
	}
	
	/**
	 * 判断是否存在
	 * @param code
	 * @return
	 */
	public static boolean isExists(String type) {
		for (ShareFlagEnum status : values()) {
			if (status.getType().equals(type)) {
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
