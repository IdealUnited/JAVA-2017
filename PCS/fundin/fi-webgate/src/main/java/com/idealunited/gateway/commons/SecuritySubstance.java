package com.idealunited.gateway.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.util.Assert;

import com.idealunited.util.CharsetTypeEnum;
import com.idealunited.util.security.ByteArrayUtil;
import com.idealunited.util.security.HashAlgorithms;
import com.idealunited.util.security.MD5BaseAlgorithms;
import com.idealunited.util.security.RSAAlgorithms;


/**
 * @Description 安全服务类
 * @project gateway-pay
 * @file SecuritySubstance.java
 * @note <br>
 * @develop JDK1.6 + Eclipse 3.5
 * @version 1.0 Copyright © 2004-2013 ttf.com . All rights reserved. 版权所有
 *          	Author Changes 2011-4-13 Sean.Chen Create
 */

public class SecuritySubstance {
	
	/**
	 *  将gateway-security.propertes 文件所在路径，设置在 classpath中
	 */
	static String initParam = "gateway-security.propertes"; 
	
	static Map<String,String> paramsMap = new HashMap<String,String>();
	
	static final String gatewayKeyPath="gateway.keyPath";
	static final String gatewayStorepass="gateway.storepass";
	static final String gatewayAlias="gateway.alias";
	static final String gatewayPwd="gateway.pwd";
	
	static boolean flag = true;
	
	static String pkeyHeader = "&pkey=";
	
	static String securityPropertiesPath = "fi-security.properties";
	
	static String rootPath="";
	
	static{
		
		Properties p = new Properties();
		
		try {
			p.load(SecuritySubstance.class.getClassLoader().getResourceAsStream(securityPropertiesPath));
			rootPath=(String)p.get("rootPath");
			initParam = rootPath + initParam;
			
		} catch (IOException e) {
			System.err.println("读取fi-security.properties异常");
			e.printStackTrace();
			flag = false;
		}
		
		try {
			p.load(new FileInputStream(initParam));
			paramsMap.put(gatewayKeyPath, p.get("gateway.keyPath")+"");
			paramsMap.put(gatewayStorepass, p.get("gateway.storepass")+"");
			paramsMap.put(gatewayAlias, p.get("gateway.alias")+"");
			paramsMap.put(gatewayPwd, p.get("gateway.pwd")+"");
			
		} catch (IOException e) {
			System.err.println("读取gateway-security.propertes异常");
			e.printStackTrace();
			flag = false;
		}
	}


	/**
	 * 生成签名MD5
	 * @param src
	 * @return
	 */
	public static String genSignByMD5(String src, CharsetTypeEnum charsetType,String parnterPublicKey)
			throws Exception {
		
		if(!flag){
			System.err.println("初始化配置未成功,genSignByMD5无法执行");
			throw new Exception("初始化配置未成功,genSignByMD5无法执行");
		}
		
		if(src==null || "".equals(src.trim())){
			System.err.println("src is empty,genSignByMD5无法执行");
			throw new Exception("src is empty,genSignByMD5无法执行");
		}
		
		if(parnterPublicKey==null || "".equals(parnterPublicKey.trim())){
			System.err.println("parnterPublicKey is empty,genSignByMD5无法执行");
			throw new Exception("parnterPublicKey is empty,genSignByMD5无法执行");
		}
		
		src += pkeyHeader+parnterPublicKey;
		
		String mac = null;
		
		
		mac = MD5BaseAlgorithms.getMD5Str(src);
		
		return mac;
	}
	
	
	public static void main(String[] args) {
    }

	/**
	 * 生成签名RSA
	 * @param src
	 * @return
	 */
	public static String genSignByRSA(String src, CharsetTypeEnum charsetType)
			throws Exception {
		
		if(!flag){
			System.err.println("初始化配置未成功,genSignByRSA无法执行");
			throw new Exception("初始化配置未成功,genSignByRSA无法执行");
		}	
		
		if(src==null || "".equals(src.trim())){
			System.err.println("src is empty,genSignByRSA无法执行");
			throw new Exception("src is empty,genSignByRSA无法执行");
		}
		
		String mac = null;
			
		int hashCode = HashAlgorithms.PJWHash(src);
		mac = hashCode+"";
		
		String signMsg = "";
		
		PrivateKey prikey = null;
		PublicKey pubKey = null;

		InputStream in = new FileInputStream(rootPath + paramsMap.get(gatewayKeyPath));
		
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(in, paramsMap.get(gatewayStorepass).toCharArray());
			
		java.security.cert.Certificate c = ks.getCertificate(paramsMap.get(gatewayAlias));
		pubKey = c.getPublicKey();
		
		// 取出 公钥 串
		// System.err.println(ByteArrayUtil.toHexString(pubKey.getEncoded()));
	
		// 取出网关私钥
		prikey = (PrivateKey) ks.getKey(paramsMap.get(gatewayAlias), paramsMap.get(gatewayPwd).toCharArray());
		
		RSAAlgorithms rsa = new RSAAlgorithms();
		
		signMsg = rsa.genSignature(mac.getBytes(charsetType.getDescription()), prikey);

		mac = signMsg;
			
		return mac;		
	}

	/**
	 * 校验签名MD5
	 * @param src 原数据
	 * @param dit 加签后数据
	 * @return result
	 */
	public static boolean verifySignatureByMD5(String src, String dit,
			CharsetTypeEnum charsetType,String parnterPublicKey) throws Exception {
		
		if(!flag){
			System.err.println("初始化配置未成功,verifySignatureByMD5无法执行");
			throw new Exception("初始化配置未成功,verifySignatureByMD5无法执行");
		}	
		
		if(src==null || "".equals(src.trim())){
			System.err.println("初始化配置未成功,verifySignatureByMD5无法执行");
			throw new Exception("src is empty ,verifySignatureByMD5无法执行");
		}
		
		if(dit==null || "".equals(dit.trim())){
			System.err.println("初始化配置未成功,verifySignatureByMD5无法执行");
			throw new Exception("arg is empty ,verifySignatureByMD5无法执行");
		}
		
		if(parnterPublicKey==null || "".equals(parnterPublicKey.trim())){
			System.err.println("初始化配置未成功,verifySignatureByMD5无法执行");
			throw new Exception("parnterPublicKey is empty ,verifySignatureByMD5无法执行");
		}
		
		src += pkeyHeader+parnterPublicKey;
		
		String mac = null;
		
		try{
			mac = MD5BaseAlgorithms.getMD5Str(src);
		}catch(Exception e){
			System.err.println("MD5 验签出现异常");
			e.printStackTrace();
			return false;
		}
		
		if(dit.equals(mac)){
			return true;
		}
		
		return false;
	}

	/**
	 * 校验签名RSA
	 * @param src 原数据
	 * @param dit 加签后数据
	 * @param publicKey Base64后的公钥
	 * @return result
	 */
	public static boolean verifySignatureByRSA(String src, String dit,
			CharsetTypeEnum charsetType, String publicKey) throws Exception {
		
		if(!flag){
			System.err.println("初始化配置未成功,verifySignatureByRSA无法执行");
			throw new Exception("初始化配置未成功,verifySignatureByRSA无法执行");
		}
		if(src==null || "".equals(src.trim())){
			System.err.println("初始化配置未成功,verifySignatureByMD5无法执行");
			throw new Exception("src is empty ,verifySignatureByMD5无法执行");
		}
		if(dit==null || "".equals(dit.trim())){
			System.err.println("初始化配置未成功,verifySignatureByMD5无法执行");
			throw new Exception("dit is empty ,verifySignatureByMD5无法执行");
		}
		
		boolean result = false;
		
		int hashCode = HashAlgorithms.PJWHash(src);
		String hashSrc = hashCode+"";
		
		RSAAlgorithms sign = new RSAAlgorithms();
		
		try {
			result = 
				sign.verifySignature(hashSrc.getBytes(), dit, ByteArrayUtil.toByteArray(publicKey));
		
		} catch (Exception e) {
			System.err.println("RSA 验签出现异常");
			e.printStackTrace();
			result = false;
		}
			
		return result;
	}
	
	public static String decryptData(String encryptedData,String publicKey) throws Exception{
		if(!flag){
			System.err.println("初始化配置未成功,verifySignatureByRSA无法执行");
			throw new Exception("初始化配置未成功,verifySignatureByRSA无法执行");
		}
		Assert.notNull(encryptedData,"encryptedData is required");
		Assert.notNull(publicKey,"publicKey is required");
		
		String srcData = null;
		RSAAlgorithms sign = new RSAAlgorithms();
		try {
			srcData = new String(sign.getDecryptArray(ByteArrayUtil.toByteArray(encryptedData), ByteArrayUtil.toByteArray(publicKey)));
		
		} catch (Exception e) {
			System.err.println("RSA 解密出现异常");
			e.printStackTrace();
		}
		
		return srcData;
		
		
	}

}
