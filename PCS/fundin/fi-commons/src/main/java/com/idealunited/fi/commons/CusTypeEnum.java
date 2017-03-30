/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * 客户种类
 * @author snn
 *
 */
public enum CusTypeEnum {

	PERSON("D","个人"),
	ORGCODE("C","机构"),
	;
	
	private String code;
	private String desc;
	
	private CusTypeEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static boolean isExists(String code) {
		for (CusTypeEnum item : CusTypeEnum.values()) {
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
