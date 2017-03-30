package com.idealunited.file.enums;
/**
 * 文件传输模式枚举类
 * @author Administrator
 * 状态：1本地路径 2 FTP方式
 */
public enum FileTransModeEnum {
	/**  1:本地路径  */
	LOCAL("1", "本地路径"),

	/**  2：FTP方式 */
	FTP("2", "FTP方式 ");
	
	private final String code;
	private final String description;

	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	FileTransModeEnum(String code, String description) {
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
	public static FileTransModeEnum getByCode(String code) {
		for (FileTransModeEnum acctStatus : values()) {
			if (code.equals(acctStatus.getCode())) {
				return acctStatus;
			}
		}
		return null;
	}
}
