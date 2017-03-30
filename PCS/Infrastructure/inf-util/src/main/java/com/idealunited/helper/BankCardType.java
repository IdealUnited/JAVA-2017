/**
 *  File: BankCardType.java
 *  Description:
 *  Copyright 2010-2011 pay Corporation. All rights reserved.
 *  Date      Author      Changes
 *  2011-8-17   terry     Create
 *
 */
package com.idealunited.helper;

/**
 * 
 */
public enum BankCardType {

	DR(1,"借"),
	CR(2,"贷");
	
	private int value;
	private String desc;
	
	private BankCardType(int type,String desc){
		this.value = type;
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
	
	
}
