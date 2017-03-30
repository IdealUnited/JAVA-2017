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
public enum HNABusinessType2CCBEnum {

	//业务类型：0004-货物贸易；0005-酒店住宿；0006-机票旅游；0007-留学教育；0008-国际会展；0009-国际会议；0010-物流支付
	
	GOODS_TRADE("0004", "货物贸易", "02"),
	HOTEL("0005", "酒店住宿", "01"),
	TRIP("0006", "机票旅游", "01"),
	STUDY_ABROAD("0007", "留学教育", "01"),
	INTERNATIONAL_EXHIBITION("0008", "国际会展", "01"),
	INTERNATIONAL_CONFERENCE("0009", "国际会议", "01"),
	LOGISTICS_PAY("0010", "物流支付", "02"),
	OTHER_SERVICE("", "其他服务", "01");
	
	private String crossCode;
	private String desc;
	private String ccbTradeType;
	
	HNABusinessType2CCBEnum(String crossCode, String desc, String ccbTradeType){
		this.crossCode = crossCode;
		this.desc = desc;
		this.ccbTradeType = ccbTradeType;
	}

	/**
	 * @return the crossCode
	 */
	public String getCrossCode() {
		return crossCode;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return the ccbTradeType
	 */
	public String getCcbTradeType() {
		return ccbTradeType;
	}
	
	public static String getCCBTradeCode(String crossCode){
		for(HNABusinessType2CCBEnum ccb : values()){
			if(ccb.getCrossCode().equals(crossCode)){
				return ccb.getCcbTradeType();
			}
		}
		return OTHER_SERVICE.getCcbTradeType();
	}
	
}
