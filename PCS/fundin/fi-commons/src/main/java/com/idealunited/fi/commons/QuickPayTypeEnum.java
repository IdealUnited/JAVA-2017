/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * 快捷支付类型
 * @author KANGZHIGUANG
 *
 */
public enum QuickPayTypeEnum {

	FIRST_PAYMENT("1","首次支付"),
	SECOND_PAYMENT("2","二次支付"),
	;
	
	private String code;
	private String desc;
	
	private QuickPayTypeEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static boolean isExists(String code) {
		for (QuickPayTypeEnum item : QuickPayTypeEnum.values()) {
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

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
