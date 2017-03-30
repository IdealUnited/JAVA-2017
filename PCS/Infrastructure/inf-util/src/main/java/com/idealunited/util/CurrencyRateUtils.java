package com.idealunited.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class CurrencyRateUtils
{
	// 汇率显示精度为6位小数，计算时增加2位小数的精度
	private static final int CURRENCY_DIVIDE_SCALE = 6 + 2;

	private CurrencyRateUtils( )
	{}

	public static BigDecimal divide( BigDecimal dividend, BigDecimal divisor )
	{
		if ( dividend == null )
		{
			throw new IllegalArgumentException( "被除数不能为NULL" );
		}
		else if ( divisor == null || BigDecimal.ZERO.equals( divisor ) )
		{
			throw new IllegalArgumentException( "除数不能为NULL或零" );
		}

		if ( BigDecimal.ZERO.equals( dividend ) )
		{
			return BigDecimal.ZERO;
		}
		else if ( BigDecimal.ONE.equals( divisor ) )
		{
			return dividend;
		}

		// 使用BigDecimal做除法运算时需要指定结果的精度，否则如果出现无线循环小数时会抛出异常
		return dividend.divide( divisor, CURRENCY_DIVIDE_SCALE, RoundingMode.HALF_UP );
	}
}