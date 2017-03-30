/**
 * 
 */
package com.idealunited.gateway.commons;

/**
 * @author kangzhiguang
 *
 */
public enum ProductType {

	/*IN_GAYEWAY("001","进口网关"),
	OUT_GATEWAY("002","出口网关"),*/ 
	INTERNATION_REMMITE("003","国际汇款")/*,
	PAYMENT("004","付款"), 
	WITHDRAW("005","提现"), 
	DEPOSIT("006","充值"), 
	TRANSFER("007","转账"), 
	OTHERS("008","其它增值产品")*/;
	
	private String type;
	private String desc;
	
	private ProductType(String type,String desc){
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
		for (ProductType item : ProductType.values()) {
			if (item.getType().equals(type)) {
				return true;
			}
		}
		return false;
	}
	
}
