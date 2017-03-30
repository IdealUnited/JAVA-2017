package com.idealunited.fi.commons;

/**
 * 收款交易证件类型
 * @author S.L
 */
public enum CollectionOrderIDTypeEnum {
	
	ID("01","身份证"),
	OFFICER("02","军官证"),
	PASSPORT("03","护照"),
	HK_MACAO_COMPATRIOTS_1("04","回乡证"),
	TAIWAN_COMPATRIOTS_1("05","台胞证"),
	POLICE("06","警官证"),
	SOLDIER("07","士兵证"),
	RESIDENCE_BOOKLET("08","户口簿"),
	ICARD("09","临时身份证"),
	SOCIAL_SECURITY_CARD("10","社保卡"),
	HK_MACAO_COMPATRIOTS("11","港澳居民来往内地通行证"),// 同乡证
	TAIWAN_COMPATRIOTS("12","台湾同胞来往内地通行证"),// 同台胞证
	FOREIGNERS_RESIDENCE_PERMIT("13","外国人居留证"),
	HONGKONG_ID("14","香港身份证"),
	BUSINESS_LICENSE("20","营业执照"),
	ORGANIZATION_CODE("21","组织机构代码证"),
	TAX_REGISTRATION("22","税务登记证"),
	OTHER("99","其它证件");
	
	private String code;
	private String desc;
	
	/**
	 * CollectionOrderIDTypeEnum 
	 * @param type 类别
	 * @param desc 说明
	 */
	private CollectionOrderIDTypeEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 是否存在该枚举成员
	 * @param code
	 * @return
	 */
	public static boolean isExist(String code) {
		for (CollectionOrderIDTypeEnum status : values()) {
			if (status.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取枚举成员的代码
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 获取枚举成员的说明
	 * @return
	 */
	public String getDesc() {
		return desc;
	}
	
}
