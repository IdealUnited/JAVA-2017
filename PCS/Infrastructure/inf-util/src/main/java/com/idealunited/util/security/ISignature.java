package com.idealunited.util.security;

import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface ISignature {

	/**
	 * 生成签名
	 * 
	 * @param src
	 * @return
	 */

	public String genSignature(byte[] src, ObjectInputStream usekey)
			throws Exception;

	public String genSignature(byte[] src, PrivateKey usekey) throws Exception;

	// add a overrider method for set provider @modify by: dylan.shi
	public String genSignature(byte[] src, PrivateKey usekey, String provider)
			throws Exception;

	public String genSignature(byte[] src, byte[] usekey) throws Exception;

	byte[] genSignatureReturnByte(byte[] src, PrivateKey usekey)
			throws Exception;

	/**
	 * 私钥解密
	 * 
	 * @param encryptedData
	 * @param usekey
	 * @return
	 * @throws Exception
	 */
	byte[] decryptByPrivateKey(byte[] encryptedData, PrivateKey usekey)
			throws Exception;

	/**
	 * 签名的较验
	 * 
	 * @param src
	 * @param dest
	 * @return
	 */

	public boolean verifySignature(byte[] src, String dest,
			ObjectInputStream usekey) throws Exception;

	public boolean verifySignature(byte[] src, String dest, PublicKey usekey)
			throws Exception;

	public boolean verifySignature(byte[] src, byte[] dest, PublicKey usekey)
			throws Exception;

	public boolean verifySignature(byte[] src, byte[] dest, byte[] usekey)
			throws Exception;

	// add a overrider method for set provider @modify by: dylan.shi
	public boolean verifySignature(byte[] src, String dest, PublicKey usekey,
			String provider) throws Exception;

	public boolean verifySignature(byte[] src, String dest, byte[] usekey)
			throws Exception;
}
