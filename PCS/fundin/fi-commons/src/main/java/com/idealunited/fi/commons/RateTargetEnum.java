/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * 汇率目标类型
 * 0 表示针对所有商户
 * 1 表示针对特定商户
 * @author peiyu.yang
 * @since 2015年6月29日
 */
public enum RateTargetEnum {

	RSA("0","所有"),
	MD5("1","商户");
	
	private String code;
	private String desc;
	
	private RateTargetEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public static boolean isExists(String code) {
		for (RateTargetEnum item : RateTargetEnum.values()) {
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
