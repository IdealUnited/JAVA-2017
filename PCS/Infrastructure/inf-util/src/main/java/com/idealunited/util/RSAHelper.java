package com.idealunited.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSAHelper {

	public static String public_key_String = "30819f300d06092a864886f70d010101050003818d0030818902818100cd889fec17dc0e8ff23cf66f1054ae7287616302ecbca143e96711b41371fb3a653aceaf2ce393f4e1ed624162fc799cc1837ff1e01e652611e637e61d50a9532deb92f28f46c9e260a2ffebd3c7167d6a42057144faeb98719b8b3b754f2b7b1c46665a0ed7a1601d60adb672e5548ecf2ee30421ffcf5943ab7bc85fe9b5710203010001";
	public static String private_key_String = "30820276020100300d06092a864886f70d0101010500048202603082025c02010002818100cd889fec17dc0e8ff23cf66f1054ae7287616302ecbca143e96711b41371fb3a653aceaf2ce393f4e1ed624162fc799cc1837ff1e01e652611e637e61d50a9532deb92f28f46c9e260a2ffebd3c7167d6a42057144faeb98719b8b3b754f2b7b1c46665a0ed7a1601d60adb672e5548ecf2ee30421ffcf5943ab7bc85fe9b5710203010001028180023a06b18218aa37b9021c115bf5eee5e2bd955b04c18e65b3f39fe7798674984f5c71bcc819b712a217f6468b11fd274b99671b71b8229465013f7dc8b784fe12bab573c241bd5321d042e9c7efd1d23d7fe2cb72fb52ecc778f15886692ca8121e7e24d3f3e723f3ed4bec0b9b6a55d12c559d0f106025825c3acd0f140cd9024100e9a445d7c5591cc3c86cb651ac22167d69d9175ff00760c948b7ee2d4a2e9fc24cc8703b9a3dd371541a9fddad2a57aefcd64f216b8dd90363bd400ef09d3937024100e133c3ec053ddf48bb0b7a9f9e0da68f07935ecda064125807d14a51522d2426dcfd818a96f38d15579b339527ff8400731a46f19cb043899cf5bf39d6bdba970241009f5d3b7e87cfd6bcfc529ab16eb5f99d25bbbc23e637421f49889bdf2c804cdc5d3f42be84e0b2fed41d2cfa29897e318fa82665675563b6da2b562c5a97035502404f2cc4e7f8dbcce1a4291ffe7831f269fcceda18cf17ffbf055896994a9be646e79114f725a510e7fcf9eac3b8e1438668f14719f0eda381013700f913bf769702403bfc9bd265a22e210f7be492d9174a9bfaa0f60576dbb2ccb21ecd007ef2903b64c6be2bca4ba61e08076857e397c8b6c87b95e85b27d872b5c695e2c6418678";

	/**
	 * 得到公钥
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = (new BASE64Decoder()).decodeBuffer(key);

		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 得到私钥
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = (new BASE64Decoder()).decodeBuffer(key);

		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	/**
	 * 得到密钥字符串（经过base64编码）
	 * 
	 * @return
	 */
	public static String getKeyString(byte[] keyBytes) throws Exception {
		String s = (new BASE64Encoder()).encode(keyBytes);
		return s;
	}

	public static String getRSAString(String keyName) throws Exception {
		RSAUtil rsaUtil = RSAUtil.getInstance();
		String private_key = get16to2KeyString(private_key_String);
		PrivateKey privateKey = getPrivateKey(private_key);
		byte[] datas = decodeHex(keyName.toLowerCase());
		byte[] deBytes = rsaUtil.decode(privateKey, datas);
		String returnKeytoString = new String(deBytes);
		return returnKeytoString;
	}

	public static String get16to2KeyString(String keyString) throws Exception {
		byte[] decodebyte = decodeHex(keyString);
		String keydecodeToencode = getKeyString(decodebyte);
		return keydecodeToencode;
	}

	public static final String encodeHex(byte[] bytes)// 把二进制对象转化为16进制串（用字符串表示）
	{
		StringBuffer buf = new StringBuffer(bytes.length * 2);

		for (int i = 0; i < bytes.length; ++i) {
			if ((bytes[i] & 0xFF) < 16)
				buf.append("0");

			buf.append(Long.toString(bytes[i] & 0xFF, 16));
		}
		return buf.toString();
	}

	public static final byte[] decodeHex(String hex) {// 把字符串（该串标表示的是16进制）转化为二进制对象
		char[] chars = hex.toCharArray();
		byte[] bytes = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2) {

			byte newByte = 0;
			newByte = (byte) (newByte | hexCharToByte(chars[i]));
			newByte = (byte) (newByte << 4);
			newByte = (byte) (newByte | hexCharToByte(chars[(i + 1)]));
			bytes[byteCount] = newByte;
			++byteCount;
		}
		return bytes;
	}

	private static final byte hexCharToByte(char ch) {
		switch (ch) {
		case '0':
			return 0;
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		case 'a':
			return 10;
		case 'b':
			return 11;
		case 'c':
			return 12;
		case 'd':
			return 13;
		case 'e':
			return 14;
		case 'f':
			return 15;
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
		/*
		 * RSAUtil rsaUtil = RSAUtil.getInstance();
		 * 
		 * KeyPair keyPair = rsaUtil.generateRandomKeyPair(); // 公钥 PublicKey
		 * publicKey = (RSAPublicKey) keyPair.getPublic();
		 * 
		 * // 私钥 PrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		 * 
		 * String publicKeyString = getKeyString(publicKey);
		 * System.out.println("public:\n" + publicKeyString);
		 * 
		 * String privateKeyString = getKeyString(privateKey);
		 * System.out.println("private:\n" + privateKeyString);
		 * 
		 * //加解密类 Cipher cipher =
		 * Cipher.getInstance("RSA");//Cipher.getInstance(
		 * "RSA/ECB/PKCS1Padding");
		 * 
		 * //明文 byte[] plainText = "111111".getBytes();
		 * 
		 * //加密 // cipher.init(Cipher.ENCRYPT_MODE, publicKey); byte[] enBytes
		 * =rsaUtil.encode(publicKey, plainText);// cipher.doFinal(plainText);
		 * String s1=new String(encodeHex(enBytes));
		 * System.out.println("s1-->"+s1); byte[] s2= decodeHex(s1); String
		 * s3=new String(s2); String s4=new String(encodeHex(s2));
		 * System.out.println("s2-->"+s4); //通过密钥字符串得到密钥 publicKey =
		 * getPublicKey(publicKeyString); privateKey =
		 * getPrivateKey(privateKeyString);
		 * 
		 * //解密 // cipher.init(Cipher.DECRYPT_MODE, privateKey); byte[]
		 * q=decodeHex(s1); byte[]deBytes = rsaUtil.decode(privateKey, q);
		 * 
		 * 
		 * String s = new String(deBytes); System.out.println(s);
		 */
		RSAHelper rsa = new RSAHelper();
		rsa.getRSAString("240C6F42F1C612A94A4937156BB4C76831A9E1167EA49851A16198454A225285645ED92BCA9EA443B6AB75C9F4074382DE8A825301B2F299AFAA72E4864CDA2688D570EACD185B93C2ACB550E318779714EF8DCDDD09ED7EAE13E8B0E4FE4598F6E77AF9424CAF1A305D53147A9A393F735AF98072B445727114DD2826B43A96");
		/*
		 * // byte[] sss=decodeHex(
		 * "30819F300D06092A864886F70D010101050003818D00308189028181008100EA1844A9D8A23738CA25DBC73A57BECABF216353DE2631C944D1A0F42FED33CBB711269B946FC0615707CFE04506D6AB3F2F9A9D5EE0F362BD802B694890E718C23BBDC9FE9A284E2FEA8F43981574FC095961C4CBE1BC347989F4F7BDEA3F6630A78B8157493062C7FF9D4A4C54DFF29EB0103E56779ECDD05A588141770203010001"
		 * ); // String s = (new BASE64Encoder()).encode(sss); //
		 * System.out.println(s); RSAUtil rsaUtil = RSAUtil.getInstance(); //
		 * RSAHelper rsa=new RSAHelper(); byte[]
		 * private_key=rsa.decodeHex(rsa.private_key_String); byte[]
		 * public_key=rsa.decodeHex(rsa.public_key_String); String
		 * bsae64_private=rsa.getKeyString(private_key); //
		 * System.out.println("bsae64_private-->"+bsae64_private); String
		 * bsae64_public=rsa.getKeyString(public_key); //
		 * System.out.println("bsae64_public-->"+bsae64_public); byte[]
		 * plainText = "ttt".getBytes(); PublicKey publicKey =
		 * getPublicKey(bsae64_public); PrivateKey privateKey =
		 * getPrivateKey(bsae64_private); byte[] enBytes
		 * =rsaUtil.encode(publicKey, plainText);// cipher.doFinal(plainText);
		 * // System.out.println(enBytes.length); String s1=new
		 * String(encodeHex(enBytes)); System.out.println("s1-->"+s1); byte[]
		 * q=decodeHex(s1); byte[]deBytes = rsaUtil.decode(privateKey, q);
		 * String s = new String(deBytes); System.out.println(s);
		 */
	}
}
