package com.idealunited.util;


import java.security.PrivateKey;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.idealunited.util.security.ISignature;
import com.idealunited.util.security.impl.RSASignatureImpl;

public class SignatureHelper {
    private static Log logger = LogFactory.getLog(SignatureHelper.class);
    
    private static String generateSignature(String data, PrivateKey prikey)throws Exception  {
        ISignature sign = new RSASignatureImpl();
        return sign.genSignature(data.getBytes(), prikey);
    }
    
    public static String generateAppSignature(String data){
        String args=null;
        try {
            String private_key=RSAHelper.get16to2KeyString(RSAHelper.private_key_String);
            args = SignatureHelper.generateSignature(data,RSAHelper.getPrivateKey(private_key));
        } catch (Exception e) {
            logger.error("SignatureHelper.generateAppSignature throws error ",e);
        }
        return args;
    }
    
    public static void main(String[] args) throws Exception {
       String s= "0328348538458358934895389458934|127.0.0.7";
    
       String str=SignatureHelper.generateAppSignature(s);
       String str1=SignatureHelper.generateAppSignature(s);
       System.out.println(str);
       System.out.println(str1);
    }
}
