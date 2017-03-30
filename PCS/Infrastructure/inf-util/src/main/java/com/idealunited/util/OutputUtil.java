package com.idealunited.util;

import java.io.IOException;
import java.io.Writer;

/**
 * 
 * 输出
 * 
 * @author jerry_jin
 *
 */
public class OutputUtil {
	
	public static void write(Writer writer,String content) throws IOException{
		if(writer!=null){
			try {
				writer.write(String.valueOf(content));
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(writer!=null){
					writer.close();
				}
			}
		}
	}
	
	public static void write(Writer writer,boolean content) throws IOException{
		if(writer!=null){
			try {
				writer.write(content ? "true" : "false");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(writer!=null){
					writer.close();
				}
			}
		}
	}
	
}
