package com.idealunited.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 二级商户收款方账户类型
 */
public enum PayeeAccountTypeEnum {
	COMPANY( 0, "企业" ), 
	INDIVIDUAL( 1, "个人" );

	private final int code;
	private final String description;

	private PayeeAccountTypeEnum( int code, String description )
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

	public static PayeeAccountTypeEnum fromAccountTypeCode( final int accountTypeCode )
	{
		PayeeAccountTypeEnum result = null;
		for ( PayeeAccountTypeEnum accountType : values( ) )
		{
			if ( accountType.getCode( ) == accountTypeCode )
			{
				result = accountType;
				break;
			}
		}

		return result;
	}

	public static PayeeAccountTypeEnum fromAccountTypeCode( String accountTypeCode )
	{
		accountTypeCode = StringUtils.trimToEmpty( accountTypeCode );
		if ( StringUtils.isEmpty( accountTypeCode ) || !StringUtils.isNumeric( accountTypeCode ) )
		{
			return null;
		}

		return fromAccountTypeCode( Integer.parseInt( accountTypeCode ) );
	}
}