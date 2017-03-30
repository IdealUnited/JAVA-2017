/**
 * 
 */
package com.idealunited.util.security;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;

/**
 * @author ruiyong.xiong
 *
 */
public interface IKeyFactory {
	
	
	/**
	 * 生成密钥
	 * @param algname
	 * @param keylength
	 * @return
	 * @throws Exception 
	 */
	SecretKey genSecretKey(String algname,int keylength) throws Exception;
	
	
	/**
	 * 非对称密钥
	 * @param algname
	 * @param keylength
	 * @return
	 */
	KeyPair genPairKey(String algname,int keylength) throws Exception;
	
	
	/**
	 * 公钥
	 * @param key
	 * @return
	 * @throws Exception
	 */
	PublicKey genPublicKey(KeyPair key) throws Exception;
	
	
	/**
	 * 私钥
	 * @param key
	 * @return
	 * @throws Exception
	 */
	PrivateKey genPrivateKey(KeyPair key) throws Exception;
	
}
