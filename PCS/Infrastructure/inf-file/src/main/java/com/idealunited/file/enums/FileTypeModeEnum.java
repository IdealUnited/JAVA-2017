package com.idealunited.file.enums;
/**
 * 文件类型枚举类
 * @author Administrator
 * 状态：1上载型文件,2下载型文件
 */
public enum FileTypeModeEnum {
	/**  1: 上载型文件 */
	UPLOAD("1", "上载型文件"),

	/**  2：下载型文件*/
	DOWNLOAD("2", "下载型文件 ");
	
	private final String code;
	private final String description;

	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	FileTypeModeEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 通过枚举<code>code</code>获得枚举
	 * 
	 * @param code
	 * @return
	 */
	public static FileTypeModeEnum getByCode(String code) {
		for (FileTypeModeEnum acctStatus : values()) {
			if (code.equals(acctStatus.getCode())) {
				return acctStatus;
			}
		}
		return null;
	}
}
