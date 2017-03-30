package com.idealunited.util;

import java.text.DecimalFormat;

import org.springframework.util.Assert;

public class NumberUtil {
	public static boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static Boolean isBetweenEquals(final Long min, final Long mid,
			final Long max) {
		Assert.notNull(mid);
		Assert.notNull(min);
		if (max == null || max.longValue() == new Long(0).longValue()) {
			return isGreateThenEquals(mid, min);
		}
		return max.longValue() >= mid.longValue()
				&& mid.longValue() >= min.longValue();
	}

	public static Boolean isGreateThenEquals(final Long max, final Long min) {
		return max.longValue() >= min.longValue();
	}

	/**
	 * 格式化一个double值，如保留小数位等
	 * 
	 * @param adouble
	 * @param pattern
	 *            :如"##0.00"表示保留两位小数
	 * @return
	 */
	public static String formatDoubleToString(double adouble, String pattern) {
		DecimalFormat df = new DecimalFormat(pattern);
		String doubleString = df.format(adouble);
		return doubleString;
	}

	// 检查字符串是否为数字
	public static boolean isNumber(String str) {
		if (str == null || str == "")
			return false;
		java.util.regex.Pattern pattern = java.util.regex.Pattern
				.compile("[0-9]*");
		java.util.regex.Matcher match = pattern.matcher(str);
		if (match.matches() == false) {
			return false;
		} else {
			return true;
		}
	}
}
