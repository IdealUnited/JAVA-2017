/**
 * 
 */
package com.idealunited.gateway.commons;

/**
 * @author kangzhiguang
 *
 */
public enum FeeType {

	PAYER_FEE("0","付款方付手续费")/*,
	PAYEE_FEE("1","收款方付手续费")*/;
	
	private String type;
	private String desc;
	
	private FeeType(String type,String desc){
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}
	
	public static boolean isExists(String type) {
		for (FeeType item : FeeType.values()) {
			if (item.getType().equals(type)) {
				return true;
			}
		}
		return false;
	}
	
}
