/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * 交易主体
 * @author snn
 *
 */
public enum TradeSubjectEnum {
	/**
	 * 1：境内个人
	 * 2：境外个人
	 */
	INSIDE("1","境内个人"),
	OUTSIDE("2","境外个人"),
	;
	
	private String code;
	private String desc;
	
	private TradeSubjectEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static boolean isExists(String code) {
		for (TradeSubjectEnum item : TradeSubjectEnum.values()) {
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
