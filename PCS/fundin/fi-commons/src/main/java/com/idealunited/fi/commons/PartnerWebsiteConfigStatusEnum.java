package com.idealunited.fi.commons;

/**
 * @author bpliang
 *  0：冻结
	1：正常
	2：待审核
	3：审核未通过
	4：已删除
 */
public enum PartnerWebsiteConfigStatusEnum {
	FREEZE("0","冻结"),
	NORMAL("1","正常"),
	CHECKING("2","待审核"),
	UNCHECK("3","审核未通过"),
	DELETE("4","已删除");
	
    private String code;
	private String des;
	
	
	PartnerWebsiteConfigStatusEnum(String code,String des) {
		
		this.code = code;
		this.des = des;
	}



	
	public String getCode() {
		return code;
	}
	
	public String getDes() {
		return des;
	}




	public static PartnerWebsiteConfigStatusEnum getPartnerWebsiteConfigStatusEnum(String value) {
		if (value != null) {
			for (PartnerWebsiteConfigStatusEnum nameEnum : values()) {
				if (nameEnum.getCode().equals(value)) {
					return nameEnum;
				}
			}
		}
		return null;
	}
	
	
	public static boolean isCustomSysCodeEnum(String value) {
		if (value != null) {
			for (PartnerWebsiteConfigStatusEnum nameEnum : values()) {
				if (nameEnum.getCode().equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

}
