/**
 * 
 */
package com.idealunited.gateway.commons;

/**
 * @author kangzhiguang
 *
 */
public enum ProductExtendsType {

	/*IN_GATEWAY_ACC_PAY("101","帐户支付"), 
	IN_GATEWAY_B2C_NET_PAY("102","普通B2C网银"), 
	IN_GATEWAY_CREDIT_BIG_PAY("103","信用卡大额"),
	OUT_GATEWAY_ACCT_PAY("201","帐户支付"), 
	OUT_GATEWAY_BANK_REMMITE("202","银行汇款"), 
	OUT_GATEWAY_EXCHANGE_CARD("203","外卡收单"),*/
	INTERNATION_REMMITE("301","国际汇款")/*,
	NATIVE_CNY_PAYMENT("401","境内人民币付款"), 
	NATIVE_EXCHANGE_PAYMENT("402","境内外币付款"), 
	EXCHANGE_CNY_PAYMENT("403","境外人民币付款"), 
	EXCHANGE_EXCHANGE_PAYMENT("404","境外外币付款"), 
	NATIVE_CNY_WITHDRAW("501","境内人民币提现"), 
	NATIVE_EXCHANGE_WITHDRAW("502","境内外币提现"),
	EXCHANGE_CNY_WITHDRAW("503","境外人民币提现"), 
	EXCHANGE_EXCHANGE_WITHDRAW("504","境外外币提现")*/;
	
	private String type;
	private String desc;
	
	private ProductExtendsType(String type,String desc){
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
		for (ProductExtendsType item : ProductExtendsType.values()) {
			if (item.getType().equals(type)) {
				return true;
			}
		}
		return false;
	}
	
}
