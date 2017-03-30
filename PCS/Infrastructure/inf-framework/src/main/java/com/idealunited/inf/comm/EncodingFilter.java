/**
 *  File: EncodingFilter.java
 *  Description:
 *  Copyright (c) 2004-2013 pay.com . All rights reserved. 
 *  Date      Author      Changes
 *  2010-7-16   Terry Ma    Create
 *
 */
package com.idealunited.inf.comm;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * <p>
 * Title: 实现WEB层字符集的转换
 * </p>
 */

public class EncodingFilter extends OncePerRequestFilter {
	private String encoding;

	private boolean forceEncoding;

	/**
	 * Set the encoding to use for requests. This encoding will be passed into a
	 * ServletRequest.setCharacterEncoding call.
	 * <p>
	 * Whether this encoding will override existing request encodings depends on
	 * the "forceEncoding" flag.
	 * 
	 * @see #setForceEncoding
	 * @see javax.servlet.ServletRequest#setCharacterEncoding
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * Set whether the encoding of this filter should override existing request
	 * encodings. Default is "false", i.e. do not modify encoding if
	 * ServletRequest.getCharacterEncoding returns a non-null value.
	 * 
	 * @see #setEncoding
	 * @see javax.servlet.ServletRequest#getCharacterEncoding
	 */
	public void setForceEncoding(boolean forceEncoding) {
		this.forceEncoding = forceEncoding;
	}

	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		if (url.endsWith("/consume/payment/tyConsumeCardResponse.htm")) {
			logger.info(url);
		} else {
			if (this.forceEncoding || request.getCharacterEncoding() == null) {
				request.setCharacterEncoding(this.encoding);
			}
		}
		filterChain.doFilter(request, response);
	}
}
