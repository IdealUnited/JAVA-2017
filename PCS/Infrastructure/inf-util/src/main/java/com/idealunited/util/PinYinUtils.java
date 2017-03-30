package com.idealunited.util;

import net.sourceforge.pinyin4j.PinyinHelper;

public final class PinYinUtils
{
	private static final int DEFAULT_PINYIN_INDEX = 0;

	private PinYinUtils( )
	{}

	public static String convertToPinYinString( String string )
	{
		StringBuilder buffer = new StringBuilder( );

		String[ ] pinyinArray = null;
		final int length = string.length( );
		for ( int i = 0; i < length; ++i )
		{
			pinyinArray = PinyinHelper.toHanyuPinyinStringArray( string.charAt( i ) );
			if ( pinyinArray != null )
			{
				// FIXME: 多音字处理
				buffer.append( pinyinArray[ DEFAULT_PINYIN_INDEX ] );
			}
		}

		return buffer.toString( );
	}
}