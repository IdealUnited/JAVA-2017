/**
 *  File: HttpClientUtil.java
 *  Description: Http连接工具
 *  Copyright © 2004-2013 pay.com . All rights reserved.
 *  Date      Author      Changes
 *  2008-8-27   terry_ma     设置http连接的读超时时间
 *
 */
package com.idealunited.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("deprecation")
public class HttpClientUtil {

	private static final int READ_TIMEOUT = 10000;// 10s
	private static final int CONNECTION_TIMEOUT = 10000; // 10s
	private static Log logger = LogFactory.getLog(HttpClientUtil.class);

	/**
	 * 使用get方式发送请求
	 * 
	 * @param url
	 *            要访问的url
	 * @return
	 * @throws IOException
	 * @throws HttpException
	 */
	public static final GetMethod getWithHttpMethod(final String url,
			final int connectionTimeout, final int readTimeout)
			throws HttpException, IOException {

		HttpClient httpClient = new HttpClient();

		Protocol https = new Protocol("https",
				new SecureProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", https);

		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());

		// httpClient.getParams().setConnectionManagerTimeout(connectionTimeout);
		httpClient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(connectionTimeout);
		httpClient.getParams().setSoTimeout(readTimeout);
		int statusCode = httpClient.executeMethod(getMethod);
		if (statusCode == HttpStatus.SC_OK) {
			return getMethod;
		}
		return null;

	}

	/**
	 * 使用get方式发送请求返回流.
	 * 
	 * @param url
	 *            要访问的url
	 * @return
	 */
	public static final byte[] getWithBackStream(final String url) {

		HttpClient httpClient = new HttpClient();

		Protocol https = new Protocol("https",
				new SecureProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", https);

		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(10, false));

		// httpClient.getParams().setConnectionManagerTimeout(CONNECTION_TIMEOUT);
		httpClient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(CONNECTION_TIMEOUT);
		httpClient.getParams().setSoTimeout(READ_TIMEOUT);
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				return getMethod.getResponseBody();
			}
			return null;
		} catch (Exception httpexception) {
			return null;
		} finally {
			getMethod.releaseConnection();
		}
	}

	/**
	 * 使用get方式发式送请求返回流.
	 * 
	 * @param url
	 *            要访问的url
	 * @return
	 * @throws IOException
	 * @throws HttpException
	 */
	public static final byte[] getWithBackStreamThrowException(final String url)
			throws HttpException, IOException {

		HttpClient httpClient = new HttpClient();

		Protocol https = new Protocol("https",
				new SecureProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", https);

		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(10, false));

		// httpClient.getParams().setConnectionManagerTimeout(CONNECTION_TIMEOUT);
		httpClient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(15000);
		httpClient.getParams().setSoTimeout(READ_TIMEOUT);
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				return getMethod.getResponseBody();
			}
			return null;
		} finally {
			getMethod.releaseConnection();
		}
	}

	public static final byte[] getWithBackStreamByGet(final String url)
			throws HttpException, IOException {

		HttpClient httpClient = new HttpClient();

		Protocol https = new Protocol("https",
				new SecureProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", https);

		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(10, false));

		// httpClient.getParams().setConnectionManagerTimeout(CONNECTION_TIMEOUT);
		httpClient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(15000);
		httpClient.getParams().setSoTimeout(READ_TIMEOUT);
		int statusCode = httpClient.executeMethod(getMethod);
		if (statusCode == HttpStatus.SC_OK) {
			return getMethod.getResponseBody();
		}
		return null;
	}

	public static final byte[] getWithBackStream(final String url,
			final NameValuePair[] parameters) {

		HttpClient httpClient = new HttpClient();

		Protocol https = new Protocol("https",
				new SecureProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", https);

		PostMethod method = new PostMethod(url);
		method.setRequestBody(parameters);
		// httpClient.getParams().setConnectionManagerTimeout(CONNECTION_TIMEOUT);//
		// 通知商家超时时间
		httpClient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(CONNECTION_TIMEOUT);
		httpClient.getParams().setSoTimeout(READ_TIMEOUT);
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");  
		try {
			int statusCode = httpClient.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
				return method.getResponseBody();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("conmunication error:", e);
			return null;
		} finally {
			method.releaseConnection();
		}
	}

	public static final byte[] getWithBackStreamByGetMethod(final String url,
			final int connectionTimeout, final int readTimeout)
			throws HttpException, IOException {
		HttpClient httpClient = new HttpClient();
		Protocol https = new Protocol("https",
				new SecureProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", https);
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(10, false));

		// httpClient.getParams().setConnectionManagerTimeout(CONNECTION_TIMEOUT);
		httpClient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(connectionTimeout);
		httpClient.getParams().setSoTimeout(readTimeout);
		int statusCode = httpClient.executeMethod(getMethod);
		if (statusCode == HttpStatus.SC_OK) {
			return getMethod.getResponseBody();
		}
		return null;
	}

	public static final byte[] postDealRequest(String chargeUrl)
			throws HttpException, IOException {
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = null;
		byte[] response = null;
		try {
			Protocol https = new Protocol("https",
					new SecureProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", https);
			postMethod = new PostMethod(chargeUrl);
			postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(10, false));

			httpClient.getHttpConnectionManager().getParams()
					.setConnectionTimeout(CONNECTION_TIMEOUT);
			httpClient.getParams().setSoTimeout(READ_TIMEOUT);
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				response = postMethod.getResponseBody();
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		return response;
	}

	public static final byte[] executeWithHeader(final String url, Header header) {

		HttpClient httpClient = new HttpClient();

		List<Header> headers = new ArrayList<Header>();
		// headers.add(new Header("User-Agent", "POSS"));
		headers.add(header);
		httpClient.getHostConfiguration().getParams()
				.setParameter("http.default-headers", headers);

		Protocol https = new Protocol("https",
				new SecureProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", https);

		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(10, false));

		// httpClient.getParams().setConnectionManagerTimeout(CONNECTION_TIMEOUT);
		httpClient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(CONNECTION_TIMEOUT);
		httpClient.getParams().setSoTimeout(READ_TIMEOUT);
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				return getMethod.getResponseBody();
			}
			return null;
		} catch (Exception httpexception) {
			return null;
		} finally {
			getMethod.releaseConnection();
		}
	}

	/**
	 * 使用POST方式提交数据
	 * 
	 * @param url
	 * @return xml str.
	 * @throws HttpException.
	 * @throws IOException.
	 */
	public static String getPostXml(String url, String xmlData)
			throws HttpException, IOException {

		PostMethod post = null;
		try {
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams()
					.setConnectionTimeout(10 * 1000);
			client.getHttpConnectionManager().getParams()
					.setSoTimeout(10 * 1000);
			post = new PostMethod(url);
			post.setRequestHeader("contentType", "text/html;charset=UTF-8");
			RequestEntity requestEntity = new StringRequestEntity(xmlData,
					"text/xml", "UTF-8");
			post.setRequestEntity(requestEntity);

			int statusCode = client.executeMethod(post);
			if (statusCode != HttpStatus.SC_OK)
				throw new HttpException("connection error!");
			String response = post.getResponseBodyAsString();
			// System.out.println(response);
			return response;
		} finally {
			post.releaseConnection();
		}
	}
}
