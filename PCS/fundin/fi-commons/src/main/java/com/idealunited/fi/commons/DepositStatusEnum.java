/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * @Description 充值状态
 * @project 	gateway-pay
 * @file 		DepositStatusEnum.java 
 * @note		<br>
 * @develop		JDK1.6 + Eclipse 3.5
 * @version     1.0
 * Copyright 2006-2011 HNA Corporation. All rights reserved.
 * Date				Author			Changes
 * 2010-7-12		Elx.OuYang		Create
 */
public enum DepositStatusEnum {
	
	/** 0---处理中*/
	CREATE(0, "处理中"),
	
	/** 1---充值成功 */
	SUCCESS(1, "充值成功"),

	/** 2---充值失败 */
	FAILURE(2, "充值失败"),
	
	DEPOSIT_BACK(3,"充值已充退");
	
	private final int code;
	private final String description;

	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	DepositStatusEnum(int code, String description) {
		this.code = code;
		this.description = description;
	}

	/**
	 * @return Returns the code.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 通过枚举<code>code</code>获得枚举
	 * 
	 * @param code
	 * @return
	 */
	public static DepositStatusEnum getByCode(int code) {
		for (DepositStatusEnum depositStatus : values()) {
			if (depositStatus.getCode() == code) {
				return depositStatus;
			}
		}
		return null;
	}
}
