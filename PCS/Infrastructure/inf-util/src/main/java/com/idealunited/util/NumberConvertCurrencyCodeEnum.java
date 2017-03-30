package com.idealunited.util;

/**
 * 
 * @Description 账户类型转成对应币种符号
 * @project 	poss-membermanager
 * @file 		NumberConvertCurrencyCodeEnum.java 
 * @note		<br>
 * @develop		JDK1.6 + Eclipse 3.5
 * @version     1.0
 * Copyright © 2004-2013 pay.com . All rights reserved. 版权所有
 * Date				Author			Changes
 * 2010-10-22		gungun_zhang			Create
 */
 
public enum NumberConvertCurrencyCodeEnum {
	
	CNY("01","CNY", "人民币"), 
	USD("02","USD", "美元"), 
	EUR("03","EUR", "欧元"), 
	GBP("04","GBP", "英镑"), 
	HKD("05","HKD", "港币"), 
	TWD("06","TWD", "新台币"), 
	AUD("07","AUD", "澳元"), 
	CAD("08","CAD", "加元"), 
	INR("09","INR", "印度卢比"), 
	JPY("10","JPY", "日元"), 
	KRW("11","KRW", "韩元"), //KRW
	MOP("12","MOP", "澳门元"), 
	MYR("13","MYR", "马来西亚林吉特"), 
	NZD("14","NZD", "新西兰元"), 
	RUB("15","RUB", "俄罗斯卢布"), 
	SGD("16","SGD", "新加坡元"), 
	CHF("17","CHF", "瑞士法郎"), //CHF
	THB("18","THB", "泰铢"), 
	PHP("19","PHP", "菲律宾比索"), 
	SEK("20","SEK", "瑞典克朗"), 
	TRL("21","TRL", "土耳其里拉"), 
	ILS("22","ILS", "以色列谢尔克"), 
	DKK("23","DKK", "丹麦克朗"), 
	AED("24","AED", "阿联酋迪拉姆"), 
	NOK("25","NOK", "挪威克朗"), 
	KWD("26","KWD", "科威特第纳尔"), 
	BHD("27","BHD", "巴林第纳尔"), 
	OMR("28","OMR", "阿曼里亚尔"), 
	JOD("29","JOD", "约旦第纳尔"), 
	QAR("30","QAR", "卡塔尔里亚尔"), 
	KSA("31","KSA", "沙特阿拉伯里亚尔"), 
	CZK("32","CZK", "捷克克朗"), 
	MXN("33","MXN", "墨西哥比索"), 
	PLN("34","PLN", "波兰兹罗提"), 
	ZAR("35","ZAR", "南非兰特"), 
	BRL("36","BRL", "巴西里尔");
	
	private final String numNo;
	private final String code;
	private final String description;

	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	NumberConvertCurrencyCodeEnum(String numNo,String code, String description) {
		this.numNo = numNo;
		this.code = code;
		this.description = description;
	}

	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	public String getNumNo() {
		return numNo;
	}

	/**
	 * 通过枚举code获得枚举
	 * 
	 * @param code
	 * @return description
	 */
	public static NumberConvertCurrencyCodeEnum getByNumNo(String numNo) {
		for (NumberConvertCurrencyCodeEnum numberConvertCurrencyCode : values()) {
			if (numberConvertCurrencyCode.getNumNo().equals(numNo)) {
				return numberConvertCurrencyCode;
			}
		}
		return null;
	}
	public static String getDescriptionByCode(String code) {
		for (NumberConvertCurrencyCodeEnum numberConvertCurrencyCode : values()) {
			if (numberConvertCurrencyCode.getCode().equals(code)) {
				return numberConvertCurrencyCode.description;
			}
		}
		return null;
	}
	
}
