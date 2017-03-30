package com.idealunited.fi.commons;

/**
 * 支付单报送, 报关返回状态: 3，报关已受理；4，报关未受理
 * @author S.L
 */
public enum CollectionDeclareResultEnum {

	UNSUPPORT_AUTO("0", "不支持自动报关"),
	SUPPORT_AUTO("1", "支持自动报关"),
	ACCEPT_ING("3", "报关已受理"),
	ACCEPT_OFF("4", "报关未受理");
	
	private String code;
	private String desc;
	
	/**
	 * CollectionOrderIDTypeEnum 
	 * @param type 类别
	 * @param desc 说明
	 */
	private CollectionDeclareResultEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 是否存在该枚举成员
	 * @param code
	 * @return
	 */
	public static boolean isExist(String code) {
		for (CollectionDeclareResultEnum status : values()) {
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
