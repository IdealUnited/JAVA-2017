/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * @author chaoyue
 *
 */
public enum GatewayPayTypeEnum {
	
	ALL("ALL","全部"),
	E_PAY("EPAY","网银支付"),
	QUICK_PAY_PC("QUICKPAY_PC","快捷pc"),
	QUICK_PAY_MB("QUICKPAY_MB","快捷mobile"),
	QUICK_PAY_API("QUICKPAY_API","快捷API")
	;
	
	private String type;
	private String desc;
	
	private GatewayPayTypeEnum(String type,String desc){
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
		for (GatewayPayTypeEnum status : values()) {
			if (status.getType().equals(code)) {
				return true;
			}
		}
		return false;
	}
	
}
