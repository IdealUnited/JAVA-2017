/**
 * 
 */
package com.idealunited.util.security.impl;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import com.idealunited.util.security.IEncrypt;

public abstract class AbstractEncryptImpl implements IEncrypt {

	public abstract byte[] genKey() throws Exception ;
	abstract  String getAlgname();
	
	public abstract byte[] decrypt(byte[] enctext, byte[] key) throws Exception;

	/**
	 * 明文加密码生成密文
	 * @param cleartext
	 * @param key
	 * @param algname
	 * @return
	 */
	protected  byte[] EnCrypt(byte[] cleartext, SecretKey key) throws Exception
	  {
		String keyValue = null;
            // generate a secret key
            // run the encryption algorithm
            Cipher cipher = Cipher.getInstance(getAlgname());
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal();
	  }
	  /**
	   * 密文解密
	   */
	  protected byte[] deCrypt(byte[] enctext, SecretKey sks) throws Exception
	  {
			// TODO Auto-generated method stub
		  	Cipher cipher = Cipher.getInstance(getAlgname());
		  	cipher.init(Cipher.DECRYPT_MODE, sks);
		  	return cipher.doFinal(enctext);
	  }
	
}