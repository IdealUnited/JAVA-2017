package com.idealunited.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.util.Properties;

import org.bouncycastle.util.encoders.Hex;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class SSOSignatureUtil extends SignatureUtil {
	
	private static String pass = "";
	private static String storepass = "";
	private static String alias = "";
	
	private static String pass4sso = "";
	private static String storepass4sso = "";
	private static String alias4sso = "";
	
	private static PublicKey publicKey4Mall;	// JKS 公钥
	private static PrivateKey privateKey4Mall;	// JKS 私钥
	
	private static PublicKey publicKey4SSO;
	private static PrivateKey privateKey4SSO;
	
	private static BASE64Decoder decoder = new BASE64Decoder();
	
	private static BASE64Encoder encoder = new BASE64Encoder();
	
	// 静态初始化
    static{
    	FileInputStream fis = null;
    	InputStream in = null;
    	try{
    		fis = new FileInputStream("/opt/pay/config/ma/sso/signatureConfig.properties");
    		Properties config = new Properties();
    		config.load(fis);
    		pass = config.getProperty("signature.mall.password");
    		storepass = config.getProperty("signature.mall.storepassword");
    		alias = config.getProperty("signature.mall.alias");
    		
    		pass4sso = config.getProperty("signature.sso.password");
    		storepass4sso = config.getProperty("signature.sso.storepassword");
    		alias4sso = config.getProperty("signature.sso.alias");
    		
    		// 读取 JKS 文件
	    	in = new FileInputStream("/opt/pay/config/ma/sso/website1.jks");
		    
	    	// 构建 KeyStore
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load(in, storepass.toCharArray());
			
			// 读取私钥
			PrivateKey prikey = (PrivateKey) ks.getKey(alias, pass.toCharArray());
			
			// 生成公钥
			Certificate c = ks.getCertificate(alias);
			PublicKey pubKey = c.getPublicKey();
			
			publicKey4Mall = pubKey;
			privateKey4Mall = prikey;
			
			//---------初始化sso验证key
			
			in = new FileInputStream("/opt/pay/config/ma/sso/website4sso.jks");
		    
	    	// 构建 KeyStore
			ks = KeyStore.getInstance("JKS");
			ks.load(in, storepass4sso.toCharArray());
			
			// 读取私钥
			prikey = (PrivateKey) ks.getKey(alias4sso, pass4sso.toCharArray());
			
			// 生成公钥
			c = ks.getCertificate(alias4sso);
			pubKey = c.getPublicKey();
			
			publicKey4SSO = pubKey;
			privateKey4SSO = prikey;
			
			
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		try {
				if(fis!=null && fis.available()!=-1){
					fis.close();
				}
				if(in!=null && in.available()!=-1){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    
    
	public static PublicKey getPublicKey4Mall() {
		return publicKey4Mall;
	}

	public static PrivateKey getPrivateKey4Mall() {
		return privateKey4Mall;
	}

	public static PublicKey getPublicKey4SSO() {
		return publicKey4SSO;
	}

	public static PrivateKey getPrivateKey4SSO() {
		return privateKey4SSO;
	}

	public static String getPublicKey4MallHexString(){
		return toHexString(publicKey4Mall.getEncoded());
	}

	public static String getPrivateKey4MallHexString(){
		return toHexString(privateKey4Mall.getEncoded());
	}
	
	public static byte[] decryptBase64(String code) throws IOException{
		return decoder.decodeBuffer(code);
	}
	
	public static String encryptBase64(byte[] code) throws IOException{
		return encoder.encodeBuffer(code);
	}
	
	/**
	 * 使用RSA 私钥 加密原文
	 * @param originaltext
	 * @return sign
	 */
	public static String encryptOfRSAKey4Mall(String originaltext){
		
		String sign = null;
		
		try{
			// 生成 Signature 实例，签名算法为：MD5WithRSA
			Signature sig = Signature.getInstance("MD5WithRSA");
			// 使用私钥初始化签名对象，用于计算签名
			sig.initSign(SSOSignatureUtil.privateKey4Mall);
			// update 数据
			sig.update(originaltext.getBytes("UTF-8"));
			// 计算签名，输出签名值
			byte[] signature = sig.sign();
			sign = new String(Hex.encode(encryptBase64(signature).getBytes()));
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return sign;
	}
	
	/**
	 * 使用RSA 私钥 加密原文
	 * @param originaltext
	 * @return sign
	 */
	public static String encryptOfRSAKey4SSO(String originaltext){
		
		String sign = null;
		
		try{
			// 生成 Signature 实例，签名算法为：MD5WithRSA
			Signature sig = Signature.getInstance("MD5WithRSA");
			// 使用私钥初始化签名对象，用于计算签名
			sig.initSign(SSOSignatureUtil.privateKey4SSO);
			// update 数据
			sig.update(originaltext.getBytes("UTF-8"));
			// 计算签名，输出签名值
			byte[] signature = sig.sign();
			sign = new String(Hex.encode(encryptBase64(signature).getBytes()));
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return sign;
	}
	
	/**
	 * 对商城验签
	 * @param ciphertext	密文
	 * @param text			待验签 明文
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws SignatureException 
	 * @throws IOException 
	 */
	public static boolean doSignature4Mall(String ciphertext,String text) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException{
		byte[] plainText = text.getBytes("UTF-8");
		/** 验签 **/
		Signature sig = Signature.getInstance("MD5WithRSA");
		// 使用公钥初始化签名对象，用于验证签名
		sig.initVerify(SSOSignatureUtil.getPublicKey4Mall());
		// update数据
		sig.update(plainText);
		// 验证签名
		
		return sig.verify(decryptBase64(new String(Hex.decode(ciphertext))));
	}
	
	/**
	 * 对sso验签
	 * @param ciphertext	密文
	 * @param text			待验签 明文
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws SignatureException 
	 * @throws IOException 
	 */
	public static boolean doSignature4SSO(String ciphertext,String text) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException{
		byte[] plainText = text.getBytes("UTF-8");
		/** 验签 **/
		Signature sig = Signature.getInstance("MD5WithRSA");
		// 使用公钥初始化签名对象，用于验证签名
		sig.initVerify(SSOSignatureUtil.getPublicKey4SSO());
		// update数据
		sig.update(plainText);
		// 验证签名
		
		return sig.verify(decryptBase64(new String(Hex.decode(ciphertext))));
	}
	
	
	public static void main(String[] args) throws Exception{
		

		String originaltext = "memberName=gjk23962@sina.com&memberPwd=1810A0CF9DCF758B1F047391615A979ECEEB2137E65966982EC1079BB5ABEABF23CAA0F3D8E627320E985B208B8B514EAD1093F04D321B550142345DC61CDA21CAB46B7EC7CC0F89B9545480907A678185252C1431C127826DE2E8F7F36F6645BDAD2554D7F31F6D571628AD8C2C871737E82FC3E6A3A78CD1B0A7A64F89ED0C";
		
		String strToSign = "memberName=gjk23962@sina.com&memberPwd=A0C826B22A9C107B843C7254AB1E37B4E67B6B2A022B0194F83D18CB6B2FDF4F2083A60DF9D43FD10FDEF67FFE5C1497374D95412B4669B0F0B967A025E022705181398798CB5C4D9A7BF9C5B0CE40724E4E09B683A1607EA920A74930180E5FF54F7AD9AAA14F9F26DAB32BEC42A0DF969F04996043A74C711AF84F5109F84B";
		
//		String ciphertext = SSOSignatureUtil.encryptOfRSAKey(strToSign);
		
		String ciphertext = "50527342304951396c54632b656f655566793178506c78742b3065654650446b366b4676786e2b4b315169416b6c344a2f6238333366774672565541584a514d5630644d71514358493251750d0a5731764a376662457374495a43774951334e7859502f4e77303146642b7a763750492f5978394a53316e3478512b2b32616b2f755763427a68326e5a7568356e39775a7246763432575a70620d0a3832764c54676457664a4144537777684875513d0d0a";
		
		System.out.println(ciphertext);
		
		System.out.println(SSOSignatureUtil.doSignature4Mall(ciphertext, strToSign));
		
			System.out.println(DESUtil.decrypt("87ea06deed427fae01bc19cb7121c6902ce7e6b08fd01f6b"));
	
			
		System.out.println(DESUtil.encrypt("34120419861012226X"));
	}
	
}
