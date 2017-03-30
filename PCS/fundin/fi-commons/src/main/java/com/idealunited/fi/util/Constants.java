package com.idealunited.fi.util;

/**
 * @author bpliang
 * 常量类
 */
public class Constants {
	
	//网址www方式的正则
	public static final String REGWWW="^(\\w{3})(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$";
	
	//网址http方式的正则
	public static final String REGHTTP="^(\\h{4})+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$";
}
