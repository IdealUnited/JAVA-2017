package com.idealunited.fi.commons;

import org.apache.commons.lang.StringUtils;

public enum EportResultEnum {

	UNPROCESSED( "0", "未受理" ), 
	PROCESSED( "1", "已受理" ), 
	DECLARE_FAILED( "2", "申报失败" ), 
	EPORT_DECLARE_SUCCEED( "3", "电子口岸申报成功" ),
	OVERTIME( "4", "超时" ),

	// "海关入库"及"海关退单"一般用于总署统一版电子口岸系统
	CUSTOMS_ENTRY( "120", "海关入库" ),
	CUSTOMS_RETURN( "100", "海关退单" );

	private String code;
	private String desc;

	private EportResultEnum( String code, String desc )
	{
		this.code = code;
		this.desc = desc;
	}

	public String getCode( )
	{
		return code;
	}

	public String getDesc( )
	{
		return desc;
	}

	public static EportResultEnum fromResultCode( String code )
	{
		code = StringUtils.trimToEmpty( code );
		if ( StringUtils.isEmpty( code ) )
		{
			return null;
		}

		EportResultEnum result = null;
		for ( EportResultEnum eportResult : values( ) )
		{
			if ( eportResult.getCode( ).equals( code ) )
			{
				result = eportResult;
				break;
			}
		}

		return result;
	}
}