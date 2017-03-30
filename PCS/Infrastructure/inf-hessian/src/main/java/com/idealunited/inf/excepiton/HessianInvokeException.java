/**
 * 
 */
package com.idealunited.inf.excepiton;

/**
 * @author new
 * 
 */
public class HessianInvokeException extends Exception {


	private static final long serialVersionUID = 1L;
	/**
	 * 异常代码
	 */
	private String errorCode;
	/**
	 * 异常描述
	 */
	private String errorDesc;
	
	
	

	public HessianInvokeException(String code, String desc) {
		super(code+":"+desc);
		this.errorCode = code;
		this.errorDesc = desc;
		
	}
	
	public  HessianInvokeException(String code, String desc,Throwable e){
		super(code+":"+desc,e);
		this.errorCode = code;
		this.errorDesc = desc;
	}
	
	public String getErrorCode() {
		return errorCode;
	};
	
	public String getErrorDesc() {
		return errorDesc;
	}
	

}
