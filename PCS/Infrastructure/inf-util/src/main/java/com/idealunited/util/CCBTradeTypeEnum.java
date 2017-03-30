/**
 *  File: HNABusinessType2CCBEnum.java
 *  Date: 2016年5月9日
 *  Author: Simen.Liu 
 *  Changes: Create
 *  Copyright 2006-2016 HNAPAY Corporation. All rights reserved.
 *  Description:
 *
 */
package com.idealunited.util;

/**
 * 
 */
public enum CCBTradeTypeEnum {
	
	SERVICE_TRADE("01", "服务贸易", "223022"),
	GOODS_TRADE("02", "货物贸易", "121010");

	private String code;
	private String desc;
	private String defaultTradeCode;
	
	CCBTradeTypeEnum(String code, String desc, String defaultTradeCode){
		this.code = code;
		this.desc = desc;
		this.defaultTradeCode = defaultTradeCode;
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return the defaultTradeCode
	 */
	public String getDefaultTradeCode() {
		return defaultTradeCode;
	}
	
	/**
	 * 通过业务编码，找默认的交易编码
	 * @param code
	 * @return
	 */
	public static String getDefaultTradeCode(String code){
		for(CCBTradeTypeEnum ccb : values()){
			if(ccb.getCode().equals(code)){
				return ccb.getDefaultTradeCode();
			}
		}
		return null;
	}
	
}
