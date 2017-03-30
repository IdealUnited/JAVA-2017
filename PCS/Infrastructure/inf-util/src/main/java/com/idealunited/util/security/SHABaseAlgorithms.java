package com.idealunited.util.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHABaseAlgorithms {
     
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	/** 
     * SHA512 加密 
     */  
    public static String getSHA512Str(String str) {  
        MessageDigest messageDigest = null;  
  
        try {  
            messageDigest = MessageDigest.getInstance("SHA-512");  
            messageDigest.reset();  
            messageDigest.update(str.getBytes("UTF-8"));  
        } catch (NoSuchAlgorithmException e) {  
            System.out.println("NoSuchAlgorithmException caught!");  
            System.exit(-1);  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
  
        byte[] byteArray = messageDigest.digest();  
  
        StringBuffer sha512StrBuff = new StringBuffer();  
  
        for (int i = 0; i < byteArray.length; i++) {              
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
            	sha512StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
            else  
            	sha512StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
  
        return sha512StrBuff.toString();  
   }
    
	  /** 
		* 将摘要信息转换为相应的编码 
		* @param code 编码类型 
		* @param message 摘要信息 
		* @return 相应的编码字符串 
		* @throws UnsupportedEncodingException 
		*/  
		private static String Encode(String code,String message) throws UnsupportedEncodingException{  
		   MessageDigest md;  
		   String encode = null;  
		   try {  
		       md = MessageDigest.getInstance(code);  
		       encode = byteArrayToHexString(md.digest(message.getBytes("UTF-8")));  
		   } catch (NoSuchAlgorithmException e) {  
		       e.printStackTrace();  
		   }  
		   return encode;  
		}

		private static String byteArrayToHexString(byte[] byteArray){
			StringBuffer sb = new StringBuffer();
			for(byte byt:byteArray){
				sb.append(byteToHexString(byt));
			}
			return sb.toString();
		}

		private static String byteToHexString(byte byt) {
			int n = byt;
			if (n < 0)
				n = 256 + n;
			return hexDigits[n/16] + hexDigits[n%16];
		}
		
	
	    /** 
         * 将摘要信息转换成MD5编码 
         * @param message 摘要信息 
         * @return MD5编码之后的字符串 
	     * @throws UnsupportedEncodingException 
         */  
        public String md5Encode(String message) throws UnsupportedEncodingException{  
            return Encode("MD5",message);  
        }  
        /** 
         * 将摘要信息转换成SHA编码 
         * @param message 摘要信息 
         * @return SHA编码之后的字符串 
         * @throws UnsupportedEncodingException 
         */  
        public String shaEncode(String message) throws UnsupportedEncodingException{  
            return Encode("SHA",message);  
        }  
        /** 
         * 将摘要信息转换成SHA-256编码 
         * @param message 摘要信息 
         * @return SHA-256编码之后的字符串 
         * @throws UnsupportedEncodingException 
         */  
        public String sha256Encode(String message) throws UnsupportedEncodingException{  
            return Encode("SHA-256",message);  
        }  
        /** 
         * 将摘要信息转换成SHA-512编码 
         * @param message 摘要信息 
         * @return SHA-512编码之后的字符串 
         * @throws UnsupportedEncodingException 
         */  
        public static String sha512Encode(String message) throws UnsupportedEncodingException{  
            return Encode("SHA-512",message);  
        }  
    
    public static void main(String[] args) {
		System.out.println(SHABaseAlgorithms.getSHA512Str("yangpeiyu"));
		
		try {
			System.out.println(SHABaseAlgorithms.sha512Encode("yangpeiyu"));
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
	}
	
}
