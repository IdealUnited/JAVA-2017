package com.idealunited.poss.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.idealunited.util.DateUtil;
import com.idealunited.util.JSonUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public final class CsvFileUtils
{
	public static final String CONTENT_TYPE_CSV = "application/csv;charset=UTF-8";
	public static final String FILE_EXTENSION_CSV = ".csv";

	private static final String DEC_FORMAT_CSV = "0.00";

	private static final int DECIMAL_THOUSAND = 3;
	private static final int SCALE_CENT = 2;

	// UTF-8 BOM头信息
	private static final String HEAD_BOM_UTF_8 = new String( new byte[ ] { ( byte ) 0xEF,
			( byte ) 0xBB, ( byte ) 0xBF } );

	private static final String PREFIX_DIRECTORY = "/";

	private static final char TAB = '\t';

	private static final String EXPORT_FILE_DATE_ALL = "_%1s_至_%2s";
	private static final String EXPORT_FILE_DATE_BEGIN = "_起始自_%1s";
	private static final String EXPORT_FILE_DATE_END = "_截至_%1s";

	private CsvFileUtils( )
	{}

	public static String createContent( Map< String, Object > map, String templateFilePath )
			throws IOException, TemplateException
	{
		map = ( map != null ) ? map : new HashMap< String, Object >( 0 );

		if ( StringUtils.isEmpty( templateFilePath ) )
		{
			return JSonUtil.toJSonString( map );
		}

		Configuration cfg = new Configuration( );
		cfg.setClassForTemplateLoading( CsvFileUtils.class, PREFIX_DIRECTORY );
		cfg.setDefaultEncoding( CharEncoding.UTF_8 );

		Template template = cfg.getTemplate( templateFilePath );
		String result = FreeMarkerTemplateUtils.processTemplateIntoString( template, map );

		return ( result != null ) ? result : "";
	}

	/**
	 * Excel打开csv文件时会对数字和日期进行格式转换： 数字用科学记数法显示，日期用用户机器本地格式显示。
	 * 为避免上述自动转换，对数字和日期字段添加制表符'\t'。
	 * 
	 * @param string
	 * @return
	 */
	public static String formatStringForCsv( String string )
	{
		return !StringUtils.isEmpty( string ) ? ( TAB + string ) : "";
	}

	public static String formatNumberForCsv( long number )
	{
		return formatNumberForCsv( number, DEC_FORMAT_CSV );
	}

	public static String formatNumberForCsv( BigDecimal number )
	{
		return formatNumberForCsv( number, DEC_FORMAT_CSV );
	}

	public static String formatNumberForCsv( long number, String decimalFormat )
	{
		return formatNumberForCsv( new BigDecimal( number ), decimalFormat );
	}

	private static String formatNumberForCsv( BigDecimal number, String decimalFormat )
	{
		if ( number == null )
		{
			return "";
		}

		number = number.movePointLeft( DECIMAL_THOUSAND );
		number = number.setScale( SCALE_CENT, BigDecimal.ROUND_HALF_UP );

		return new DecimalFormat( decimalFormat ).format( number );
	}

	/**
	 * 将csv文件写入HttpServletResponse
	 * 
	 * @param response
	 *            输出HttpServletResponse
	 * @param fileName
	 *            导出文件名（建议使用英文、数字、符号组成）
	 * @param content
	 *            导出文件内容
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static void writeToHttpResponse( final HttpServletResponse response,
			final String userAgent, String fileName, String content )
			throws UnsupportedEncodingException, IOException
	{
		if ( response == null )
		{
			return;
		}
		else if ( StringUtils.isEmpty( fileName ) || !fileName.endsWith( FILE_EXTENSION_CSV ) )
		{
			throw new IllegalArgumentException( "Invalid CSV file name: " + fileName );
		}

		response.reset( );

		response.setHeader( "Expires", "0" );
		response.setHeader( "Pragma", "public" );
		response.setHeader( "Cache-Control", "must-revalidate, post-check=0, pre-check=0" );
		response.addHeader( "Content-Disposition",
				"attachment;" + UserAgentUtils.encodeFileName( userAgent, fileName ) );

		response.setContentType( CONTENT_TYPE_CSV );
		response.setCharacterEncoding( CharEncoding.UTF_8 );

		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter( new OutputStreamWriter( response.getOutputStream( ),
					CharEncoding.UTF_8 ) );
			writer.write( HEAD_BOM_UTF_8 );
			writer.write( ( content != null ) ? content : "" );
			writer.flush( );
		}
		catch ( IOException e )
		{
			throw e;
		}
		finally
		{
			if ( writer != null )
			{
				try
				{
					writer.close( );
				}
				catch ( IOException e )
				{
					// Ignore this exception.
				}
			}
		}
	}

	public static String generateExportFileName( String fileNameMain, Date beginTime, Date endTime )
	{
		return generateExportFileName( fileNameMain,
				DateUtil.formatDateTime( DateUtil.SIMPLE_DATE_FROMAT, beginTime ),
				DateUtil.formatDateTime( DateUtil.SIMPLE_DATE_FROMAT, endTime ) );
	}

	public static String generateExportFileName( String fileNameMain, String beginTime,
			String endTime )
	{
		fileNameMain = StringUtils.trimToEmpty( fileNameMain );
		if ( StringUtils.isEmpty( fileNameMain ) )
		{
			throw new IllegalArgumentException( "File name MUST NOT be empty: " + fileNameMain );
		}

		beginTime = StringUtils.trimToEmpty( beginTime );
		endTime = StringUtils.trimToEmpty( endTime );
		if ( StringUtils.isEmpty( beginTime ) && StringUtils.isEmpty( endTime ) )
		{
			return fileNameMain + FILE_EXTENSION_CSV;
		}

		StringBuilder fileNameBuilder = new StringBuilder( 64 );
		fileNameBuilder.append( fileNameMain );

		if ( StringUtils.isEmpty( beginTime ) )
		{
			fileNameBuilder.append( EXPORT_FILE_DATE_END );
			fileNameBuilder.append( FILE_EXTENSION_CSV );

			return String.format( fileNameBuilder.toString( ), endTime );
		}
		else if ( StringUtils.isEmpty( endTime ) )
		{
			fileNameBuilder.append( EXPORT_FILE_DATE_BEGIN );
			fileNameBuilder.append( FILE_EXTENSION_CSV );

			return String.format( fileNameBuilder.toString( ), beginTime );
		}
		else
		{
			fileNameBuilder.append( EXPORT_FILE_DATE_ALL );
			fileNameBuilder.append( FILE_EXTENSION_CSV );

			return String.format( fileNameBuilder.toString( ), beginTime, endTime );
		}
	}
}