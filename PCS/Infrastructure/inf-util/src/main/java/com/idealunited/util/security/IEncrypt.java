/**
 * 
 */
package com.idealunited.util.security;


/**
 * @author ruiyong.xiong
 *
 */
public interface IEncrypt {
	
	/**
	 * 
	 * @param cleartext
	 * @param key
	 * @return
	 * @throws Exception 
	 */
	public byte[] encrypt(byte[] cleartext,byte[] key) throws Exception;
	
	/**
	 * 
	 * @param enctext
	 * @param key
	 * @return
	 */
	public byte[] decrypt(byte[] enctext,byte[] key) throws Exception ;
	
	

}
