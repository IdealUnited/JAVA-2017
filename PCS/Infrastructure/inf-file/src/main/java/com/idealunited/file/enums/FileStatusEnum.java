package com.idealunited.file.enums;
/**
 * 文件状态枚举类
 * @author Administrator
 * 状态：1上载或下载中,2上载或下载成功,3上载或下载失败4上传或下载通知成功5上传或下载通知失败
 */
public enum FileStatusEnum {
	/**  1:上载或下载中  */
	UPLOAD_OR_DOWNLOAD("1", "上载或下载中"),

	/**  2：上载或下载成功 */
	UPLOAD_OR_DOWNLOAD_SUCCESS("2", "上载或下载成功 "),
	
	/**  3：上载或下载失败 */
	UPLOAD_OR_DOWNLOAD_FAIL("3", "上载或下载失败 "),
	
	/**  4：上传或下载通知成功 */
	UPLOAD_OR_DOWNLOAD_NOTIFY_SUCCESS("4", "上传或下载通知成功 "),
	
	/**  5：上传或下载通知失败*/
	UPLOAD_OR_DOWNLOAD_NOTIFY_FAIL("5", "上传或下载通知失败 ");
	
	private final String code;
	private final String description;

	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param description
	 */
	FileStatusEnum(String code, String description) {
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
	public static FileStatusEnum getByCode(String code) {
		for (FileStatusEnum acctStatus : values()) {
			if (code.equals(acctStatus.getCode())) {
				return acctStatus;
			}
		}
		return null;
	}
}
