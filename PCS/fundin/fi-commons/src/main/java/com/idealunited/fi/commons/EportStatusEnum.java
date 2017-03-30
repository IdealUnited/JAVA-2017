package com.idealunited.fi.commons;

import org.apache.commons.lang.StringUtils;

public enum EportStatusEnum {
	INIT("0","未报关"),
	PROCESSING("1","报关中"),
	APPLYED("2","已报关"),
	OVER_TIME("3","超时"),
	REDECLARED( "9", "已重发" );
	
	private String code;
	private String desc;
	/**
	 * 私有构造函数
	 * 
	 * @param code
	 * @param desc
	 */
	EportStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}

	public static EportStatusEnum fromStatusCode( String code )
	{
		code = StringUtils.trimToEmpty( code );
		if ( StringUtils.isEmpty( code ) )
		{
			return null;
		}

		EportStatusEnum result = null;
		for ( EportStatusEnum status : values( ) )
		{
			if ( status.getCode( ).equals( code ) )
			{
				result = status;
				break;
			}
		}

		return result;
	}
}
