package com.idealunited.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 支付链 过滤器
 * 
 * @author zengjin
 * 
 */
public class PrivateFilter implements Filter {

	private static final Log logger = LogFactory.getLog(PrivateFilter.class);
	private static final String loginOutUrl = "/outapp.htm";
	private static final String apploginOutUrl = "/logout.htm";
	// see autoJump.html
	private static final String isLoginFlag = "1";

	// 需要强制登录的url
	private String[] needLoginUrl = null;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String forwadrUrl = loginOutUrl;

		String requestPath = request.getRequestURI();
		// String callBackUrl=AppConf.defaultCallBack;
		String isLogin = request.getParameter("isLogin");

		logger.info("privateFilter lisenter  requestPath is :" + requestPath);
		if (!isNeedLogin(requestPath, isLogin)) {
			logger.info("privateFilter writer the log  user login success  [memberCode:"
					+ request.getSession().getAttribute("memberCode") + "]");

			filterChain.doFilter(new XSSHttpServletRequestWrapper(
					(HttpServletRequest) servletRequest), servletResponse);
			return;
		}

		/***************** 跳登录页 ********************/
		RequestDispatcher rd = servletRequest.getRequestDispatcher(forwadrUrl);
		logger.info("privateFilter writer the log  [no session  will forward :"
				+ forwadrUrl + "]");
		rd.forward(servletRequest, servletResponse);

		return;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String url = config.getInitParameter("needLoginUrl");
		if (StringUtils.isNotBlank(url)) {
			needLoginUrl = url.split("[,]");
		}
	}

	/*
	 * 需要强制登录的url
	 */
	private boolean isNeedLogin(String path, String isLogin) {
		if (needLoginUrl == null || needLoginUrl.length == 0
				|| StringUtils.isEmpty(path)) {
			return false;
		}
		for (String t : needLoginUrl) {
			if (path.endsWith(t)) {
				if (isLoginFlag.equals(isLogin)) {
					// 已经登录了，不需要再登录
					return false;
				}
				return true;
			}
		}
		return false;
	}
}
