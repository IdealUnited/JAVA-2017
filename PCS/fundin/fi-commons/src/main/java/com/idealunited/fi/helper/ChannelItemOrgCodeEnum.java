/**
 * 
 */
package com.idealunited.fi.helper;

/**
 * @author chaoyue
 *
 */
public enum ChannelItemOrgCodeEnum {

	MOCK("00000000", "MOCK"), 
	@Deprecated
	BOCS("10076001", "中银卡司"), 
	BOC("10003001", "中国银行"), 
	@Deprecated
	CREDORAX("10075001","Credorax"), 
	
	NATIVE_BANK("00000000","NativeBank"), 
	;

	private String code;
	private String desc;

	private ChannelItemOrgCodeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static boolean isExists(String code) {

		for (ChannelItemOrgCodeEnum item : ChannelItemOrgCodeEnum.values()) {
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
