/**
 * 
 */
package com.idealunited.fi.commons;

/**
 * 格式化异常信息
 * @author huhb
 *
 */
public class ExceptionMsgFormat {

	public static String errorMessageFormat(Exception e){
		
		Throwable t = (Throwable)e;
		
		StringBuffer sbf = new StringBuffer();
		sbf.append(e.toString()+"\n");
	    StackTraceElement[] trace = e.getStackTrace();
	    for (int i=0; i < trace.length; i++)
	    	sbf.append("\tat " + trace[i] + "\n");
	   return sbf.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
