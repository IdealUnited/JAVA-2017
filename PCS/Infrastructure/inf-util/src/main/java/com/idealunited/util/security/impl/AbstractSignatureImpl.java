package com.idealunited.util.security.impl;

import java.io.ByteArrayOutputStream;
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

import com.idealunited.util.security.ISignature;
import com.idealunited.util.security.ToolsUtil;

public abstract class AbstractSignatureImpl implements ISignature {
	
	/** *//** 
     * RSA最大解密密文大小 
     */  
    private static final int MAX_DECRYPT_BLOCK = 128;  

	public abstract String getAlgname();

	public abstract String getKeyAlgname();

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
		return ToolsUtil.toHexString(sig.sign());
	}

	public boolean verifySignature(byte[] src, String dest, PublicKey usekey,
			String provider) throws Exception {
		Signature sigcheck = Signature.getInstance(getAlgname(), provider);
		sigcheck.initVerify(usekey);
		sigcheck.update(src);
		return sigcheck.verify(ToolsUtil.toByteArray(dest));
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
		return ToolsUtil.toHexString(sig.sign());
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
		return ToolsUtil.toHexString(sig.sign());
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
	public byte[] genSignatureReturnByte(byte[] src, PrivateKey usekey)
			throws Exception {

		Signature sig = Signature.getInstance(getAlgname());
		sig.initSign(usekey);
		sig.update(src);
		return sig.sign();
	}
	
	/** *//** 
     * <P> 
     * 私钥解密 
     * </p> 
     *  
     * @param encryptedData 已加密数据 
     * @param privateKey 私钥(BASE64编码) 
     * @return 
     * @throws Exception 
     */  
    public byte[] decryptByPrivateKey(byte[] encryptedData, PrivateKey usekey)  
            throws Exception {  

        Cipher cipher = Cipher.getInstance(usekey.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, usekey);  
        int inputLen = encryptedData.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // 对数据分段解密  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {  
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_DECRYPT_BLOCK;  
        }  
        byte[] decryptedData = out.toByteArray();  
        out.close();  
        return decryptedData;  
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
		return sigcheck.verify(ToolsUtil.toByteArray(dest));
	}

	/**
	 * 验签
	 */
	public boolean verifySignature(byte[] src, String dest, PublicKey usekey)
			throws Exception {

		Signature sigcheck = Signature.getInstance(getAlgname());
		sigcheck.initVerify(usekey);
		sigcheck.update(src);
		return sigcheck.verify(ToolsUtil.toByteArray(dest));
	}

	/**
	 * 验签
	 */
	public boolean verifySignature(byte[] src, byte[] dest, PublicKey usekey)
			throws Exception {

		Signature sigcheck = Signature.getInstance(getAlgname());
		sigcheck.initVerify(usekey);
		sigcheck.update(src);
		return sigcheck.verify(dest);
	}

	@Override
	public boolean verifySignature(byte[] src, byte[] dest, byte[] usekey)
			throws Exception {
		Signature sigcheck = Signature.getInstance(getAlgname());

		PublicKey publicKey = getPublicKey(usekey);
		sigcheck.initVerify(publicKey);
		sigcheck.update(src);
		return sigcheck.verify(dest);
	}

	/**
	 * 由传入的参数生成
	 * 
	 * @return
	 */
	PublicKey getPublicKey(byte[] key) throws Exception {

		// byte[] keyBytes = Base64Utils.decode(publicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicK = keyFactory.generatePublic(keySpec);

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

}
