package com.idealunited.poss.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

/**
 * <p>
 * 产生令牌辅助类
 * </p>
 * 
 * @author Herny_zeng
 * 
 */
public class TokenHelper {

	final public static String TOKEN_NAME = "bocpToken";

	final public static String TOKEN_SET_KEY = "com.paic.egis.common.web.tag.TokenHelper$SET";
	final public static String TOKEN_LIST_KEY = "com.paic.egis.common.web.tag.TokenHelper$LIST";

	/**
	 * <p>
	 * 产生一个新的令牌
	 * </p>
	 * 
	 * @param formId
	 * @param pageContext
	 * @return
	 */
	final static public String newToken(String formId, PageContext pageContext) {
		HttpSession session = pageContext.getSession();
		synchronized (session) {
			@SuppressWarnings("unchecked")
			Set<String> tokens = (Set<String>) session.getAttribute(TokenHelper.TOKEN_SET_KEY);
			if (null == tokens) {
				tokens = new HashSet<String>();
				session.setAttribute(TokenHelper.TOKEN_SET_KEY, tokens);
				session.setAttribute(TokenHelper.TOKEN_LIST_KEY, new LinkedList<String>());
			}
			// 删除其他未提交Token多余
			if (formId != null && formId.length() > 0) {
				String form = "**" + formId;
				for (Iterator<String> it = tokens.iterator(); it.hasNext();) {
					String token = it.next();
					if (token.endsWith(form)) {
						it.remove();
					}
				}
			}
			String newKey = generateId(formId);
			tokens.add(newKey);
			@SuppressWarnings("unchecked")
			LinkedList<String> history = (LinkedList<String>) session.getAttribute(TokenHelper.TOKEN_LIST_KEY);
			history.add(0, newKey);
			// 防止内存问题
			safeRemove(tokens, history);
			return newKey;
		}
	}

	public final static int TRACK_SIZE = 100;

	/**
	 * 防止内存问题
	 * 
	 * @param tokens
	 * @param traces
	 */
	static private void safeRemove(Set<String> tokens, LinkedList<String> traces) {
		while (traces.size() > TRACK_SIZE) {
			Object key = traces.removeLast();
			tokens.remove(key);
		}
	}

	/**
	 * 移除令牌
	 * 
	 * @param request
	 * @return
	 */
	final public static boolean removeToken(HttpServletRequest request) {
		String token = request.getParameter(TokenHelper.TOKEN_NAME);
		// 没有Token，继续后续处理
		if (null == token) {
			return true;
		}

		HttpSession session = request.getSession(false);
		// 没有session不继续处理
		if (null == session) {
			return false;
		}

		synchronized (session) {
			@SuppressWarnings("unchecked")
			Set<String> tokens = (Set<String>) session.getAttribute(TokenHelper.TOKEN_SET_KEY);
			if (null == tokens) {
				return false;
			}

			if (!tokens.contains(token)) {
				return false;
			}
			tokens.remove(token);
		}
		return true;
	}

	private final static char[] letters = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

	private final static int size = 24;

	/**
	 * 产生随机数
	 * 
	 * @param form
	 * @return
	 */
	private static String generateId(String form) {

		Random r = new Random();

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < size; i++) {
			buf.append(letters[r.nextInt(letters.length)]);
		}
		if (null == form || form.length() == 0) {
			return buf.toString();
		}
		return buf.toString() + "**" + form;
	}
}
