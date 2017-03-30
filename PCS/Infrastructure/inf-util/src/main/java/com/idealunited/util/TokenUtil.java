package com.idealunited.util;

import java.util.UUID;

public class TokenUtil {

 	public static String getUUID(){
        return UUID.randomUUID().toString();
    }
    
    public static Long getMostSignUUID(){
        return UUID.randomUUID().getMostSignificantBits();
    }
    
  
}
