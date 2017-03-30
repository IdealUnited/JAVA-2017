/**
 * 
 */
package com.idealunited.util.security.impl;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.idealunited.util.security.ToolsUtil;


public class DESEncrypt extends AbstractEncryptImpl {
	
	private static final String ALGORITHM_OPTIONS = "DES/ECB/PKCS5Padding";

    private static final String ALGORITHM_KEY = "DES";

    private static final String ALGORITHM_ENCODING = "UTF-8";
	
    private static final int keyleng = 56;
    

	/**
	 * EDS
	 */
	public byte[] genKey() throws Exception {
		// TODO Auto-generated method stub
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_KEY);
        kg.init(keyleng);
        SecretKey key = kg.generateKey();
        // get the encoded value for the string and convert to hex
        byte[] encodedKey = key.getEncoded();
		return encodedKey;
	}

	@Override
	
	
	/**
	 * 密文解密(同一个密钥)
	 */
	public byte[] decrypt(byte[] enctext, byte[] key) throws  Exception {
		// TODO Auto-generated method stub
		// SecretKeySpec sks = new SecretKeySpec(keyPart, ALGORITHM_KEY);
		Cipher cipher = Cipher.getInstance(ALGORITHM_OPTIONS);
		SecretKeySpec skey = new SecretKeySpec(key,ALGORITHM_KEY);
		return deCrypt(enctext,skey);
	}
	
	/**
	 * 明文加密
	 */
	public byte[] encrypt(byte[] cleartext, byte[] key)throws Exception {
		// TODO Auto-generated method stub
			Cipher cipher = Cipher.getInstance(ALGORITHM_OPTIONS);
			SecretKeySpec skey = new SecretKeySpec(key,ALGORITHM_KEY);
			return EnCrypt(cleartext,skey);
		 
	}
	
	public static void main(String[]args){
		byte[] key=null;
		byte[] txt=null;
		
		DESEncrypt t = new DESEncrypt();
		
		t.test4(t);
		
	}
	
	
	void test1(DESEncrypt t){
		try{
			byte[] e=t.genKey();
			System.out.println( ToolsUtil.toHexString(e));
			System.out.println(ToolsUtil.toHexString(ToolsUtil.toByteArray(ToolsUtil.toHexString(e))) );
		}catch(Exception c)
		{
			c.printStackTrace();
		}
		
	}
	
	void test2(DESEncrypt t){
		String ctext="加密123abc";
		try{
			byte[] key =t.genKey();
			byte[] e=t.encrypt(ctext.getBytes(ALGORITHM_ENCODING),key);
			//System.out.println("ctxt="+ToolsUtil.toHexString( ctext.getBytes(ALGORITHM_ENCODING)));
			System.out.println( "key="+ToolsUtil.toHexString(key));
			System.out.println( "txt="+ToolsUtil.toHexString(e));
			e=t.decrypt(e,key);
			System.out.println( new String(e,ALGORITHM_ENCODING));
			//System.out.println(ToolsUtil.toHexString(ToolsUtil.toByteArray(ToolsUtil.toHexString(e))) );
		}catch(Exception c)
		{
			c.printStackTrace();
		}
	}
	
	
	void test3(DESEncrypt t){
		String ctext="c637815ee1c8bbd9298b3829dde3195764814f70f07d7edc";
		byte[] key=ToolsUtil.toByteArray("5b9b3167e08f68cd");
		byte[] e=null;
		try{
			e=t.decrypt(ToolsUtil.toByteArray(ctext),key);
			System.out.println( new String(e,ALGORITHM_ENCODING));
			//System.out.println(ToolsUtil.toHexString(ToolsUtil.toByteArray(ToolsUtil.toHexString(e))) );
			
		}catch(Exception c)
		{
			c.printStackTrace();
		}
	}
	
	
	void test4(DESEncrypt t){
		
		try{
		String ctext="abc123asdfergrthdfgjhrt1234346ertew457658rtyhretyw35tq3";
		byte[] cleartext= ctext.getBytes(ALGORITHM_ENCODING);
		byte[] mtext = null; 
		
		byte[] sk=   genKey();
		System.out.println("sk="+ToolsUtil.toHexString(sk));
	//	String ctext="c637815ee1c8bbd9298b3829dde3195764814f70f07d7edc";
	//	byte[] key = ToolsUtil.toByteArray("5b9b3167e08f68cd");
	//	byte[] e = null;
		mtext = t.encrypt(cleartext, sk);
		System.out.println("mtext="+ToolsUtil.toHexString(mtext));
		mtext = t.decrypt( mtext,sk );
		
		System.out.println("o="+ new String(mtext,ALGORITHM_ENCODING));
			//System.out.println(ToolsUtil.toHexString(ToolsUtil.toByteArray(ToolsUtil.toHexString(e))) );
		}catch(Exception c)
		{
			c.printStackTrace();
		}
		
		
	}

	@Override
	String getAlgname() {
		// TODO Auto-generated method stub
		return ALGORITHM_OPTIONS;
	}
	
}
