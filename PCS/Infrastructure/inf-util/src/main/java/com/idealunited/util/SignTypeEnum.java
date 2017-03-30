package com.idealunited.util;

/**
 * @Description 签名类型的枚举
 * @project gateway-pay
 * @file SignTypeEnum.java
 * @note <br>
 * @develop JDK1.6 + Eclipse 3.5
 * @version 1.0 Copyright © 2004-2013 pay.com . All rights reserved. 版权所有
 *          	Author Changes 2011-4-13 Sean.Chen Create
 */


public enum SignTypeEnum {

	RSA("1", "RSA"), MD5("2", "MD5"),SHA512("3","SHA512");

	private String code;
	private String description;

	/**
	 * @param value
	 * @param description
	 */
	private SignTypeEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	/**
	 * 通过code获得枚举
	 * @param code
	 * @return
	 */
	public static SignTypeEnum getByCode(String code) {
		for (SignTypeEnum signType : values()) {
			if (signType.getCode().equals(code.trim())) {
				return signType;
			}
		}
		return null;
	}
	
}
