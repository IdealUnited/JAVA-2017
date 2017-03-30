package com.idealunited.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Spring Controller Utils类.
 * 
 * 绕过jsp/freemaker直接输出文本的简化函数.
 * 
 * @author calvin volcano.wu
 */
public class SpringControllerUtils {

	//header 常量定义
	private static final String ENCODING_PREFIX = "encoding:";
	private static final String NOCACHE_PREFIX = "no-cache:";
	private static final String ENCODING_DEFAULT = "UTF-8";
	private static final boolean NOCACHE_DEFAULT = true;

	private static Log log = LogFactory.getLog(SpringControllerUtils.class);

	private SpringControllerUtils() {
	}


	// 绕过jsp/freemaker直接输出文本的函数 //

	/**
	 * 直接输出内容的简便函数.
	 
	 * eg.
	 * render("text/plain", "hello", "encoding:GBK");
	 * render("text/plain", "hello", "no-cache:false");
	 * render("text/plain", "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @param headers 可变的header数组，目前接受的值为"encoding:"或"no-cache:",见示例代码.
	 *                不设置时默认值分别为UTF-8和true.                 
	 */
	public static void render(HttpServletResponse response,final String contentType, final String content, final String... headers) {
		
		//分析headers参数
		String encoding = ENCODING_DEFAULT;
		boolean noCache = NOCACHE_DEFAULT;
		for (String header : headers) {
			String headerName = StringUtils.substringBefore(header, ":");
			String headerValue = StringUtils.substringAfter(header, ":");

			if (StringUtils.equalsIgnoreCase(headerName, ENCODING_PREFIX)) {
				encoding = headerValue;
			} else if (StringUtils.equalsIgnoreCase(headerName, NOCACHE_PREFIX)) {
				noCache = Boolean.parseBoolean(headerValue);
			} else
				throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
		}

		//设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(content);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 直接输出文本.
	 * @see #render(String, String, String...)
	 */
	public static void renderText(HttpServletResponse response,final String text, final String... headers) {
		render(response,"text/plain", text, headers);
	}

	/**
	 * 直接输出HTML.
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(HttpServletResponse response,final String html, final String... headers) {
		render(response,"text/html", html, headers);
	}

	/**
	 * 直接输出XML.
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(HttpServletResponse response,final String xml, final String... headers) {
		render(response,"text/xml", xml, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param string json字符串.
	 * @see #render(String, String, String...)
	 *//*
	public static void renderJson(HttpServletResponse response,final String string, final String... headers) {
		render(response,"application/json", string, headers);
	}

	*//**
	 * 直接输出JSON.
	 * 
	 * @param map Map对象,将被转化为json字符串.
	 * @see #render(String, String, String...)
	 *//*
	@SuppressWarnings("unchecked")
	public static void renderJson(final Map map, final String... headers) {
		String jsonString = new JSONObject(map).toString();
		renderJson(jsonString, headers);
	}

	*//**
	 * 直接输出JSON.
	 * 
	 * @param object Java对象,将被转化为json字符串.
	 * @see #render(String, String, String...)
	 *//*
	public static void renderJson(final Object object, final String... headers) {
		String jsonString = new JSONObject(object).toString();
		renderJson(jsonString, headers);
	}*/
}
