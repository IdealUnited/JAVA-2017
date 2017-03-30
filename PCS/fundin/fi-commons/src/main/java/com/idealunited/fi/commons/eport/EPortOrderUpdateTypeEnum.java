package com.idealunited.fi.commons.eport;

import org.apache.commons.lang.StringUtils;

public enum EPortOrderUpdateTypeEnum {
	NOTIFICATION( "1", "异步通知更新" ), 
	SCHEDULE( "2", "调度更新" );

	private final String code;
	private final String description;

	private EPortOrderUpdateTypeEnum( String code, String description )
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

	public static EPortOrderUpdateTypeEnum fromUpdateType( String updateType )
	{
		updateType = StringUtils.trimToEmpty( updateType );
		if ( StringUtils.isEmpty( updateType ) )
		{
			return null;
		}

		EPortOrderUpdateTypeEnum result = null;
		for ( EPortOrderUpdateTypeEnum updateTypeEnum : values( ) )
		{
			if ( updateTypeEnum.getCode( ).equals( updateType ) )
			{
				result = updateTypeEnum;
				break;
			}
		}

		return result;
	}
}