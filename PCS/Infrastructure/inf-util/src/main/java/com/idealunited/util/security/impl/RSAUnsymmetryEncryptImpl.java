/**
 * 
 */
package com.idealunited.util.security.impl;

import java.security.KeyPair;

/**
 * 使用RSA进行加密
 */

public class RSAUnsymmetryEncryptImpl extends AbstractUnsymmetryEncryptImpl {
	
	final static String ALGNAME="RSA";
	final static  int LENGTH =1024;

	
	public static void main( String[] args ){
		try{
			RSAUnsymmetryEncryptImpl r = new RSAUnsymmetryEncryptImpl();
			String cleartxt = "test123中国";
			byte[] cltxt = cleartxt.getBytes();
			KeyPair k = null;
			k = r.genPairKey(1024);
			cltxt=r.enCrypt(cltxt, k.getPublic());
			cltxt=r.deCrypt(cltxt, k.getPrivate());
			System.out.println(new String (cltxt));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public byte[] genPrivateKey(KeyPair k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getAlgName() {
		// TODO Auto-generated method stub
		return ALGNAME;
	}

	public byte[] deCrypt(byte[] entxt, byte[] dekey, int keyType)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
