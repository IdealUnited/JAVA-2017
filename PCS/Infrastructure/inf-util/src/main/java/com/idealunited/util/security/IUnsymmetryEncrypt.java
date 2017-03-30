package com.idealunited.util.security;

import java.security.Key;
 
/**
 * 
 * @author ruiyong.xiong
 *
 *非对称加密
 *
 */
public interface IUnsymmetryEncrypt {
	final static int MPUBLICKEY=0;
	final static int MPRIVATEKEY=1;
	/**
	 * 
	 * @param cleartxt
	 * @param enkey
	 * @return
	 */
	public byte[] enCrypt(byte[] cleartxt,Key enkey) throws Exception;
	
	// add a overrider method for set provider @modify by: dylan.shi
	public byte[] enCrypt(byte[] cleartxt,Key enkey, String provider) throws Exception;
	public byte[] enCrypt(byte[] cleartxt,byte[] enkey,int keyType) throws Exception;
	/**
	 * 
	 * @param entxt
	 * @param dekey
	 * @return
	 */
	public byte[] deCrypt(byte[] entxt,Key dekey )throws Exception;
	// add a overrider method for set provider @modify by: dylan.shi
	public byte[] deCrypt(byte[] entxt,Key dekey, String provider)throws Exception;
	public byte[] deCrypt(byte[] entxt,byte[] dekey ,int keyType)throws Exception;
	
	
	
}
