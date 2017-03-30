/**
 * 
 */
package com.idealunited.fi.commons;



/**
 * @author chaoyue
 *
 */
public enum TradeTypeEnum {
	//支付类型：0001-担保交易,0002-即时付款,0003-跨境支付,0004-货物贸易,0005-酒店住宿,0006-机票旅游,0007-留学教育,0008-国际会展,0009-国际会议,0010-物流支付,0011-国际汇款
	//PREAUTH("2000","授权交易"),
	/*货物贸易	122030留学教育	223022航空机票	222024酒店住宿	223010   国际会展228025*/

	REALTIME("0001","担保交易","121990"),
	ASSURE("0002","即时付款","121990"),
	CROSSPAY("0003","跨境支付","121990"),
	GOODTRADE("0004","货物贸易","121990"),
	HOTELSERVICE("0005","酒店住宿","223010"),
	TICKETS("0006","机票旅游","222024"),
	EDUCATION("0007","留学教育","223022"),
	INTRSHOW("0008","国际会展","228025"),
	INTRMEETING("0009","国际会议","228990"),
	EXPRESSPAY("0010","物流支付","121990"),
	INTRREMITTANCE("0011","国际汇款","121990"),
	BILLSYSTEM("0012","订单系统","121990");
	
	private String code;
	private String desc;
	private String tradeCode;
	private TradeTypeEnum(String code,String desc,String tradeCode){
		this.code = code;
		this.desc = desc;
		this.tradeCode = tradeCode;
	}
	
	public static boolean isExists(String code) {
		for (TradeTypeEnum item : TradeTypeEnum.values()) {
			if (item.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	/**
	 * 通过枚举code获得枚举
	 * 
	 * @param code
	 * @return desc
	 */
	public static TradeTypeEnum getByCode(String code) {
		for (TradeTypeEnum tradeType : values()) {
			if (tradeType.getCode().equals(code)) {
				return tradeType;
			}
		}
		return null;
	}
	
}
