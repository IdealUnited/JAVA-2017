package com.idealunited.inf.enums;

public enum ResponseCodeEnum {
	
	SUCCESS("0000", "请求处理成功"), 
	PROCESSING("0001", "请求已受理"),
	FAIL("0002", "处理失败"),
	MSG_PARSING_FAILURE("6001", "报文解析失败"),
	MSG_INVALID_SIGN("6002", "报文签名验证失败"),  
	COMPRESS_FAILURE("6003", "报文压缩处理失败"), 
	UNCOMPRESS_FAILURE("6004", "报文解压处理失败"),
	INVALID_DATA("6005", "数据校验失败"),
	INVALID_PARAM("6100", "无效的请求参数"), 
	VALID_TARGETSYSCODE_FAILURE("6101", "目标系统校验失败"), 
	VALID_DATAMSGSIZE_FAILURE("6102","请求报文内容长度校验失败"), 
	UNDEFINED_SERVICE("6103", "请求服务代码未定义"), 
	INVALID_PARAMETER("6104", "参数校验未通过"), 
	UPDATE_ERROR("6105", "该记录不能修改,正在修改审核中"), 
	ADD_ERROR("6106", "该条纪录已经存在"), 
	INVALID_SYSTEM_CODE("6107", "无效的系统编号"), 
	DB_ADD_FAILURE("6201", "新增处理失败"), 
	DB_UPDATE_FAILURE("6202", "更新处理失败"), 
	DB_INVALID_DATA("6203", "没查到有效的记录"), 
	DB_DATA_ALREADY_EXISTS("6204", "记录已存在"), 
	ENCRYP_MACHINE_HANDLE_FAILURE("7000", "加密机处理失败"), 
	ENCRYP_MACHINE_ACCESS_FAILURE("9001","加密机访问失败"),
	DATA_ACCESS_ERROR("9002","数据访问错误"),
	DATA_RESOURCE_BUSY("9003","数据资源忙"),
	SYSTEM_RESPONSE_TIMEOUT("9004","系统响应超时"), 
	SYSTEM_HNAPAY_CONNECTION("9005","连接银行前置失败"),
	PRE_SYS_LOGIN_FAIL("9006","前置登陆失败"),
	UNDEFINED_ERROR("9999", "未知错误"),
	TRADING_CODE_NOT_EXISTS("6060","交易码类型不存在"),
	PINGAN_TIME_OUT("5555","平安响应超时"),
	PINGAN_UNIQUE_CONSTRAINT("6666","平安数据插入异常：存在唯一性约束")
	;

	private String code;
	private String desc;

	ResponseCodeEnum(String code, String desc) {

		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static ResponseCodeEnum getResponseCodeEnum(String value) {
		if (value != null) {
			for (ResponseCodeEnum nameEnum : values()) {
				if (nameEnum.getCode().equals(value)) {
					return nameEnum;
				}
			}
		}
		return null;
	}

	public static boolean isResponseCodeEnum(String value) {
		if (value != null) {
			for (ResponseCodeEnum nameEnum : values()) {
				if (nameEnum.getCode().equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

}
