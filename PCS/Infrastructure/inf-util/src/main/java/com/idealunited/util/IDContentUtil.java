/**
 *  File: IDContentUtil.java
 *  Description:
 *  Copyright © 2004-2013 pay.com . All rights reserved. 
 *  Date      Author      Changes
 *  2010-7-18   Terry Ma    Create
 *
 */
package com.idealunited.util;

import java.util.regex.Pattern;

import com.idealunited.util.StringUtil;

/**
 * 邮箱或手机号验证 *
 * 
 * @author peng jiong
 * 
 */
public class IDContentUtil {

	/**
	 * 验证是否是Email.
	 * 
	 * @param idContent
	 * @return
	 */
	public static boolean validateEmail(final String idContent) {
		if (StringUtil.isEmpty(idContent)) {
			return false;
		}
		// String pattern =
		// "[\\w]+([-_.][\\w]+)*@[-\\w]+([.][\\w]+)*\\.[\\w]+([.][\\w]+)*";
		// \\w+([-+.]\\w+)*@([a-z0-9A-Z]+)([-.]\\w+)*\\.\\w+([-.]\\w+)*
		String pattern = "\\w+([-+.]\\w+)*@([a-z0-9A-Z]+)([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		return Pattern.matches(pattern, idContent);
	}

	/**
	 * 验证是否是手机或小灵通.
	 * 
	 * @param idContent
	 * @return
	 */
	public static boolean validateMobile(final String idContent) {
		if (StringUtil.isEmpty(idContent)) {
			return false;
		}
		String pattern = "((1[34578]\\d{9})|((0\\d{2,3}){1}([1-9]\\d{6,7}){1}))";
		return Pattern.matches(pattern, idContent);
	}

	public static boolean validateIdentity(final String idcontent) {
		return validateEmail(idcontent) || validateMobile(idcontent);
		
	}

	public static void main(String[] args) {

		System.out.println(validateEmail("chma@99bill.com"));
	}
}
