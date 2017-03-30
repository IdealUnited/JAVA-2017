package com.idealunited.fi.commons.eport;

import org.apache.commons.lang.StringUtils;

/**
 * 此枚举所列报关结果仅用于website查询报关订单
 */
public enum EPortOrderQueryResultEnum {

	UNPROCESSED( "0", "未受理" ), 
	PROCESSED( "1", "已受理" ), 
	PROCESS_FAILURE( "2", "处理失败" ), 
	PROCESS_SUCCESS( "3", "处理成功" );

	private String code;
	private String desc;

	private EPortOrderQueryResultEnum( String code, String desc )
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

	public static EPortOrderQueryResultEnum fromResultCode( String code )
	{
		code = StringUtils.trimToEmpty( code );
		if ( StringUtils.isEmpty( code ) )
		{
			return null;
		}

		EPortOrderQueryResultEnum result = null;
		for ( EPortOrderQueryResultEnum eportResult : values( ) )
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