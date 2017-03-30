package com.idealunited.fi.commons;

public enum SystemAlarmStatusEnum {
	
	CREATE(0,"创建"),
	PROCESS(1, "处理中"),
	SUCCESS(2, "处理成功");
	
	private final int code;
	private final String description;



	private SystemAlarmStatusEnum(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * 通过枚举<code>code</code>获得枚举
	 * 
	 * @param code
	 * @return
	 */
	public static SystemAlarmStatusEnum getByCode(int code) {
		for (SystemAlarmStatusEnum status : values()) {
			if (status.getCode() == code) {
				return status;
			}
		}
		return null;
	}

	/**
	 * 通过枚举<code>description</code>获得枚举
	 * 
	 * @param code
	 * @return
	 */
	public static SystemAlarmStatusEnum getByDescription(String description) {
		for (SystemAlarmStatusEnum status : values()) {
			if (status.getDescription().equals(description)) {
				return status;
			}
		}
		return null;
	}
	
}
