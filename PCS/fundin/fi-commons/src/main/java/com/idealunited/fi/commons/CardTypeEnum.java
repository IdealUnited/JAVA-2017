/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * @author chaoyue
 *
 */
public enum CardTypeEnum {
	
	//卡种:1-借记卡,2-贷记卡,3-准贷记卡,4-预付费卡,5-准借记卡
	
	DEBIT_CARD("1","借用卡"),
	CREBIT_CARD("2","贷记卡"),
	SEMI_CREBIT_CARD("3","准贷记卡"),
	PRE_CARD("4","预付费卡"),
	SEMI_DEBIT_CARD("5","准借记卡"),
	;
	
	private String type;
	private String desc;
	
	private CardTypeEnum(String type,String desc){
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}
	
	
	public static boolean isExist(String code) {
		for (CardTypeEnum status : values()) {
			if (status.getType().equals(code)) {
				return true;
			}
		}
		return false;
	}
	
}
