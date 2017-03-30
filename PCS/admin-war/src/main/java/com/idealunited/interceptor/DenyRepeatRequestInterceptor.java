package com.idealunited.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.idealunited.poss.utils.TokenHelper;

public class DenyRepeatRequestInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 请求前处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		boolean isOK = TokenHelper.removeToken(request);

		if (isOK || (!isNeedDenyRepeatRequest())) {
			return true;
		} else {
			request.setAttribute("url", request.getRequestURI());
			request.getRequestDispatcher(getRequestErrorPage()).forward(request, response);
			return false;
		}
	}

	/**
	 * 异常页面
	 */
	private String requestErrorPage;
	/**
	 * 是否令token生效
	 */
	private boolean needDenyRepeatRequest = true;

	public boolean isNeedDenyRepeatRequest() {
		return needDenyRepeatRequest;
	}

	public void setNeedDenyRepeatRequest(boolean needDenyRepeatRequest) {
		this.needDenyRepeatRequest = needDenyRepeatRequest;
	}

	public String getRequestErrorPage() {
		return requestErrorPage;
	}

	public void setRequestErrorPage(String requestErrorPage) {
		this.requestErrorPage = requestErrorPage;
	}

}
