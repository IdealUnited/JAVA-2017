package com.idealunited.enums;

import org.apache.commons.lang.StringUtils;

public enum FileTypeEnum {
	CSV( 0, "Comma-Separated Values", "csv", "application/csv;charset=UTF-8" ), 
	EXCEL_03( 1, "MS Excel 97-03 WorkBook", "xls", "application/vnd.ms-excel" ), 
	EXCEL( 2, "MS Excel WorkBook", "xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );

	private final int code;
	private final String description;
	private final String suffix;
	private final String contentType;

	private FileTypeEnum( int code, String description, String suffix, String contentType )
	{
		this.code = code;
		this.description = description;
		this.suffix = suffix;
		this.contentType = contentType;
	}

	public int getCode( )
	{
		return this.code;
	}

	public String getDescription( )
	{
		return this.description;
	}

	public String getSuffix( )
	{
		return this.suffix;
	}

	public String getContentType( )
	{
		return this.contentType;
	}

	public static FileTypeEnum fromFileSuffix( String fileSuffix )
	{
		fileSuffix = StringUtils.trimToEmpty( fileSuffix );
		if ( StringUtils.isEmpty( fileSuffix ) )
		{
			return null;
		}

		FileTypeEnum result = null;
		for ( FileTypeEnum fileType : values( ) )
		{
			if ( fileType.getSuffix( ).equals( fileSuffix ) )
			{
				result = fileType;
				break;
			}
		}

		return result;
	}

	public static FileTypeEnum fromFileTypeCode( final int code )
	{
		FileTypeEnum result = null;
		for ( FileTypeEnum fileType : values( ) )
		{
			if ( fileType.getCode( ) == code )
			{
				result = fileType;
				break;
			}
		}

		return result;
	}

	public static FileTypeEnum fromFileTypeCode( String code )
	{
		code = StringUtils.trimToEmpty( code );
		if ( StringUtils.isEmpty( code ) || !StringUtils.isNumeric( code ) )
		{
			return null;
		}

		return fromFileTypeCode( Integer.parseInt( code ) );
	}

	private static final char DOT = '.';

	public static FileTypeEnum fromFullFileName( String fileName )
	{
		fileName = StringUtils.trimToEmpty( fileName );
		if ( StringUtils.isEmpty( fileName ) )
		{
			return null;
		}

		final int index = fileName.lastIndexOf( DOT );
		if ( index <= 0 || index >= fileName.length( ) )
		{
			return null;
		}

		return fromFileSuffix( fileName.substring( index ) );
	}
}