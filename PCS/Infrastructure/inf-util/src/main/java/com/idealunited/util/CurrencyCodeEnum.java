/**
 * 
 */
package com.idealunited.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * @author chaoyue
 *
 */
public enum CurrencyCodeEnum {
	CNY("CNY", "人民币"), 
	USD("USD", "美元"), 
	EUR("EUR", "欧元"), 
	GBP("GBP", "英镑"), 
	HKD("HKD", "港币"), 
	//TWD("TWD", "新台币"), 
	AUD("AUD", "澳元"), 
	CAD("CAD", "加元"), 
	//INR("INR", "印度卢比"), 
	JPY("JPY", "日元"), 
	/*KRW("KRW", "韩元"), //KRW
	MOP("MOP", "澳门元"), 
	MYR("MYR", "马来西亚林吉特"), 
	NZD("NZD", "新西兰元"), 
	RUB("RUB", "俄罗斯卢布"), 
	SGD("SGD", "新加坡元"), 
	CHF("CHF", "瑞士法郎"), //CHF
	THB("THB", "泰铢"), 
	PHP("PHP", "菲律宾比索"), 
	SEK("SEK", "瑞典克朗"), 
	TRL("TRL", "土耳其里拉"), 
	ILS("ILS", "以色列谢尔克"), 
	DKK("DKK", "丹麦克朗"), 
	AED("AED", "阿联酋迪拉姆"), 
	NOK("NOK", "挪威克朗"), 
	KWD("KWD", "科威特第纳尔"), 
	BHD("BHD", "巴林第纳尔"), 
	OMR("OMR", "阿曼里亚尔"), 
	JOD("JOD", "约旦第纳尔"), 
	QAR("QAR", "卡塔尔里亚尔"), 
	KSA("KSA", "沙特阿拉伯里亚尔"), 
	CZK("CZK", "捷克克朗"), 
	MXN("MXN", "墨西哥比索"), 
	PLN("PLN", "波兰兹罗提"), 
	ZAR("ZAR", "南非兰特"), 
	BRL("BRL", "巴西里尔"),
	BGN("BGN","保加利亚列弗"),
	PYG("PYG","乌拉圭比索"),
	VND("VND","越南盾"),*/
	;

	private String code;
	private String desc;

	private CurrencyCodeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static boolean isExists(String code) {
		for (CurrencyCodeEnum item : CurrencyCodeEnum.values()) {
			if (item.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public static String getCurrencyNameByCode(String code) {
		for (CurrencyCodeEnum item : CurrencyCodeEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getDesc();
			}
		}
		return null;
	}
	
	public static CurrencyCodeEnum getCurrencyByCode(String code) {
		for (CurrencyCodeEnum item : CurrencyCodeEnum.values()) {
			if (item.getCode().equals(code)) {
				return item;
			}
		}
		return null;
	}
	
	public static Map<String,String> getAllCurrency() {
		Map<String,String> currencyMap = new HashMap<String,String>();
		for (CurrencyCodeEnum item : CurrencyCodeEnum.values()) {
			currencyMap.put(item.getCode(), item.getDesc());
		}
		return currencyMap;
	}
	
	public static Map<String,String> getAllCurrencyNoCCY(String ccy) {
		Map<String,String> currencyMap = new HashMap<String,String>();
		for (CurrencyCodeEnum item : CurrencyCodeEnum.values()) {
			if(!StringUtils.equals(item.getCode(), ccy)){
				currencyMap.put(item.getCode(), item.getDesc());
			}
		}
		return currencyMap;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
