/**
 * 
 */
package com.idealunited.util.security.impl;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.idealunited.util.security.IKeyFactory;

/**
 * @author Administrator
 *
 */
public class KeyFactoryImpl implements IKeyFactory {
	
	public KeyPair genPairKey(String algname, int keylength)throws Exception {
		// TODO Auto-generated method stub
		KeyPairGenerator kp = KeyPairGenerator.getInstance(algname);
		SecureRandom secrand = new SecureRandom();
		secrand.setSeed("seashell".getBytes()); 
		kp.initialize(keylength);
		KeyPair keys= kp.genKeyPair();
		return keys;
	}

	public SecretKey genSecretKey(String algname_key, int keylength)throws Exception {
		// TODO Auto-generated method stub
        KeyGenerator kg = KeyGenerator.getInstance(algname_key);
        kg.init(keylength);
        SecretKey key = kg.generateKey();
		return key;
	}

	public PrivateKey genPrivateKey(KeyPair key) throws Exception {
		// TODO Auto-generated method stub
		return key.getPrivate();
	}

	public PublicKey genPublicKey(KeyPair key) throws Exception {
		// TODO Auto-generated method stub
		return key.getPublic();
	}
	
}
