/**
 * 
 */
package com.idealunited.fi.exception.refund;

import com.idealunited.fi.exception.ExceptionCodeEnum;
import com.idealunited.inf.exception.AppUnTxException;

/**
 * @Description 退款异常，无需回滚
 * @project gateway-pay
 * @file RefundUnTxException.java
 * @note <br>
 * @develop JDK1.6 + Eclipse 3.5
 * @version 1.0 Copyright 2006-2010 Corporation. All rights reserved. Date
 *          Author Changes 2010-7-15 Elx.OuYang Create
 */
public class RefundUnTxException extends AppUnTxException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6687442967882712973L;

	/** 异常代码 */
	private ExceptionCodeEnum code = ExceptionCodeEnum.UN_KNOWN_EXCEPTION;

	public RefundUnTxException(String msg, ExceptionCodeEnum exceptionCodeEnum) {
		super(msg, exceptionCodeEnum);
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
		return "RefundUnTxException [code=" + code + "]";
	}
}
