/**
 * 
 */
package com.idealunited.inf.service;

/**
 * @author ruiyong.xiong
 * 
 * 
 */
public interface IMessageDigest {
	
	/**
	 * 生成摘要
	 * @param src
	 * @return
	 */
	public String genMessageDigest(byte[] src) throws Exception;
	/**
	 * 摘要的较验
	 * @param src
	 * @param dest
	 * @return
	 */
	public boolean validateMessageDigest(byte[] src,String dest) throws Exception;
	
}
