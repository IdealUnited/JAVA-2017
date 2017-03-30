package com.idealunited.fi.commons;

/**
 * 订单状态：0-创建，1-收款中 2-收款成功 3-收款失败 4-已失效
 * @author S.L
 */
public enum CollectionOrderStatusEnum {
	
	CREATE("0", "创建"),
	PAYEEING("1", "收款中"),
	SUCC("2", "收款成功"),
	FAIL("3", "收款失败"),
	EXPIRATION("4", "已失效");
	
	private String code;
	private String desc;
	
	/**
	 * CollectionOrderIDTypeEnum 
	 * @param type 类别
	 * @param desc 说明
	 */
	private CollectionOrderStatusEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 是否存在该枚举成员
	 * @param code
	 * @return
	 */
	public static boolean isExist(String code) {
		for (CollectionOrderStatusEnum status : values()) {
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
