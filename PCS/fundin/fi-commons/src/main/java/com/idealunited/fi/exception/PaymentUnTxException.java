/**
 * 
 */
package com.idealunited.fi.exception;

import com.idealunited.fi.exception.ExceptionCodeEnum;
import com.idealunited.inf.exception.AppUnTxException;

/**
 * @Description 付款异常，无需回滚
 * @project 	gateway-pay
 * @file 		PaymentUnTxException.java 
 * @note		<br>
 * @develop		JDK1.6 + Eclipse 3.5
 * @version     1.0
 * Copyright 2006-2010  Corporation. All rights reserved.
 * Date				Author			Changes
 * 2010-7-15		Elx.OuYang		Create
 */
public class PaymentUnTxException extends AppUnTxException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8452521683710723233L;

	/** 异常代码 */
    private ExceptionCodeEnum code             = ExceptionCodeEnum.UN_KNOWN_EXCEPTION;

	public PaymentUnTxException(String msg, ExceptionCodeEnum exceptionCodeEnum) {
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
