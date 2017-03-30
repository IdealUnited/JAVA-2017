/**
 * 
 */
package com.idealunited.util;



/**
 * @author chaoyue
 *
 */
public enum TradeTypeEnum {
	//支付类型：0001-担保交易,0002-即时付款,0003-跨境支付,0004-货物贸易,0005-酒店住宿,0006-机票旅游,0007-留学教育,0008-国际会展,0009-国际会议,0010-物流支付,0011-国际汇款
	//PREAUTH("2000","授权交易"), 
	/*货物贸易	122030留学教育	223022航空机票	222024酒店住宿	223010   国际会展228025*/

	REALTIME("0001","担保交易","121990","跨境电子商务网络购物(报关)进口支出","网络购物（报关）"),
	ASSURE("0002","即时付款","121990","跨境电子商务网络购物(报关)进口支出","网络购物（报关）"),
	CROSSPAY("0003","跨境支付","121990","跨境电子商务网络购物(报关)进口支出","网络购物（报关）"),
	GOODTRADE("0004","货物贸易","121990","跨境电子商务网络购物(报关)进口%1s支出","网络购物（报关）"),
	HOTELSERVICE("0005","酒店住宿","223010","跨境电子商务公务旅行支出","因公旅行机票款（不跨境）"),
	TICKETS("0006","机票旅游","222024","跨境电子商务跨境机票款支出","跨境机票款"),
	EDUCATION("0007","留学教育","223022","跨境电子商务一年以上出境培训费支出","一年以上因公跨境教育培训支出"),
	INTRSHOW("0008","国际会展","228025","跨境电子商务展会服务支出","跨境电子商务展会服务支出"),
	INTRMEETING("0009","国际会议","228990","跨境电子商务国际会议服务支出","跨境电子商务国际会议服务支出"),
	EXPRESSPAY("0010","物流支付","121990","跨境电子商务网络购物(报关)进口支出","网络购物（报关）"),
	INTRREMITTANCE("0011","国际汇款","121990","跨境电子商务网络购物(报关)进口支出","网络购物（报关）"),
	BILLSYSTEM("0012","订单系统","121990","跨境电子商务网络购物(报关)进口支出","网络购物（报关）");
	
	private String code;
	private String desc;
	private String tradeCode;
	private String tradeCodeDesc;
	private String spdbTradeCodeDesc;
	private TradeTypeEnum(String code,String desc,String tradeCode,String tradeCodeDesc,String spdbTradeCodeDesc){
		this.code = code;
		this.desc = desc;
		this.tradeCode = tradeCode;
		this.tradeCodeDesc = tradeCodeDesc;
		this.spdbTradeCodeDesc = spdbTradeCodeDesc;
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

	/**
	 * 通过枚举code获得枚举
	 * 
	 * @param code
	 * @return desc
	 */
	public static String getSpdbTradeCodeDescByCode(String code) {
		for (TradeTypeEnum tradeType : values()) {
			if (tradeType.getTradeCode().equals(code)) {
				return tradeType.getSpdbTradeCodeDesc();
			}
		}
		return null;
	}

	public String getTradeCodeDesc() {
		return tradeCodeDesc;
	}

	public void setTradeCodeDesc(String tradeCodeDesc) {
		this.tradeCodeDesc = tradeCodeDesc;
	}

	public String getSpdbTradeCodeDesc() {
		return spdbTradeCodeDesc;
	}

	public void setSpdbTradeCodeDesc(String spdbTradeCodeDesc) {
		this.spdbTradeCodeDesc = spdbTradeCodeDesc;
	}
	
}
