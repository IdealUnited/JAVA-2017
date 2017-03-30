package com.idealunited.fi.commons;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description Http协议预处理工具类
 * @project gateway-pay
 * @file RefundService.java
 * @note <br>
 * @develop JDK1.6 + Eclipse 3.5
 */

public class HTTPProtocolHandleUtil {

	public static void setAll(HttpServletRequest request,
			HttpServletResponse response) {
		HTTPProtocolHandleUtil.setNoCache(request, response);
		HTTPProtocolHandleUtil.setUTF8(request, response);
	}

	/**
	 * 设置字符集为UTF-8
	 * 
	 * @param request
	 * @param response
	 */
	public static void setUTF8(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
	}

	/**
	 * 设置字符集为GBK
	 * 
	 * @param request
	 * @param response
	 */
	public static void setGBK(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.setCharacterEncoding("GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("GBK");
	}

	/**
	 * 设置字符集为GB2312
	 * 
	 * @param request
	 * @param response
	 */
	public static void setGB2312(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.setCharacterEncoding("GB2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("GB2312");
	}

	/**
	 * 设置不缓存
	 * 
	 * @param request
	 * @param response
	 */
	public static void setNoCache(HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getProtocol().compareTo("HTTP/1.0") == 0) {

			response.setHeader("Pragma", "no-cache");

		} else if (request.getProtocol().compareTo("HTTP/1.1") == 0) {

			response.setHeader("Cache-Control", "no-cache");

		}
		response.setDateHeader("Expires", 0);
	}

}
