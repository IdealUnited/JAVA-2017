/**
 *  File: XSSHttpServletRequestWrapper.java
 *  Description:
 *  Copyright (c) 2004-2013 pay.com . All rights reserved. 
 *  Date      Author      Changes
 *  2010-8-18   terry_ma     Create
 *
 */
package com.idealunited.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.idealunited.util.StringUtil;

/**
 * 
 */
public class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private static Map<String, String> transferMaps = new HashMap<String, String>();
	static {
		transferMaps.put("%3c", "&lt;");
		transferMaps.put("<", "&lt;");
		transferMaps.put("\"", "&quot;");
		transferMaps.put("%22", "&quot;");
		transferMaps.put("'", "&#39;");
		transferMaps.put("%27", "&apos;");
		transferMaps.put(">", "&#62;");
	}

	public static String transferBack(String parameter){
	    if (!StringUtil.isEmpty(parameter)) {
            String result = parameter;
            for (String key : transferMaps.keySet()) {
                result = result.replaceAll(transferMaps.get(key), key);
            }
            return result;
        }
        return parameter;
	}
	
	public XSSHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getQueryString() {

		String queryString = super.getQueryString();

		return transfer(queryString);
	}

	@Override
	public Object getAttribute(String name) {

		Object attribute = super.getAttribute(name);

		if (null != attribute && attribute instanceof String) {
			return transfer((String) attribute);
		}

		return attribute;
	}

	@Override
	public Map getParameterMap() {

		Map resultMap = super.getParameterMap();

		if (null != resultMap && !resultMap.isEmpty()) {

			for (Object key : resultMap.keySet()) {

				Object value = resultMap.get(key);
				if (null != value && value instanceof String) {
					resultMap.put(key, transfer((String) value));
				}
			}
		}
		return resultMap;
	}

	@Override
	public String getParameter(String parameter) {

		String value = super.getParameter(parameter);
		return transfer((String) value);
	}

	@Override
	public String[] getParameterValues(String parameter) {

		String[] results = super.getParameterValues(parameter);
		if (results == null) {
			return null;
		}

		int count = results.length;
		String[] transferResults = new String[count];
		for (int i = 0; i < count; i++) {
			String value = results[i];
			transferResults[i] = transfer((String) value);
		}
		return transferResults;
	}

	private String transfer(String parameter) {

		if (!StringUtil.isEmpty(parameter)) {
			String result = parameter;
			for (String key : transferMaps.keySet()) {
				result = result.replaceAll(key, transferMaps.get(key));
			}
			return result;
		}
		return parameter;
	}
}