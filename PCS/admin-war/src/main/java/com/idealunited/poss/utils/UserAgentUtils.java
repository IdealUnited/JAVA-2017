package com.idealunited.poss.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.mail.internet.MimeUtility;
import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.StringUtils;

public class UserAgentUtils
{
	public static final String KEY_USER_AGENT = "User-Agent";

	private static final String USER_AGENT_FIREFOX = "mozilla";
	private static final String USER_AGENT_SAFARI = "safari";
	private static final String USER_AGENT_CHROME = "applewebkit";
	private static final String USER_AGENT_OPERA = "opera";
	// 注: 此信息对IE11不适用
	private static final String USER_AGENT_IE = "msie";

	private UserAgentUtils( )
	{}

	/**
	 * 获取客户端浏览器类型、编码下载文件名
	 * 
	 * @param request
	 * @param fileName
	 * @return String
	 * @author fantasy
	 * @date 2014-1-9
	 */
	public static String encodeFileName( String userAgent, String fileName )
	{
		userAgent = StringUtils.trimToEmpty( userAgent ).toLowerCase( );
		String rtn = "";
		try
		{
			String newFileName = URLEncoder.encode( fileName, CharEncoding.UTF_8 );
			// 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
			rtn = "filename=\"" + newFileName + "\"";
			if ( StringUtils.isNotEmpty( userAgent ) )
			{
				// IE浏览器，只能采用URLEncoder编码
				if ( userAgent.contains( USER_AGENT_IE ) )
				{
					rtn = "filename=\"" + newFileName + "\"";
				}
				// Opera浏览器只能采用filename*
				else if ( userAgent.contains( USER_AGENT_OPERA ) )
				{
					rtn = "filename*=UTF-8''" + newFileName;
				}
				// Safari浏览器，只能采用ISO编码的中文输出
				else if ( userAgent.contains( USER_AGENT_SAFARI ) )
				{
					rtn = "filename=\""
							+ new String( fileName.getBytes( CharEncoding.UTF_8 ),
									CharEncoding.ISO_8859_1 ) + "\"";
				}
				// Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
				else if ( userAgent.contains( USER_AGENT_CHROME ) )
				{
					newFileName = MimeUtility.encodeText( fileName, CharEncoding.UTF_8, "B" );
					rtn = "filename=\"" + newFileName + "\"";
				}
				// FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
				else if ( userAgent.contains( USER_AGENT_FIREFOX ) )
				{
					rtn = "filename*=UTF-8''" + newFileName;
				}
			}
		}
		catch ( UnsupportedEncodingException e )
		{
			// Should not get here
			// Ignore this exception
		}

		return rtn;
	}
}