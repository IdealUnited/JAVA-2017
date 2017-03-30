package com.idealunited.fi.commons;

/**
 * @author bpliang
 * 0：已过期
	1：正常
	2：已作废
 */
public enum PartnerExchangeRateStatusEnum {
	OUTOFDATE("0","已过期"),
	NORMAL("1","正常"),
	CANCELLATION("2","已作废");
	
    private String code;
	private String des;
	
	
	PartnerExchangeRateStatusEnum(String code,String des) {
		
		this.code = code;
		this.des = des;
	}



	
	public String getCode() {
		return code;
	}
	
	public String getDes() {
		return des;
	}




	public static PartnerExchangeRateStatusEnum getPartnerWebsiteConfigStatusEnum(String value) {
		if (value != null) {
			for (PartnerExchangeRateStatusEnum nameEnum : values()) {
				if (nameEnum.getCode().equals(value)) {
					return nameEnum;
				}
			}
		}
		return null;
	}
	
	
	public static boolean isCustomSysCodeEnum(String value) {
		if (value != null) {
			for (PartnerExchangeRateStatusEnum nameEnum : values()) {
				if (nameEnum.getCode().equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

}
