package com.idealunited.util;

import java.math.BigDecimal;

/**
 * @Title: MathUtil.java
 * @Package com.idealunited.gateway.utils
 * @Description: 精确计算器工具类
 * @author Gavin_Song(foxdog888@gmail.com)
 * @date 2010-10-29 下午03:06:56
 * @version V1.0
 */
public class MathUtil {

	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static String add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).toString();
	}

	/**
	 * 提供精确的加法运算-用于金额计算。
	 * 
	 * @param v1
	 *            被加数-以厘为单位
	 * @param v2
	 *            加数-以厘为单位
	 * @return 两个参数的和-以元为单位
	 */
	public static String add(long v1, long v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).toString();
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static String sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).toString();
	}

	/**
	 * 提供精确的减法运算-用于金额计算。
	 * 
	 * @param v1
	 *            被减数-以厘为单位
	 * @param v2
	 *            减数-以厘为单位
	 * @return 两个参数的差-以元为单位
	 */
	public static String sub(long v1, long v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).toString();
	}
	
	/**
	 * 提供精确的减法运算,参数为Long,返回Long
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Long sub(Long v1,Long v2){
		BigDecimal balance1 = new BigDecimal(v1);
		BigDecimal balance2 = new BigDecimal(v2);
		return balance1.subtract(balance2).longValue();
	}

	/**
	 * 提供精确的乘法运算-参数为double
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static String multiply(double v1, double v2) {
		BigDecimal balance1 = new BigDecimal(v1);
		BigDecimal balance2 = new BigDecimal(v2);
		return balance1.multiply(balance2).toString();
	}

	/**
	 * 提供精确的乘法运算-参数为long
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static String multiply(long v1, long v2) {
		BigDecimal balance1 = new BigDecimal(v1);
		BigDecimal balance2 = new BigDecimal(v2);
		return balance1.multiply(balance2).toString();
	}
	
	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static String div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算-用于金额计算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static String div(long v1, long v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static String div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 提供（相对）精确的除法运算-用于金额计算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数-以厘为单位
	 * @param v2
	 *            除数-以厘为单位
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商-以元为单位
	 */
	public static String div(long v1, long v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static String round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 提供精确的小数位四舍五入处理-用于金额计算
	 * 
	 * @param v
	 *            以厘为单位
	 * @param scale小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static String round(long v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal balance = new BigDecimal(v);
		BigDecimal one = new BigDecimal("1");
		return balance.divide(one, scale, BigDecimal.ROUND_HALF_UP).toString();
	}

}
