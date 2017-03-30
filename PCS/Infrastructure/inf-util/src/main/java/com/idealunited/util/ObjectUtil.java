/**
 * Created on 2006-09-20
 */
package com.idealunited.util;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class ObjectUtil {

	private static final Log logger = LogFactory.getLog(ObjectUtil.class);
	public static final Integer EXCHANGEREATE_PRECISION = 10000;

	/**
	 * Default constructor.
	 * 
	 */
	private ObjectUtil() {
	}

	/**
	 * 判断字符串是否为数字.
	 * 
	 * @param str
	 *            String
	 * @return boolean
	 */
	public static boolean isIntType(final String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否是long.
	 * 
	 * @param str
	 *            String
	 * @return boolean
	 */
	public static boolean isLong(final String str) {
		try {
			Long.parseLong(str);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	/**
	 * 判断包括null与null或者null与！null或者！null与null的equal功能.
	 * 
	 * @param arg1
	 *            Object
	 * @param arg2
	 *            Object
	 * @return boolean
	 */
	public static boolean equalsContainNull(final Object arg1, final Object arg2) {
		if ((arg1 == null && arg2 != null) || (arg1 != null && arg2 == null)) {
			return false;
		}
		if (arg1 == null && arg2 == null) {
			return true;
		}
		return arg1.equals(arg2);
	}

	/**
	 * 对传人的double进行四舍五入处理.
	 * 
	 * @param v
	 * @param scale
	 * @return long
	 */
	public static long round(final double v, final int scale) {
		BigDecimal b = new BigDecimal(Double.toString(v));
		// System.out.println(b.setScale(0,
		// BigDecimal.ROUND_HALF_UP).longValue());
		// BigDecimal one = new BigDecimal("1");
		// return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).longValue();
		return b.setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
	}

	/**
	 * 对计费函数计算的金额进行万分处理.
	 * 
	 * @param v
	 *            long
	 * @return long
	 */
	public static long formatFee(final long v) {
		long value = ObjectUtil.round(new Double(v) / 100000, 0) * 10;

		return value;
	}

	/**
	 * 对汇率计算结果进行格式化 例如，$1换算成￥,汇率是6.5556 v=1000*65556=65556000 返回6556
	 * 
	 * @param v
	 *            long
	 * @return long
	 */
	public static long formatExchange(final long v) {// FX:金额转换规范
		long value = ObjectUtil.round(
				new Double(v) / (EXCHANGEREATE_PRECISION), 0);

		return value;
	}

	/**
	 * 对传入的汇率* 10000后返回long型,如:6.55返回65500
	 * 
	 * @param amount
	 *            String
	 * @return long
	 */
	public static long formatStrExRate(final String exRateStr) {// FX:金额转换规范
		long exRate = ObjectUtil.round(new Double(exRateStr)
				* (EXCHANGEREATE_PRECISION), 0);

		return exRate;
	}

	/**
	 * 对传入的金额* 1000后返回long型,如:56.23返回56230.
	 * 
	 * @param amount
	 *            String
	 * @return long
	 */
	public static long formatStrAmount(final String amountStr) {
		long amount = 0L;
		int pos = amountStr.indexOf(".");
		int len = amountStr.length();
		int lastPos = 0;
		if (pos <= 0) {
			lastPos = pos;
		} else {
			lastPos = len - pos - 1;
		}
		if (lastPos < 0) {
			amount = new Long(amountStr).longValue() * 1000;
		} else if (0 == lastPos) {
			amount = new Long(amountStr.substring(0, len - 1) + "000");
		} else if (1 == lastPos) {
			amount = new Long(amountStr.substring(0, len - lastPos - 1)
					+ amountStr.substring(len - lastPos) + "00");
		} else if (2 == lastPos) {
			amount = new Long(amountStr.substring(0, len - lastPos - 1)
					+ amountStr.substring(len - lastPos) + "0");
		} else {
			amount = ObjectUtil.round(new Double(amountStr) * 1000, 0);
		}
		return amount;
	}

	public static Object instanceByClass(String className) {
		try {
			Class cmdClass = Class.forName(className.trim());
			return cmdClass.newInstance();
		} catch (ClassNotFoundException cnfe) {
			logger.error("cann't find class[" + className + "]", cnfe);
		} catch (InstantiationException ie) {
			logger.error("cann't instance for the class. [" + className + "]",
					ie);
		} catch (IllegalAccessException iae) {
			logger.error("cann't access for the class. [" + className + "]",
					iae);
		} catch (Exception e) {
			logger.error("other exception for the class. [" + className + "]",
					e);
		}
		return null;
	}

	public static void main(String args[]) {
		long a = ObjectUtil.round(new Double(995.881), 0);
		System.out.println(a);
	}
}
