package com.idealunited.inf.excepiton;

/**
 * 
 * 
 * 
 * 
 */
public class BusinessException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2955940711387926366L;
	private String serCode;
	private String errorCode;
	private String errorMsg;

	/**
	 * @param arg0
	 * @param arg1
	 */
	public BusinessException(String txnCode, String errorCode) {
		this.serCode = txnCode;
		this.errorCode = errorCode;
	}

	public BusinessException(String txnCode, String errorCode, String errorMsg) {
		this.serCode = txnCode;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public BusinessException(String txnCode, String errorCode, Throwable cause) {
		super(cause);
		this.serCode = txnCode;
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @return the txnCode
	 */
	public String getTxnCode() {
		return serCode;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

}