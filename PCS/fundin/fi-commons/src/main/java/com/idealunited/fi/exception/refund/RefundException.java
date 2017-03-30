/**
 * 
 */
package com.idealunited.fi.exception.refund;

import com.idealunited.fi.exception.ExceptionCodeEnum;
import com.idealunited.inf.exception.AppException;

/**
 * @Description 退款异常，需要回滚
 * @project gateway-pay
 * @file RefundException.java
 * @note <br>
 * @develop JDK1.6 + Eclipse 3.5
 * @version 1.0 Copyright 2006-2010 Corporation. All rights reserved. Date
 *          Author Changes 2010-7-13 Elx.OuYang Create
 */
public class RefundException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5506270952152440109L;

	/** 异常代码 */
	private ExceptionCodeEnum code = ExceptionCodeEnum.UN_KNOWN_EXCEPTION;

	public RefundException(String msg, ExceptionCodeEnum exceptionCodeEnum) {
		super(msg, exceptionCodeEnum);
	}

	public RefundException(String msg) {
		super(msg);
	}

	/**
	 * @return the code
	 */
	public ExceptionCodeEnum getCode() {
		return code;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RefundException [code=" + code + "]";
	}
}
