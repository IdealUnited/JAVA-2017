/**
 * 
 */
package com.idealunited.fi.helper;


/**
 * @author chaoyue
 *
 */
public enum CredoraxCurrencyCodeEnum {

	AUD("AUD", "澳元"),
	CHF("CHF", "瑞士法郎"),
	DKK("DKK", "丹麦克朗"),
	EUR("EUR", "欧元"), 
	GBP("GBP", "英镑"), 
	HKD("HKD", "港币"),
	JPY("JPY", "日元"), 
	NOK("NOK", "挪威克朗"),
	NZD("NZD", "新西兰元"), 
	SEK("SEK", "瑞典克朗"), 
	SGD("SGD", "新加坡元"), 
	USD("USD", "美元"), 
	
	//如下币种需要转换为美元
	BRL("BRL", "巴西里尔"),
	INR("INR", "印度卢比"),
	KRW("KRW", "韩元"),
	MOP("MOP", "澳门元"),
	MYR("MYR", "马来西亚林吉特"),
	PHP("PHP", "菲律宾比索"),
	RUB("RUB", "俄罗斯卢布"),
	THB("THB", "泰铢"),
	TWD("TWD", "新台币"),
	CAD("CAD", "加元"),
	;
	
	
	public static final CredoraxCurrencyCodeEnum[] TRANSFER_CURRENCYS = new CredoraxCurrencyCodeEnum[]{
		BRL,
		INR,
		KRW,
		MOP,
		MYR,
		PHP,
		RUB,
		THB,
		TWD,
		CAD
	};

	private String code;
	private String desc;

	private CredoraxCurrencyCodeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static boolean isExists(String code) {
		for (CredoraxCurrencyCodeEnum item : CredoraxCurrencyCodeEnum.values()) {
			if (item.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否需要转换
	 * @param code
	 * @return
	 */
	public static boolean isNeedChange(String code) {
		
		for (CredoraxCurrencyCodeEnum item : TRANSFER_CURRENCYS) {
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

}
