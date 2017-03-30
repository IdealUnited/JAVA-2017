/**
 * Created on 2006-09-20
 */
package com.idealunited.helper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.util.Assert;

/**
 * @author
 * 
 */
public abstract class PricingStrategyUtil {

	/**
	 * 对计费函数计算的金额进行分处理.
	 * 对计费函数计算的金额进行万分处理.
	 * @param v
	 *            long
	 * @return long
	 */
	public static long formatFee(final long v) {
		long value = PricingStrategyUtil.round(new BigDecimal(v).divide(new BigDecimal("100000")), 0) * 10;
		//long value = PricingStrategyUtil.round(new Double(v) / 100000, 0) * 10;
//		long value = PricingStrategyUtil.round(new Double(v) / 10000, 0) * 10;
		return value;
	}

	/**
	 * 对传人的double进行四舍五入处理.
	 * 
	 * @param v
	 * @param scale
	 * @return long
	 */
	public static long round(final BigDecimal b, final int scale) {
		//BigDecimal b = new BigDecimal(Double.toString(v));
		// System.out.println(b.setScale(0,
		// BigDecimal.ROUND_HALF_UP).longValue());
		// BigDecimal one = new BigDecimal("1");
		// return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).longValue();
		return b.setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
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

	public static Boolean isBetweenEquals(final Timestamp validDate,
			final Date mid, final Timestamp invalidDate) {
		if (isNullDate(validDate)) {
			return false;
		}
		if (isNullDate(invalidDate)
				|| (invalidDate.getTime() == getDate(1900, 1, 1).getTime())
				&& (validDate.equals(mid) || validDate.before(mid))) {
			return true;//
		}

		if ((validDate.before(mid)) && invalidDate.after(mid)) {
			return true;
		}
		return false;

	}

	public static boolean isNullDate(Timestamp ts) {
		// if(ts == null || ts.getNanos() == 0) return true;
		// return false;
		if (ts == null)
			return true;
		return false;
	}

	public static Date getDate(final int year, final int month, final int day) {
		return new GregorianCalendar(year, month - 1, day).getTime();
	}

	private static Boolean isGreateThenEquals(final Long max, final Long min) {
		return max.longValue() >= min.longValue();
	}

}
