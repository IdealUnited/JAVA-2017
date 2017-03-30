package com.idealunited.fi.commons.eport;

import org.apache.commons.lang.StringUtils;

/**
 * 对应电子口岸报关订单异步更新请求参数chkMark字段
 */
public enum CHKMarkEnum {
	EPORT_DECLARE_SUCCEED( "1", "电子口岸申报成功" ), 
	DECLARE_FAILED( "2", "申报失败" ), 
	EXIST( "3", "支付单已存在" ),
	OVERTIME( "4", "申报超时" ),
	CUSTOMS_ENTRY( "120", "海关入库" ),
	CUSTOMS_RETURN( "100", "海关退单" );

	private final String code;
	private final String description;

	private CHKMarkEnum( String code, String description )
	{
		this.code = code;
		this.description = description;
	}

	public String getCode( )
	{
		return this.code;
	}

	public String getDescription( )
	{
		return this.description;
	}

	public static CHKMarkEnum fromCHKMarkCode( String code )
	{
		if ( StringUtils.isBlank( code ) )
		{
			return null;
		}

		CHKMarkEnum result = null;
		for ( CHKMarkEnum mark : values( ) )
		{
			if ( mark.getCode( ).equals( code ) )
			{
				result = mark;
				break;
			}
		}

		return result;
	}
}