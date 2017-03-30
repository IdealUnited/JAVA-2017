/**
 * 
 */
package com.idealunited.fi.exception;

import com.idealunited.fi.exception.ExceptionCodeEnum;
import com.idealunited.inf.exception.AppException;

/**
 * @Description 付款异常，需要回滚
 * @project 	gateway-pay
 * @file 		PaymentException.java 
 * @note		<br>
 * @develop		JDK1.6 + Eclipse 3.5
 * @version     1.0
 * Copyright 2006-2010  Corporation. All rights reserved.
 * Date				Author			Changes
 * 2010-7-13		Elx.OuYang		Create
 */
public class PaymentException extends AppException{

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 5914509897914552561L;
	
	/** 异常代码 */
    private ExceptionCodeEnum code             = ExceptionCodeEnum.UN_KNOWN_EXCEPTION;

	public PaymentException(String msg, ExceptionCodeEnum exceptionCodeEnum) {
		super(msg,exceptionCodeEnum);
		this.code = exceptionCodeEnum;
	}
	
	/**
	 * @return the code
	 */
	public ExceptionCodeEnum getCode() {
		return code;
	}

	@Override
    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        return (message != null) ? (s + ": " + message) : (s + ": " + code.getCode() + "["
                                                           + code.getDescription() + "]。");
    }	
}
