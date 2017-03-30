package com.idealunited.util;

public enum ChannelEnum {
	CCB("CCB","中国建设银行","10004001"),
	BOC("BOC","中国银行","10003001"),
	ABC("ABC","中国农业银行","10002001"),
	ICBC("ICBC","中国工商银行","10001001"),
	CMSB("CMSB","民生银行","10007001"),
	CMBC("CMBC","招行","10006001"),
	CDB("CDB","国家开发银行",""),
	CIB("CIB","兴业银行","10008001"),
	BCCB("BCCB","北京市商业银行","10019001"),
	PINGAN("PINGAN","平安","10017001"),
	SPDB("SPDB","浦发银行","10009001"),
	HSBC("HSBC","汇丰银行","");
	
	private String code;
	private String desc;
	private String bankCode;

	private ChannelEnum(String code, String desc,String bankCode) {
		this.code = code;
		this.desc = desc;
		this.bankCode = bankCode;
	}

	public static boolean isExists(String code) {
		for (ChannelEnum item : ChannelEnum.values()) {
			if (item.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}
	
	public static String getChannelNameByCode(String code) {
		for (ChannelEnum item : ChannelEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getDesc();
			}
		}
		return null;
	}

	public static String getChannelBankNameByCode(String code) {
		for (ChannelEnum item : ChannelEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getBankCode();
			}
		}
		return null;
	}
	
	public static ChannelEnum getChannelByCode(String code) {
		for (ChannelEnum item : ChannelEnum.values()) {
			if (item.getCode().equals(code)) {
				return item;
			}
		}
		return null;
	}

	/**
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return
	 */
	public String getDesc() {
		return desc;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
