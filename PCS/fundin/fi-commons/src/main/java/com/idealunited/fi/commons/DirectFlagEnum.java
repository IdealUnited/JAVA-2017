/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * @author chaoyue
 *
 */
public enum DirectFlagEnum {

	DIRECT("1"), NODIRECT("0")

	;

	private String code;

	private DirectFlagEnum(String code) {
		this.code = code;
	}

	public static boolean isExists(String code) {
		for (DirectFlagEnum item : DirectFlagEnum.values()) {
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
