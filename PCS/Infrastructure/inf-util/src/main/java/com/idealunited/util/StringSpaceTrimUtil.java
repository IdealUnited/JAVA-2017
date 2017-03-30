package com.idealunited.util;

public  class  StringSpaceTrimUtil   
{   
	  
    /**
     * 去除字符串中的空格
     * @param str
     * @return
     */
    public static String SpaceTrim(String str){

    	// "\u0020" 半角　"\u3000"全角,
    	// TODO 还是要扩展
        String [] spaceUnicode = {"\u0020","\u3000","\240"};
  	  	for (int i=0; i<spaceUnicode.length; i++){
  	  		str = str.replaceAll(spaceUnicode[i], "");
        }
	    
  	  	return str;
	 }  
}