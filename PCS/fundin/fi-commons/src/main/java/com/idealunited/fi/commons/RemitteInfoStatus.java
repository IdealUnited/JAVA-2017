package com.idealunited.fi.commons;


/**
 * 退款错误返回代码与商户接入规范统一
 * @author fred.feng
 */
public enum RemitteInfoStatus {
	WAIT("0","未匹配"),
	PROCESSING("2","已匹配进行中"),
	PAY_SUCCESS("1","已匹配付款成功"),
	PAY_FAILED("4","已匹配付款失败"),
	
	RECIPT_PROCESS("5","已匹配收汇中"),
	RECIPT_SUCCESS("3","已匹配收汇成功"),
	RECIPT_REFUSE("6","已匹配收汇拒绝")
	;

 
	private String code;
	private String description;
	

	private RemitteInfoStatus(String code,String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return this.code;
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
	public static RemitteInfoStatus getByCode(String code) {
		for (RemitteInfoStatus status : values()) {
			if (status.getCode().equals(code)) {
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
	public static RemitteInfoStatus getByDescription(String description) {
		for (RemitteInfoStatus status : values()) {
			if (status.getDescription().equals(description)) {
				return status;
			}
		}
		return null;
	}
	
	public static String getDescription(String code){
		for (RemitteInfoStatus status : values()) {
			if (status.getCode().equals(code)) {
				return status.getDescription();
			}
		}
		return null;
	}

}
