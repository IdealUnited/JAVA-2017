package com.idealunited.inf.params;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.idealunited.inf.enums.ResponseCodeEnum;
import com.idealunited.util.JSonUtil;

/**
 * 响应报文抽象类
 */
public class HessianInvokeResponse {

	private static final ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>();
	private String responseCode; // 应答码

	private String responseDesc; // 应答描述

	private String responseDate; // 应答时间
	
	public HessianInvokeResponse(){
		this.responseCode = ResponseCodeEnum.SUCCESS.getCode();
		this.responseDesc = ResponseCodeEnum.SUCCESS.getDesc();
		if (sdf.get() == null) {
			sdf.set(new SimpleDateFormat("yyyyMMdd HH:mm:ss"));
		}
		responseDate = sdf.get().format(new Date());
	}

	protected HessianInvokeResponse(String responseCode, String responseDesc) {
		this.responseCode = responseCode;
		this.responseDesc = responseDesc;

		if (sdf.get() == null) {
			sdf.set(new SimpleDateFormat("yyyyMMdd HH:mm:ss"));
		}
		responseDate = sdf.get().format(new Date());
	}

	/**
	 * @return the sdf
	 */
	public static ThreadLocal<SimpleDateFormat> getSdf() {
		return sdf;
	}

	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @return the responseDesc
	 */
	public String getResponseDesc() {
		return responseDesc;
	}

	/**
	 * @return the responseDate
	 */
	public String getResponseDate() {
		return responseDate;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}

	public String toJson() {
		String serializedStr = null;
		try {
			serializedStr = JSonUtil.toJSonString(this);
		} catch (Exception e) {
			throw new RuntimeException("Error when serializing response", e);
		}
		return serializedStr;
	}
}
