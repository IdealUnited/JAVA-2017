package com.idealunited.util.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class ToolsUtil {
	   /**
     * Converts a byte array to hex string.
     * 
     * @param b -
     *            the input byte array
     * @return hex string representation of b.
     */
    
    public static String toHexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(ToolsUtil.HEX_CHARS.charAt(b[i] >>> 4 & 0x0F));
            sb.append(ToolsUtil.HEX_CHARS.charAt(b[i] & 0x0F));
        }
        return sb.toString();
    }

    /**
     * Converts a hex string into a byte array.
     * 
     * @param s -
     *            string to be converted
     * @return byte array converted from s
     */
    public static byte[] toByteArray(String s) {
    	if(s.length() % 2 != 0 ) return new byte[s.length()/2];
        byte[] buf = new byte[s.length() / 2];
        int j = 0;
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) ((Character.digit(s.charAt(j++), 16) << 4) | Character
                    .digit(s.charAt(j++), 16));
        }
        return buf;
    }
    
    private static final String HEX_CHARS = "0123456789abcdef";

    
    
    
    
    /**
	 * 生成密钥对
	 * @param algname
	 * @return
	 * @throws Exception
	 */
	KeyPair genPairKey(String algname,int length)throws Exception {
		
		KeyPairGenerator kp = KeyPairGenerator.getInstance(algname);
		kp.initialize(length);
		
		KeyPair keys= kp.genKeyPair();
		return keys;
		
	}
    
}