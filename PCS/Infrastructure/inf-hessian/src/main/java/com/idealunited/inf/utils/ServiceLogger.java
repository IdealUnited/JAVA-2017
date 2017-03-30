package com.idealunited.inf.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A log helper class in our System It helps to print the message with a prefix
 * looks like follow:<br/>
 * Original message: "This is a info message"<br/>
 * Message with prefix: "[030010] [134123112111][301] This is a info message"<br/>
 * 
 * <p>
 * The first alphanumeric element is <code>serCode</code> parameter in request,
 * and the second one is <code>sysTraceNo</code>, the third one is
 * <code>originNo</code> source system.
 * </p>
 * 
 * <p>
 * With the prefix offered by this logger, we could easily located the log
 * informations belongs to request witch we want
 * </p>
 */
public class ServiceLogger {

	/**
	 * Only Logger instance in our system, it is thread safe.
	 */
	private static final Logger log = LoggerFactory
			.getLogger(ServiceLogger.class);

	/**
	 * Each request will be handled by one thread in Servlet container thread
	 * pool We holds each session identify elements per request
	 */
	private static final ThreadLocal<Map<String, String>> sessionMap = new ThreadLocal<Map<String, String>>();

	/**
	 * This Method should be used in Service Facade.
	 * 
	 * Note: service implementations should not use this method to change the
	 * session identification
	 * 
	 * @param serCode
	 * @param sysTraceNo
	 * @param originNo
	 */
	public static void setSession(String serCode, String sysTraceNo,
			String originNo) {
		Map<String, String> sessionMap = new HashMap<String, String>(3, 1);
		sessionMap.put("serCode", serCode);
		sessionMap.put("sysTraceNo", sysTraceNo);
		sessionMap.put("originNo", originNo);

		ServiceLogger.sessionMap.set(sessionMap);
	}

	public static void trace(String msg, Object... obj) {
		log.trace(wrapMsg(msg, obj));
	}
	
	public static void debug(String msg, Object... obj) {
		log.debug(wrapMsg(msg, obj));
	}
	
	public static void info(String msg, Object... obj) {
		log.info(wrapMsg(msg, obj));
	}
	
	public static void warn(String msg, Object... obj) {
		log.warn(wrapMsg(msg, obj));
	}
	
	public static void error(String msg, Object... obj) {
		log.error(wrapMsg(msg, obj));
	}
	
	public static void error(String msg, Throwable cause) {
		log.error(wrapMsg(msg), cause);
	}

	private static String wrapMsg(String msg, Object... keyValues) {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ")
				.append(ServiceLogger.sessionMap.get() == null ? "undefined"
						: ServiceLogger.sessionMap.get().get("serCode"))
				.append(" ] [ ")
				.append(ServiceLogger.sessionMap.get() == null ? "undefined"
						: ServiceLogger.sessionMap.get().get("sysTraceNo"))
				.append(" ] [ ")
				.append(ServiceLogger.sessionMap.get() == null ? "undefined"
						: ServiceLogger.sessionMap.get().get("originNo"))
				.append(" ] ").append(msg);
		if (keyValues != null && keyValues.length > 0) {
			boolean isKey = true;
			for (Object entry : keyValues) {
				if (isKey) {
					isKey = false;
					sb.append(":");
					sb.append(entry);
				} else {
					sb.append("=");
					sb.append(entry);
					isKey = true;
				}
			}
		}
		return sb.toString();
	}

}
