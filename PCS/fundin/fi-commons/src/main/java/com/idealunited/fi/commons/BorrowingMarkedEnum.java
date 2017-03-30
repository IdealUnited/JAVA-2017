/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * @author chaoyue
 *
 */
public enum BorrowingMarkedEnum {

	ALL("0", "无特殊要求"), DEBIT("1", "只借"), CRDIT("2", "只贷")

	;

	private String code;
	private String desc;

	private BorrowingMarkedEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static boolean isExists(String code) {
		for (BorrowingMarkedEnum item : BorrowingMarkedEnum.values()) {
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
