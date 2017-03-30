package com.idealunited.enums;

import org.apache.commons.lang.StringUtils;

public enum SubMerchantCrossFlagEnum {
	DOMESTIC( 0, "境内商户" ), 
	ABROAD( 1, "境外商户" );

	private final int code;
	private final String description;

	private SubMerchantCrossFlagEnum( int code, String description )
	{
		this.code = code;
		this.description = description;
	}

	public int getCode( )
	{
		return this.code;
	}

	public String getDescription( )
	{
		return this.description;
	}

	public static SubMerchantCrossFlagEnum fromCrossFlag( final int crossFlag )
	{
		SubMerchantCrossFlagEnum result = null;
		for ( SubMerchantCrossFlagEnum flag : values( ) )
		{
			if ( flag.getCode( ) == crossFlag )
			{
				result = flag;
				break;
			}
		}

		return result;
	}

	public static SubMerchantCrossFlagEnum fromCrossFlag( String crossFlag )
	{
		crossFlag = StringUtils.trimToEmpty( crossFlag );
		return ( StringUtils.isNotEmpty( crossFlag ) && StringUtils.isNumeric( crossFlag ) ) ? fromCrossFlag( Integer
				.parseInt( crossFlag ) ) : null;
	}
}