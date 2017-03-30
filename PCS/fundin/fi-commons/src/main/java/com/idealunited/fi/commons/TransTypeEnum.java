/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * @author chaoyue
 *
 */
public enum TransTypeEnum {

	EDC("EDC"),
	DCC("DCC"),
	;
	
	private String code;
	
	private TransTypeEnum(String code){
		this.code = code;
	}
	
	public static boolean isExists(String code) {
		for (TransTypeEnum item : TransTypeEnum.values()) {
			if (item.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public String getCode() {
		return code;
	}

}
