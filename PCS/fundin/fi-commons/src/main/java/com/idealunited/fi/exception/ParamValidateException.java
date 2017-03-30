package com.idealunited.fi.exception;

import com.idealunited.fi.exception.ExceptionCodeEnum;


/***********************************************************************
 * 
 * ParamValidateException.java 支付异常类
 * 
 * 版权所有， 受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。
 * 
 ***********************************************************************/

public class ParamValidateException extends BusinessException {

	private static final long serialVersionUID = 1L;

	private ExceptionCodeEnum code = ExceptionCodeEnum.ILLEGAL_PARAMETER;

	public ParamValidateException(String msg, ExceptionCodeEnum error) {
		super(msg, error);
		this.code = error;
	}

	public ParamValidateException(String msg) {
		super(msg);
	}

	@Override
	public String toString() {
		String s = getClass().getName();
		String message = getLocalizedMessage();
		return (message != null) ? (s + ": " + message)
				: (s + ": " + code.getCode() + "[" + code.getDescription() + "]。");
	}

	public ExceptionCodeEnum getCode() {
		return code;
	}

	public void setCode(ExceptionCodeEnum code) {
		this.code = code;
	}
}
