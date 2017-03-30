package com.idealunited.fi.commons;

import org.apache.commons.lang.StringUtils;

public enum EportCodeEnum {

	HANGZHOU( "01", "杭州", false ), 
	NINGBO( "02", "宁波", true ), 
//	ZHENGZHOU( "03", "郑州", false ), 
	SHANGHAI( "04", "上海", true ), 
//	CHONGQING( "05", "重庆", false ), 
//	GUANGZHOU( "06", "广州", false ), 
//	SHENZHEN( "07", "深圳", false ),
	XIAN( "08", "西安", false ),
	PINGTAN( "09", "平潭", false ),
	QINGDAO( "10", "青岛", false ),
	PINGTAN_LOCAL( "11", "平潭（地方版）", false )
	;

	private final String code;
	private final String cityName;
	/** 电子口岸接口是否为同步接口 */
	private final boolean isSynchronous;

	private EportCodeEnum( String code, String cityName, boolean isSynchronous )
	{
		this.code = code;
		this.cityName = cityName;
		this.isSynchronous = isSynchronous;
	}

	public String getCode( )
	{
		return this.code;
	}

	public String getCityName( )
	{
		return this.cityName;
	}

	public boolean isSynchronous( )
	{
		return this.isSynchronous;
	}

	/**
	 * 通过枚举<code>code</code>获得枚举
	 * 
	 * @param code
	 * @return
	 */
	public static boolean isExist( String code )
	{
		for ( EportCodeEnum eport : values( ) )
		{
			if ( eport.getCode( ).equals( code ) )
			{
				return true;
			}
		}
		return false;
	}

	public static EportCodeEnum fromEportCode( String code )
	{
		if ( StringUtils.isBlank( code ) )
		{
			return null;
		}

		EportCodeEnum result = null;
		for ( EportCodeEnum eport : values( ) )
		{
			if ( eport.getCode( ).equals( code ) )
			{
				result = eport;
				break;
			}
		}

		return result;
	}

	public static String getCityNameFromCode( String code )
	{
		if ( StringUtils.isBlank( code ) )
		{
			return "";
		}

		EportCodeEnum result = null;
		for ( EportCodeEnum eport : values( ) )
		{
			if ( eport.getCode( ).equals( code ) )
			{
				result = eport;
				break;
			}
		}

		return ( result != null ) ? result.getCityName( ) : "";
	}

	/**
	 * 根据电子口岸城市代码判断该口岸接口是否为同步接口，如果没有对应口岸，返回FALSE
	 * 
	 * @param code
	 * @return
	 */
	public static boolean isSynchronous( String code )
	{
		if ( StringUtils.isBlank( code ) )
		{
			return false;
		}

		boolean isSynchronous = false;
		for ( EportCodeEnum eport : values( ) )
		{
			if ( eport.getCode( ).equals( code ) )
			{
				isSynchronous = eport.isSynchronous( );
				break;
			}
		}

		return isSynchronous;
	}
}
