/**
 *  File: CCBTradeEnum.java
 *  Date: 2016年5月9日
 *  Author: Simen.Liu 
 *  Changes: Create
 *  Copyright 2006-2016 HNAPAY Corporation. All rights reserved.
 *  Description:
 *
 */
package com.idealunited.util;

/**
 * 建行交易编码
 */
public enum CCBTradeCodeEnum {

	STUDY_ABROAD_YEAR_OUT("223022","海外留学-留学及教育相关旅行（一年以上）", "中国居民在境外居留，接收非居民提供的教育和培训所支付的款项。", 200, "服务贸易"),
	STUDY_ABROAD_YEAR_IN("223023","海外留学-留学及教育相关旅行（一年及一下）", "中国居民在境外居留，接收非居民提供的教育和培训所支付的款项。", 200, "服务贸易"),
	/*STUDY_ABROAD_YEAR_IN("223023","海外留学-留学及教育相关旅行（一年以上）", "中国居民在境外居留，接收非居民提供的教育和培训所支付的款项。", 200, "服务贸易"),
	STUDY_ABROAD_YEAR_IN("223023","海外留学-留学及教育相关旅行（一年以上）", "中国居民在境外居留，接收非居民提供的教育和培训所支付的款项。", 200, "服务贸易"),
	STUDY_ABROAD_YEAR_IN("223023","海外留学-留学及教育相关旅行（一年以上）", "中国居民在境外居留，接收非居民提供的教育和培训所支付的款项。", 200, "服务贸易"),
	STUDY_ABROAD_YEAR_IN("223023","海外留学-留学及教育相关旅行（一年以上）", "中国居民在境外居留，接收非居民提供的教育和培训所支付的款项。", 200, "服务贸易"),
	STUDY_ABROAD_YEAR_IN("223023","海外留学-留学及教育相关旅行（一年以上）", "中国居民在境外居留，接收非居民提供的教育和培训所支付的款项。", 200, "服务贸易"),
	STUDY_ABROAD_YEAR_IN("223023","海外留学-留学及教育相关旅行（一年以上）", "中国居民在境外居留，接收非居民提供的教育和培训所支付的款项。", 200, "服务贸易"),
	STUDY_ABROAD_YEAR_IN("223023","海外留学-留学及教育相关旅行（一年以上）", "中国居民在境外居留，接收非居民提供的教育和培训所支付的款项。", 200, "服务贸易"),
	STUDY_ABROAD_YEAR_IN("223023","海外留学-留学及教育相关旅行（一年以上）", "中国居民在境外居留，接收非居民提供的教育和培训所支付的款项。", 200, "服务贸易"),
	STUDY_ABROAD_YEAR_IN("223023","海外留学-留学及教育相关旅行（一年以上）", "中国居民在境外居留，接收非居民提供的教育和培训所支付的款项。", 200, "服务贸易"),
	STUDY_ABROAD_YEAR_IN("223023","海外留学-留学及教育相关旅行（一年以上）", "中国居民在境外居留，接收非居民提供的教育和培训所支付的款项。", 200, "服务贸易"),*/

	GENERAL_TRADE("121010","网站销售进口商品-一般贸易", "我国有进出口经营权的企业单边进口或单边出口的货物。", 200, "服务贸易"),
	HOTEL_SCENICSPOTS_B2B("223010","酒店及风景区门票预订-公务及商务旅行", "为公务和商务目的旅行的个人在旅游地货物和服务消费。", 10, "服务贸易"),
	HOTEL_SCENICSPOTS_B2C("223029","酒店及风景区门票预订-其他私人旅行", "包括个人因非商务目的出国时购买的货物和服务。", 10, "服务贸易");
	
	private String code;
	private String comment;
	private String desc;
	private long limitAmount;
	private String businessType;
	
	/**
	 * 
	 * @param code
	 * @param comment
	 * @param desc
	 * @param limitAmount
	 * @param businessType
	 */
	CCBTradeCodeEnum(String code, String comment, String desc, long limitAmount, String businessType){
		this.code = code;
		this.comment = comment;
		this.desc = desc;
		this.limitAmount = limitAmount;
		this.businessType = businessType;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return the limitAmount
	 */
	public long getLimitAmount() {
		return limitAmount;
	}

	/**
	 * @return the businessType
	 */
	public String getBusinessType() {
		return businessType;
	}
	
	public static CCBTradeCodeEnum getCCBTradeCodeEnum(String code){
		for(CCBTradeCodeEnum ccb : values()){
			if(ccb.getCode().equals(code)){
				return ccb;
			}
		}
		return null;
	}
	
}
