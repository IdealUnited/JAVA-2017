package com.idealunited.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zengjin
 * @date 2010-8-4
 * @param
 */
public class CookieUtil {

	private static final int maxAge = 60 * 60 * 24 * 365;
	private static final String path = "/website/";

	public void setCookie(HttpServletResponse response, String cookieKey,
			String cookieValue) {
		Cookie newcookie;
		newcookie = new Cookie(cookieKey, cookieValue);
		newcookie.setMaxAge(maxAge);
		newcookie.setPath(path);
		newcookie.setSecure(true);
		response.addCookie(newcookie);
	}

	public String getCookie(HttpServletRequest request, String cookieKey) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equalsIgnoreCase(cookieKey)) {
					return c.getValue();
				}

			}
		}

		return null;
	}

	public void updateCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieKey, String newValue) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equalsIgnoreCase(cookieKey)) {
					String oldValue = cookies[i].getValue();
					c.setValue(newValue);
					response.addCookie(c);
					break;
				}

			}
		}

	}

	public void clearCookie(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		try {
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					cookies[i].setPath(path);
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setCookieFile(HttpServletResponse response, String cookieKey,
			String cookieValue) {
		Cookie newcookie;
		newcookie = new Cookie(cookieKey, cookieValue);
		newcookie.setMaxAge(maxAge);
		newcookie.setPath(path);
		response.addCookie(newcookie);
	}
}
