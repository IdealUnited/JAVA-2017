/**
 * Project Name:if-hessian
 * File Name : _date.java
 * Package Name:com.idealunited.inf.reqvalidation
 * Copyright (c) 2015, www.pay.com All Rights Reserved.
 *
 */
package com.idealunited.inf.reqvalidation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 
 */
public class _date implements TypeMatcher {

	private SimpleDateFormat sdf = null;
	
	public _date(String pattern) {
		
		pattern = pattern.replaceAll("DD", "dd");
		pattern = pattern.replaceAll("hh", "HH");
		
		sdf = new SimpleDateFormat(pattern);
		
	}
	
	/* (non-Javadoc)
	 * @see com.idealunited.inf.reqvalidation.TypeMatcher#match(java.lang.String)
	 */
	@Override
	public boolean match(String value) {
		
		try {
			sdf.parse(value);
		} catch (ParseException e) {
			return false;
		}
		
		return true;
	}

}
