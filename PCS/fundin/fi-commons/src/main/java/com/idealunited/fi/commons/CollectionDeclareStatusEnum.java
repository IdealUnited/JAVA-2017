package com.idealunited.fi.commons;

/**
 * 支付单报送, 报关状态: 0-报关信息齐全，等待报关 1-报关信息不全，不支持报关 2-报关成功 3-报关失败
 * @author S.L
 */
public enum CollectionDeclareStatusEnum {

	/**
	 * 报关信息齐全,等待报关:0
	 */
	WAIT("0", "报关信息齐全,等待报关"),
	
	/**
	 * 报关信息不全,不支持报关:1
	 */
	UNSUPPORTED("1", "报关信息不全,不支持报关"),
	
	/**
	 * 报关成功:2
	 */
	SUCC("2", "报关成功"),
	
	/**
	 * 报关失败:3
	 */
	FAIL("3", "报关失败");
	
	private String code;
	private String desc;
	
	/**
	 * CollectionOrderIDTypeEnum 
	 * @param type 类别
	 * @param desc 说明
	 */
	private CollectionDeclareStatusEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 是否存在该枚举成员
	 * @param code
	 * @return
	 */
	public static boolean isExist(String code) {
		for (CollectionDeclareStatusEnum status : values()) {
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
	
	/**
	 * 是否存在该枚举成员
	 * @param code
	 * @return
	 */
	public static CollectionDeclareStatusEnum get(String code) {
		for (CollectionDeclareStatusEnum status : values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}
	
}
