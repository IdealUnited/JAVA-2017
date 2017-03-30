package com.idealunited.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import com.idealunited.util.StringUtil;

public class FormatStrUtil {

    /**
     * 字符串右补空格
     * @see 该方法默认采用空格(其ASCII码为32)来右补字符
     * @see 若想自己指定所补字符,可以使用<code>rightPadForByte(String str, int size, int padStrByASCII)</code>方法
     */
    public static String rightPadForByte(String str, int size,String charset){
        return rightPadForByte(str, size, 32,charset);
    }
    
    
    /**
     * 字符串右补字符
     * @see 若str对应的byte[]长度不小于size,则按照size截取str对应的byte[],而非原样返回str
     * @see 所以size参数很关键..事实上之所以这么处理,是由于支付处理系统接口文档规定了字段的最大长度
     * @see 若对普通字符串进行右补字符,建议org.apache.commons.lang.StringUtils.rightPad(...)
     * @param size          该参数指的不是字符串长度,而是字符串所对应的byte[]长度
     * @param padStrByASCII 该值为所补字符的ASCII码,如32表示空格,48表示0,64表示@等
     */
    public static String rightPadForByte(String str, int size, int padStrByASCII,String charset){
		try {
			
			byte[] srcByte = StringUtils.isNotBlank(charset)?str.getBytes(charset):str.getBytes();
	        byte[] destByte = null;
	        if(srcByte.length >= size){
	            //由于size不大于原数组长度,故该方法此时会按照size自动截取,它会在数组右侧填充'(byte)0'以使其具有指定的长度
	            destByte = Arrays.copyOf(srcByte, size);
	        }else{
	            destByte = Arrays.copyOf(srcByte, size);
	            Arrays.fill(destByte, srcByte.length, size, (byte)padStrByASCII);
	        }
	        return new String(destByte,charset);
	        
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
    }
    
    
    /**
     * 字符串左补空格
     * @see 该方法默认采用空格(其ASCII码为32)来左补字符
     * @see 若想自己指定所补字符,可以使用<code>leftPadForByte(String str, int size, int padStrByASCII)</code>方法
     */
    public static String leftPadForByte(String str, int size,String charset){
        return leftPadForByte(str, size, 32,charset);
    }
    
    
    /**
     * 字符串左补字符
     * @see 若str对应的byte[]长度不小于size,则按照size截取str对应的byte[],而非原样返回str
     * @see 所以size参数很关键..事实上之所以这么处理,是由于支付处理系统接口文档规定了字段的最大长度
     * @param padStrByASCII 该值为所补字符的ASCII码,如32表示空格,48表示0,64表示@等
     */
    public static String leftPadForByte(String str, int size, int padStrByASCII,String charset){
	    try{
	    	byte[] srcByte = StringUtils.isNotBlank(charset)?str.getBytes(charset):str.getBytes();
	        byte[] destByte = new byte[size];
	        Arrays.fill(destByte, (byte)padStrByASCII);
	        if(srcByte.length >= size){
	            System.arraycopy(srcByte, 0, destByte, 0, size);
	        }else{
	            System.arraycopy(srcByte, 0, destByte, size-srcByte.length, srcByte.length);
	        }
	        return new String(destByte,charset);
	    } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
    }
	
}
