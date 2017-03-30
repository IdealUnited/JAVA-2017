/**
 * Project Name:if-hessian
 * File Name : _an.java
 * Package Name:com.idealunited.inf.reqvalidation
 * Copyright (c) 2015, www.pay.com All Rights Reserved.
 *
 */
package com.idealunited.inf.reqvalidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */
public class _an implements TypeMatcher {

	private Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
	
	/* (non-Javadoc)
	 * @see com.idealunited.inf.reqvalidation.TypeMatcher#match(java.lang.String)
	 */
	@Override
	public boolean match(String value) {
		
		Matcher matcher = p.matcher(value);
		return matcher.matches();
		
	}

}
