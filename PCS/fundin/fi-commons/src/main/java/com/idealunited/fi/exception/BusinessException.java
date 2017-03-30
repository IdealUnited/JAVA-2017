package com.idealunited.fi.exception;

import com.idealunited.inf.exception.AppException;
/*********************************************************************** 
 * 
 *   BusinessException.java 
 *   支付异常类
 * 
 *   版权所有，  受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。 
 *   @copyright       Copyright:   2010   
 *   @creator           <br/> 
 *   @create-time   2010-7-20   下午12:38:02 
 *   @revision         $Id:     * 
 ***********************************************************************/ 

public class BusinessException extends AppException {

	private static final long serialVersionUID = 1L;
	
	private ExceptionCodeEnum code = ExceptionCodeEnum.UN_KNOWN_EXCEPTION;

	public BusinessException(String msg,ExceptionCodeEnum error) {
		super(msg,error);
		this.code = error;
	}

	public BusinessException(String msg) {
		super(msg);
	}
	
	@Override
    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        return (message != null) ? (s + ": " + message) : (s + ": " + code.getCode() + "["
                                                           + code.getDescription() + "]。");
    }

	public ExceptionCodeEnum getCode() {
		return code;
	}

	public void setCode(ExceptionCodeEnum code) {
		this.code = code;
	}
}
