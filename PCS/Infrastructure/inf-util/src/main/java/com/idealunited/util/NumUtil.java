/**
 * 
 */
package com.idealunited.util;

import java.util.regex.Pattern;

/**
 * @author ch-ma
 * 
 */
public class NumUtil {

	static String pattern = "^[0-9]+$";

	public static boolean isNum(final String value) {
		return !StringUtil.isEmpty(value) && Pattern.matches(pattern, value);
	}
}
