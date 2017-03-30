package com.idealunited.enums;

import org.apache.commons.lang.StringUtils;

public enum SubMerchantStatusEnum {
	VALID( 1, "有效" ), 
	ABOLISHED( 2, "废止" );

	private final int code;
	private final String description;

	private SubMerchantStatusEnum( int code, String description )
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

	public static SubMerchantStatusEnum fromStatusCode( final int statusCode )
	{
		SubMerchantStatusEnum result = null;
		for ( SubMerchantStatusEnum status : values( ) )
		{
			if ( status.getCode( ) == statusCode )
			{
				result = status;
				break;
			}
		}

		return result;
	}

	public static SubMerchantStatusEnum fromStatusCode( String statusCode )
	{
		statusCode = StringUtils.trimToEmpty( statusCode );
		return ( StringUtils.isNotEmpty( statusCode ) && StringUtils.isNumeric( statusCode ) ) ? fromStatusCode( Integer
				.parseInt( statusCode ) ) : null;
	}
}