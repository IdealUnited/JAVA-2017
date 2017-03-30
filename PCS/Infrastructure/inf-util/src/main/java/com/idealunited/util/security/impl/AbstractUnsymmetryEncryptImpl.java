package com.idealunited.util.security.impl;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import com.idealunited.util.security.IUnsymmetryEncrypt;

public abstract class AbstractUnsymmetryEncryptImpl implements
		IUnsymmetryEncrypt {

	public   byte[] genPublicKey(KeyPair k) throws Exception{
		return k.getPublic().getEncoded();
	};
	public  byte[] genPrivateKey(KeyPair k) throws Exception{
		return k.getPrivate().getEncoded();
	};
	protected abstract String getAlgName();
	
	/**
	 * 加密
	 * @param entxt
	 * @param dekey
	 * @param keytype
	 * @param algname
	 * @return
	 * @throws Exception
	 */
	
	public byte[] enCrypt(byte[] cleartxt,byte[] enkey,int keyType)throws Exception{
		Key mkey = null;
		if(keyType==MPUBLICKEY){
			mkey = getPublicKey(enkey);
		}
		else if (keyType == MPRIVATEKEY)
		{
			mkey = getPrivateKey(enkey);
		}
		return enCrypt(cleartxt,mkey);
	}
	
	/**
	 * 加密
	 */
	public byte[] enCrypt(byte[] cleartxt,Key enkey)throws Exception{
		byte[] entxt=null;
		Key key = null;

		try{
			javax.crypto.Cipher cip = javax.crypto.Cipher.getInstance(getAlgName());
			cip.init(Cipher.ENCRYPT_MODE, enkey);
			entxt=cip.doFinal(cleartxt);
			return entxt;
		}catch(Exception e){
			throw e;
		}
	}
	////////////////////////////////////////////
	public byte[] enCrypt(byte[] cleartxt,Key enkey, String provider) throws Exception{
		byte[] entxt=null;
		Key key = null;

		try{
			javax.crypto.Cipher cip = javax.crypto.Cipher.getInstance(getAlgName(), provider);
			cip.init(Cipher.ENCRYPT_MODE, enkey);
			entxt=cip.doFinal(cleartxt);
			return entxt;
		}catch(Exception e){
			throw e;
		}		
	}
	
	public byte[] deCrypt(byte[] entxt,Key dekey, String provider)throws Exception{
		byte[] cleartxt=null;
		try{
			javax.crypto.Cipher cip = javax.crypto.Cipher.getInstance(getAlgName(), provider);
			cip.init(Cipher.DECRYPT_MODE, dekey);
			cleartxt=cip.doFinal(entxt);
			return cleartxt;
		}catch(Exception e){
			throw e;
		}
	}
	
	////////////////////////////////////////////
	
	
	/**
	 * 解密
	 * @param entxt
	 * @param dekey
	 * @param keytype
	 * @param algname
	 * @return
	 * @throws Exception
	 */
	public byte[] deCrypt(byte[] entxt,Key dekey)throws Exception{
		byte[] cleartxt=null;
		try{
			javax.crypto.Cipher cip = javax.crypto.Cipher.getInstance(getAlgName());
			cip.init(Cipher.DECRYPT_MODE, dekey);
			cleartxt=cip.doFinal(entxt);
			return cleartxt;
		}catch(Exception e){
			throw e;
		}
	}
	
	
	/**
	 * 解密
	 */
	public byte[] deCrypt(byte[] entxt, byte[] dekey, int keyType)
	throws Exception {
		Key mKey = null;
		byte[] cltxt = null;
		javax.crypto.Cipher cip = javax.crypto.Cipher.getInstance(getAlgName());
		
		if(keyType==MPUBLICKEY){
			mKey = getPublicKey(dekey);
		}
		else if (keyType == MPRIVATEKEY)
		{
			mKey = getPrivateKey(dekey);
		}		
		
		cip.init(Cipher.DECRYPT_MODE, mKey);
		cltxt=cip.doFinal(entxt);
		
		return cltxt;
		
		// TODO Auto-generated method stub
	}
	
	/**
	 * 生成密钥对
	 * @param algname
	 * @return
	 * @throws Exception
	 */
	KeyPair genPairKey(int length)throws Exception {
		KeyPairGenerator kp = KeyPairGenerator.getInstance(getAlgName());
		SecureRandom secrand = new SecureRandom();
		secrand.setSeed("seashell".getBytes()); 
		kp.initialize(length);
		KeyPair keys= kp.genKeyPair();
		return keys;
	}
	
	/**
	 * 组成公钥
	 * @return
	 */
	Key getPublicKey(byte[] key)throws Exception{ 
		java.security.KeyFactory kFactory = 
			java.security.KeyFactory.getInstance(getAlgName()) ;
		X509EncodedKeySpec myPubKeySpec = new X509EncodedKeySpec(key);
		 PublicKey mPubKey = kFactory.generatePublic(myPubKeySpec);
		return mPubKey;
		
	}
	
	
	/**
	 * 组成私钥
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Key getPrivateKey(byte[] key)throws Exception{ 
		java.security.KeyFactory kFactory = 
			java.security.KeyFactory.getInstance(getAlgName()) ;
		PKCS8EncodedKeySpec myPubKeySpec = new PKCS8EncodedKeySpec(key);
		 PrivateKey mPrivateKey = kFactory.generatePrivate (myPubKeySpec);
		return mPrivateKey;
	}
	
}
