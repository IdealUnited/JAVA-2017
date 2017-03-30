/**
 *  File: IdentityUtil.java
 *  Description:
 *  Copyright © 2004-2013 pay.com . All rights reserved. 
 *  Date      Author      Changes
 *  2010-7-18   Terry Ma    Create
 *
 */
package com.idealunited.util;

import java.util.regex.Pattern;

import org.springframework.util.Assert;

/**
 * 邮箱或手机号验证 *
 * 
 * 
 */
public class IdentityUtil {

	public final static Integer MOBILE = 1;
	public final static Integer EMAIL = 2;

	/**
	 * 验证是否是Email.
	 * 
	 * @param idContent
	 * @return
	 */
	public static boolean validateEmail(final String identity) {
		if (StringUtil.isEmpty(identity)) {
			return false;
		}
		String pattern = "\\w+([-+.]\\w+)*@([a-z0-9A-Z]+)([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		return Pattern.matches(pattern, identity);
	}

	/**
	 * 验证是否是手机或小灵通.
	 * 
	 * @param idContent
	 * @return
	 */
	public static boolean validateMobile(final String identity) {
		if (StringUtil.isEmpty(identity)) {
			return false;
		}
		String pattern = "((1[3458]\\d{9})|((0\\d{2,3}){1}([1-9]\\d{6,7}){1}))";
		return Pattern.matches(pattern, identity);
	}

	/**
	 * 
	 * @param identity
	 * @return
	 */
	public static Integer getIDType(final String identity) {
		Assert.notNull(identity);
		return validateEmail(identity) ? EMAIL : MOBILE;
	}

	public static void main(String[] args) {

		System.out.println(validateEmail("chma@99bill.com"));
	}
}
