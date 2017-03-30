/**
 * 
 */
package com.idealunited.inf.service;

/**
 * @author new
 * 
 */
public interface HessianInvokeService {

	/**
	 * Hessian通讯服务接口
	 * 
	 * @param serCode
	 *            服务代码
	 * @param sysTraceNo
	 *            系统跟踪号
	 * @param originNo
	 *            源系统编号
	 * @param targetNo
	 *            目的系统编号
	 * @param versionNo
	 *            接口版本号 格式：1.0.0
	 * @param dataLength
	 *            消息正文长度
	 * @param msgCompress
	 *            消息正文是否压缩
	 * @param dataMsg
	 *            消息正文
	 * @return
	 */
	String invoke(String serCode, String sysTraceNo, String originNo,
			String targetNo, String versionNo, int dataLength, int msgCompress,
			String dataMsg);

}
