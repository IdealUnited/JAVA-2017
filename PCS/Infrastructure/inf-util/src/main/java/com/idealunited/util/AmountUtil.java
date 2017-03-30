package com.idealunited.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ 金额格式转换.
 */
public class AmountUtil {

	/**
	 * 四舍五入保留两位小数
	 * 
	 * @param amount
	 * @return amount四舍五入结果
	 */
	public static BigDecimal formatAmount( BigDecimal amount )
	{
		if ( amount == null )
		{
			return null;
		}

		return amount.setScale( 2, BigDecimal.ROUND_HALF_UP );
	}

	/**
	 * 保留6数位
	 * 
	 * @param amount
	 * @return amount结果
	 */
	public static String formatAmountTo6Digt( BigDecimal amount )
	{
		DecimalFormat fmt = new DecimalFormat( "#0.000000" );
		return fmt.format( amount );
	}

	/**
	 * 验证输入的字符串是否是金额格式
	 * 
	 * @param amount
	 * @return
	 */
	public static boolean checkAmount(String amountString) {
		if (null == amountString || amountString.trim().length() == 0)
			return false;
		amountString = amountString.trim();
		String str = "^(0(\\.\\d{0,2})?|([1-9]+[0]*)+(\\.\\d{0,2})?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(amountString);
		return m.find();
	}

	/**
	 * 验证输入的字符串是否是金额格式
	 * 
	 * @param amount
	 * @return
	 */
	public static String formatAmountStr(String amountString) {
		int pos = amountString.indexOf(".");
		if (pos != -1) {
			int tempInt = amountString
					.substring(pos + 1, amountString.length()).length();
			if (tempInt == 1)
				amountString += "0";
			if (tempInt > 2)
				amountString = amountString.substring(0, pos + 3);
		} else {
			amountString += ".00";
		}
		return amountString;
	}

	public static BigDecimal toBigDecimalAmount(Long amount) {
		Long tmpAmount = amount;
		if (tmpAmount == null) {
			tmpAmount = 0L;
		}
		return new BigDecimal(tmpAmount).divide(new BigDecimal(1000), 2,
				BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal toBigDecimalAmount(String amount) {
		Long tmpAmount = Long.valueOf(amount);
		if (tmpAmount == null) {
			tmpAmount = 0L;
		}
		return new BigDecimal(tmpAmount).divide(new BigDecimal(1000), 2,
				BigDecimal.ROUND_HALF_UP);
	}

	public static Long toLongAmount(String amountStr) {
		if (checkAmount(amountStr)) {
			return new BigDecimal(amountStr).multiply(new BigDecimal(1000))
					.longValue();
		}
		return 0L;
	}

	public static String numberFormat(BigDecimal amount) {
		NumberFormat formatter = new DecimalFormat("#,###,##0.00");
		if (amount == null) {
			return "NULL";
		}
		return formatter.format(amount.doubleValue());
	}
}