package com.idealunited.util.security;

import java.io.ObjectInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSAAlgorithms {

	static final String algname = "SHA1withRSA";
	static final String keyalgname = "RSA";
	

	public  String getAlgname(){return this.algname;}

	public  String getKeyAlgname(){return this.keyalgname;};

	/**
	 * 签名
	 */
	public String genSignature(byte[] src, byte[] usekey) throws Exception {
		return genSignature(src, getPrivateKey(usekey));
	}

	// ////////////////////////////////
	public String genSignature(byte[] src, PrivateKey usekey, String provider)
			throws Exception {
		Signature sig = Signature.getInstance(getAlgname(), provider);
		sig.initSign(usekey);
		sig.update(src);
		return ByteArrayUtil.toHexString(sig.sign());
	}

	public boolean verifySignature(byte[] src, String dest, PublicKey usekey,
			String provider) throws Exception {
		Signature sigcheck = Signature.getInstance(getAlgname(), provider);
		sigcheck.initVerify(usekey);
		sigcheck.update(src);
		return sigcheck.verify(ByteArrayUtil.toByteArray(dest));
	}

	// ////////////////////////////////

	/**
	 * 
	 */
	public String genSignature(byte[] src, Key usekey) throws Exception {
		// TODO Auto-generated method stub
		return genSignature(src, (PrivateKey) usekey);
	}

	/**
	 * 验签
	 */
	public boolean verifySignature(byte[] src, String dest, byte[] usekey)
			throws Exception {
		return verifySignature(src, dest, getPublicKey(usekey));
	}

	/**
	 * 签名
	 */
	public String genSignature(byte[] src, ObjectInputStream usekey)
			throws Exception {

		PrivateKey mykey = (PrivateKey) usekey.readObject();
		usekey.close();
		Signature sig = Signature.getInstance(getAlgname());
		sig.initSign(mykey);
		sig.update(src);
		return ByteArrayUtil.toHexString(sig.sign());
	}

	/**
	 * 具有私钥的签名算法
	 * 
	 * @param src
	 * @param usekey
	 * @param algname
	 * @return
	 * @throws Exception
	 */
	public String genSignature(byte[] src, PrivateKey usekey) throws Exception {

		Signature sig = Signature.getInstance(getAlgname());
		sig.initSign(usekey);
		sig.update(src);
		return ByteArrayUtil.toHexString(sig.sign());
	}

	/**
	 * 具有公钥的验签算法
	 * 
	 * @param src
	 * @param dest
	 * @param usekey
	 * @param algname
	 * @return
	 * @throws Exception
	 */
	public boolean verifySignature(byte[] src, String dest,
			ObjectInputStream usekey) throws Exception {
		PublicKey mypublickey = (PublicKey) usekey.readObject();
		usekey.close();
		Signature sigcheck = Signature.getInstance(getAlgname());
		sigcheck.initVerify(mypublickey);
		sigcheck.update(src);
		return sigcheck.verify(ByteArrayUtil.toByteArray(dest));
	}

	/**
	 * 验签
	 */
	public boolean verifySignature(byte[] src, String dest, PublicKey usekey)
			throws Exception {

		Signature sigcheck = Signature.getInstance(getAlgname());
		sigcheck.initVerify(usekey);
		sigcheck.update(src);
		return sigcheck.verify(ByteArrayUtil.toByteArray(dest));
	}
	

	/**
	 * 由传入的参数生成
	 * 
	 * @return
	 */
	PublicKey getPublicKey(byte[] key) throws Exception {
		java.security.KeyFactory kFactory = java.security.KeyFactory
				.getInstance(getKeyAlgname());
		X509EncodedKeySpec myPubKeySpec = new X509EncodedKeySpec(key);
		// PKCS8EncodedKeySpec myPubKeySpec = new PKCS8EncodedKeySpec(key);

		PublicKey mPubKey = kFactory.generatePublic(myPubKeySpec);
		return mPubKey;
	}

	/**
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	PrivateKey getPrivateKey(byte[] key) throws Exception {
		java.security.KeyFactory kFactory = java.security.KeyFactory
				.getInstance(getKeyAlgname());
		PKCS8EncodedKeySpec myPubKeySpec = new PKCS8EncodedKeySpec(key);
		PrivateKey mPrivateKey = kFactory.generatePrivate(myPubKeySpec);
		return mPrivateKey;
	}

	/**
	 * 生成密钥对
	 * 
	 * @param algname
	 * @return
	 * @throws Exception
	 */
	KeyPair genPairKey(int length) throws Exception {
		KeyPairGenerator kp = KeyPairGenerator.getInstance(getKeyAlgname());
		SecureRandom secrand = new SecureRandom();
		secrand.setSeed("pay".getBytes());
		kp.initialize(length, secrand);
		KeyPair keys = kp.genKeyPair();
		return keys;
	}
	
	
	/**
     * 用私钥对字符串进行加密
     * 
     * 说明：
     * @param srcData
     * @param privateKeyArray
     * @return
     * @throws Exception
     * 创建时间：2010-12-1 下午06:29:51
     */
    public byte[] getEncryptArray(byte[]  srcData,byte[] privateKeyArray) throws Exception{
        // 得到私钥
        PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(privateKeyArray);
        KeyFactory kf=KeyFactory.getInstance(getKeyAlgname());
        PrivateKey key=kf.generatePrivate(keySpec);
        
        // 加密数据
        Cipher cp=Cipher.getInstance(getKeyAlgname());
        cp.init(Cipher.ENCRYPT_MODE, key);
        return cp.doFinal(srcData);
    }
    
    /**
     * 使用公钥进行解密
     * 
     * 说明：
     * @param encryptedData
     * @return
     * @throws Exception
     * 创建时间：2010-12-1 下午06:35:28
     */
    public byte[] getDecryptArray(byte[] encryptedData,byte[] publicKeyArray) throws Exception{
        // 得到公钥
    	X509EncodedKeySpec keySpec=new X509EncodedKeySpec(publicKeyArray);
        KeyFactory kf=KeyFactory.getInstance(getKeyAlgname());
        PublicKey key=kf.generatePublic(keySpec);
        // 解密数据
        Cipher cp=Cipher.getInstance(getKeyAlgname());
        cp.init(Cipher.DECRYPT_MODE, key);
        return cp.doFinal(encryptedData);
    }

}
